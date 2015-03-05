/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbchaoa.controller;

import au.com.bytecode.opencsv.CSVReader;
import com.sbchaoa.common.FileUtility;
import com.sbchaoa.model.Catagory;
import com.wordnik.swagger.annotations.Api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Path;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abinash_s
 */
@RestController
@RequestMapping(value = "/api/nomination")
@Api(value = "Nomination Services")
@Path(value= "nomination")
public class NominationController {

    @RequestMapping(method = RequestMethod.POST)
    public List<Catagory> create(@RequestBody Catagory catagory) throws IOException {
        List<Catagory> mlist = new ArrayList<Catagory>();

        String[] catagaryRecord = {catagory.getCatagoryName()};

        if (catagory.getCatagoryName().length() > 0) {
              FileUtility.insertRecords("D://Catagory1.csv", catagaryRecord);
        }
      
        CSVReader reader = FileUtility.getRecords("D://Catagory1.csv");


        List<String[]> allRows = reader.readAll();

        //Read CSV line by line and use the string array as you want
        for (String[] row : allRows) {
            Catagory mCatagory = new Catagory();
            mCatagory.setCatagoryName(Arrays.toString(row));
            mlist.add(mCatagory);

        }

        return mlist;

    }
}
