/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbchaoa.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.sbchaoa.common.FileUtility;
import com.sbchaoa.model.NominationCatagory;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AllinOn
 */
@RestController
@RequestMapping(value = "/api/nominationcatagory")
@Api(value = "NominationCatagory Services")
@Path(value= "NominationCatagory")
public class NominationCatagoryController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<NominationCatagory> createNomination(@RequestBody NominationCatagory catNomination) throws Exception, Exception {
        String csvOutput = "D://sbchaoa//SBCHAOA_NominationCatagory.csv";
       CSVWriter writer = null;
        if (catNomination.getCatagoryName().length() > 0) {
             writer = new CSVWriter(new FileWriter(csvOutput, true));  
        }
        String[] record = {catNomination.getCatagoryName()};
        if (validateUser(record) && !record[0].trim().equals("")) {
            writer.writeNext(record);
            writer.close();
        }

        List<NominationCatagory> mList = new ArrayList<NominationCatagory>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(NominationCatagory.class);
        String[] columns = new String[]{"catagoryName"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        String csvFileName = "D://sbchaoa//SBCHAOA_NominationCatagory.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List list = csv.parse(strat, csvReader);
        for (Object object : list) {
            NominationCatagory mNomination = (NominationCatagory) object;
            mNomination.getCatagoryName();
            mList.add(mNomination);
        }
        return mList;
    }

    private boolean validateUser(String[] newRecord) throws Exception {

        CSVReader reader = FileUtility.getRecords("D://sbchaoa//SBCHAOA_NominationCatagory.csv");

        //Read CSV line by line and use the string array as you want
        String[] oldRecord;
        while ((oldRecord = reader.readNext()) != null) {
            if (oldRecord != null) {
                if (newRecord[0].equals(oldRecord[0])) {
                    return false;
                }
            }

        }
        return true;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<NominationCatagory> getNomination() throws IOException {
        List<NominationCatagory> mList = new ArrayList<NominationCatagory>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(NominationCatagory.class);
        String[] columns = new String[]{"catagoryName"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        String csvFileName = "D://sbchaoa//SBCHAOA_NominationCatagory.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List list = csv.parse(strat, csvReader);
        for (Object object : list) {
            NominationCatagory mNomination = (NominationCatagory) object;
            mNomination.getCatagoryName();
            mList.add(mNomination);
        }
        return mList;
    }
}
