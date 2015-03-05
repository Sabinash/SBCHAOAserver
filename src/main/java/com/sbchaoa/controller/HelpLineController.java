/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbchaoa.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.sbchaoa.model.Help;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abinash_s
 */
@RestController
@RequestMapping(value = "/api/helpline")
@Api(value = "HelpLine Services")
@Path(value= "helpLine")
public class HelpLineController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Help> getNomination() throws IOException {
        List<Help> mList = new ArrayList<Help>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(Help.class);
        String[] columns = new String[]{"name", "post", "conctNbr"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        String csvFileName = "D://sbchaoa//SBCHAOA_Help.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List list = csv.parse(strat, csvReader);
        for (Object object : list) {
            Help mHelp = (Help) object;
            mHelp.getName();
            mHelp.getPost();
            mHelp.getConctNbr();

            mList.add(mHelp);
        }
        return mList;
    }
}
