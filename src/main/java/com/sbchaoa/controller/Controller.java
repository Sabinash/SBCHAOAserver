/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbchaoa.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.sbchaoa.common.EncrypDecryp;
import com.sbchaoa.common.FileUtility;
import com.sbchaoa.exception.ServiceException;
import com.sbchaoa.model.Owner;
import com.sbchaoa.model.PaymentStatus;
import com.sbchaoa.model.Response;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abinash_s
 */
@RestController
@RequestMapping(value = "/api/owner")
@Api(value = "Registration Services")
@Path(value = "registrationService")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Response create(@RequestBody Owner flatOwner) throws Exception {
        String[] record = {flatOwner.getFlatNbr(), flatOwner.getType(), flatOwner.getName(), EncrypDecryp.encrypt(flatOwner.getPswd()), flatOwner.getIsAdmin()};
        Response mResponse = new Response();
        if (validateUser(flatOwner)) {
            if (flatOwner.getFlatNbr().length() > 0 && flatOwner.getType().length() > 0 && flatOwner.getName().length() > 0 && EncrypDecryp.encrypt(flatOwner.getPswd()).length() > 0 && flatOwner.getIsAdmin().length() > 0) {
                FileUtility.insertRecords("D://sbchaoa//SBCHAOA_Owners.csv", record);
            }
            mResponse.setStatus(Arrays.toString(record));
            logger.info("Sucessfully Added to Database");
            return mResponse;
        } else {
            mResponse.setStatus("EXIST");
            logger.info("Already Exist On Database");
            return mResponse;
        }

    }

    private boolean validateUser(Owner flatOwner) throws Exception {
        CSVReader reader = null;
        try {
            reader = FileUtility.getRecords("D://sbchaoa//SBCHAOA_Owners.csv");
        } catch (Exception e) {
            System.out.println("File does not Exist");
            String[] recordHeader = {"FLAT_NBR", "TYPE", "NAME", "PASSWORD", "IS_ADMIN"};
            FileUtility.insertRecords("D://sbchaoa//SBCHAOA_Owners.csv", recordHeader);
            return true;
        }

        //Read CSV line by line and use the string array as you want
        String[] oldRecord;
        while ((oldRecord = reader.readNext()) != null) {
            if (oldRecord != null) {
                if (flatOwner.getFlatNbr().equals(oldRecord[0]) && flatOwner.getType().equals(oldRecord[1])) {
                    return false;
                }
            }

        }
        return true;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody Owner flatOwner) throws ServiceException, IOException, Exception {
        String[] record = {flatOwner.getFlatNbr(), flatOwner.getType(), flatOwner.getName(), flatOwner.getPswd(), flatOwner.getIsAdmin()};
        CSVReader reader;
        String[] oldRecord;
        Response mResponse = new Response();
        if (record != null) {
            try{
            reader = FileUtility.getRecords("D://sbchaoa//SBCHAOA_Owners.csv");
            }catch(IOException exp){
            throw new ServiceException("No such user exist, please signup",exp);
            }
            while ((oldRecord = reader.readNext()) != null) {
                if (oldRecord != null) {

                    if (record[0].equals(oldRecord[0]) && record[1].equals(oldRecord[1])) {
                        if (record[3].equals(EncrypDecryp.decrypt(oldRecord[3]))) {
                            mResponse.setStatus(Arrays.toString(oldRecord));
                            return mResponse;
                        }
                    }
                }
            }
        }
        mResponse.setStatus("INVALID");
        return mResponse;
    }

    @RequestMapping(value = "/getListUser", method = RequestMethod.GET)
    public List<Owner> getNomination() throws IOException {
        List<Owner> mList = new ArrayList<Owner>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(Owner.class);
        String[] columns = new String[]{"type", "name", "pswd", "flatNbr", "isAdmin"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        String csvFileName = "D://sbchaoa//SBCHAOA_Owners.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List list = csv.parse(strat, csvReader);
        for (Object object : list) {
            Owner mOwner = (Owner) object;
            mOwner.getType();
            mOwner.getName();
            mOwner.getPswd();
            mOwner.getFlatNbr();
            mOwner.getIsAdmin();
            mList.add(mOwner);
            
        }
        return mList;
    }
}
