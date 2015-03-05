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
import com.sbchaoa.model.Oppinion;
import com.sbchaoa.model.Owner;
import com.sbchaoa.model.PaymentStatus;
import com.sbchaoa.model.Response;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.IOException;
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
@RequestMapping(value = "/api/oppinion")
@Api(value = "Oppinion Services")
@Path(value = "oppinion")
public class OppinionController {
     private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/postOppinion", method = RequestMethod.POST)
    public Response create(@RequestBody Oppinion mOppinion) throws Exception {
        String[] record = {mOppinion.getFltNbr(), mOppinion.getName(), mOppinion.getOppinion(),mOppinion.getCommets()};
        Response mResponse = new Response();
        if (validateUser(mOppinion)) {
            if (mOppinion.getFltNbr().length() > 0&& mOppinion.getName().length() >0&& mOppinion.getOppinion().length() >0 && mOppinion.getCommets().length() >0) {
             FileUtility.insertRecords("D://sbchaoa//SBCHAOA_Oppinion.csv", record);
            }

            mResponse.setStatus("SUCCESS");
            logger.info("Sucessfully Added to Database");
            return mResponse;
        } else {
            mResponse.setStatus("EXIST");
             logger.info("Already Exist On Database");
            return mResponse;
        }

    }

    private boolean validateUser(Oppinion mOppinion) throws Exception {
        CSVReader reader = null;
        try {
            reader = FileUtility.getRecords("D://sbchaoa//SBCHAOA_Oppinion.csv");
        } catch (Exception e) {
            System.out.println("File does not Exist");
            String[] recordHeader = {"FLATNBR", "NAME", "OPPINION", "COMMENTS"};
            FileUtility.insertRecords("D://sbchaoa//SBCHAOA_Oppinion.csv", recordHeader);
            return true;
        }


        //Read CSV line by line and use the string array as you want
        String[] oldRecord;
        while ((oldRecord = reader.readNext()) != null) {
            if (oldRecord != null) {
                if (mOppinion.getFltNbr().equals(oldRecord[0]) && mOppinion.getName().equals(oldRecord[1])) {
                    return false;
                }
            }

        }
        return true;
    }
    
     @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Oppinion> getPaymentrecords() throws IOException {
        List<Oppinion> mList = new ArrayList<Oppinion>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(Oppinion.class);
        String[] columns = new String[]{"fltNbr", "name", "oppinion", "commets"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        String csvFileName = "D://sbchaoa//SBCHAOA_Oppinion.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List list = csv.parse(strat, csvReader);
        for (Object object : list) {
            Oppinion mOppinion = (Oppinion) object;
                mOppinion.getFltNbr();
                mOppinion.getName();
                mOppinion.getOppinion();
                mOppinion.getCommets();
                mList.add(mOppinion);   
        }
        return mList;
    }

}
