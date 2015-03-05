/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbchaoa.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.sbchaoa.common.FileUtility;
import com.sbchaoa.model.NominateMyself;
import com.sbchaoa.model.Response;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Path;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**SS
 *
 * @author abinash_s
 */
@RestController
@RequestMapping(value = "/api/nominatemyself")
@Api(value = "NominateMyself Services")
@Path(value= "nominateMyself")
public class NominateMyselfController {

    @RequestMapping(value = "/nominate", method = RequestMethod.POST)
    public Response create(@RequestBody NominateMyself mNominateMyself) throws Exception, ArrayIndexOutOfBoundsException {
        Date dateNow = new Date();
        SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
        String date_to_string = formatDateJava.format(dateNow);
        mNominateMyself.setDateTime(date_to_string);
        Response mResponse = new Response();
        String[] record = {mNominateMyself.getName(), mNominateMyself.getFlatNbr(), mNominateMyself.getCatagoryName(), mNominateMyself.getDateTime()};
        if (ExitUser(mNominateMyself)) {
            if (mNominateMyself.getName().length() >0 && mNominateMyself.getFlatNbr().length() >0 && mNominateMyself.getCatagoryName().length() >0 &&mNominateMyself.getDateTime().length() >0) {
                FileUtility.insertRecords("D://sbchaoa//SBCHAOA_Nomination.csv", record);  
            }
          
            mResponse.setStatus("Success");
            return mResponse ;
        } else {
            mResponse.setStatus("Already EXIST");
            return mResponse ;
        }

    }

    private boolean ExitUser(NominateMyself mNominateMyself) throws Exception, ArrayIndexOutOfBoundsException {
        CSVReader reader = null;
        try {
            reader = FileUtility.getRecords("D://sbchaoa//SBCHAOA_Nomination.csv");
        } catch (Exception e) {
            System.out.println("File does not Exist");
            String[] recordHeader = {"NAME", "FLAT_NBR", "CATGORY", "TIME"};
            FileUtility.insertRecords("D://sbchaoa//SBCHAOA_Nomination.csv", recordHeader);
            return true;
        }

        //Read CSV line by line and use the string array as you want
        String[] oldRecord;
        while ((oldRecord = reader.readNext()) != null) {
            if (oldRecord != null) {
                if (mNominateMyself.getFlatNbr().equalsIgnoreCase(oldRecord[1])) {
                    return false;
                }
            }
        }
        return true;
    }

    @RequestMapping(value = "{catagoryName}", method = RequestMethod.GET)
    public List<NominateMyself> getNomination(@PathVariable String catagoryName) throws IOException {
        List<NominateMyself> mList = new ArrayList<NominateMyself>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(NominateMyself.class);
        String[] columns = new String[]{"name", "flatNbr", "catagoryName", "dateTime"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        String csvFileName = "D://sbchaoa//SBCHAOA_Nomination.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List list = csv.parse(strat, csvReader);
        for (Object object : list) {
            NominateMyself nominateMyself = (NominateMyself) object;
            if (catagoryName.equalsIgnoreCase(nominateMyself.getCatagoryName())) {
                nominateMyself.getName();
                nominateMyself.getFlatNbr();
                nominateMyself.getCatagoryName();
                nominateMyself.getDateTime();
                mList.add(nominateMyself);
            }
        }
        return mList;
    }
}
