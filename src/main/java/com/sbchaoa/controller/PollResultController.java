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
import com.sbchaoa.model.NominationCatagory;
import com.sbchaoa.model.PollResult;
import com.sbchaoa.model.VotingResult;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value = "/api/pollresult")
@Api(value = "PollResult Services")
@Path(value= "pollResult")
public class PollResultController {

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestBody PollResult mPollResult) throws Exception, ArrayIndexOutOfBoundsException {
        Date dateNow = new Date();
        SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
        String date_to_string = formatDateJava.format(dateNow);
        mPollResult.setDateTime(date_to_string);
        String[] record = {mPollResult.getFlatNbr(), mPollResult.getCandidateName(), mPollResult.getCandidateFlatNbr(), mPollResult.getCatagoryName(), mPollResult.getDateTime()};

        if (AlreadyVoted(mPollResult)) {
            FileUtility.insertRecords("D://sbchaoa//SBCHAOA_PollResult.csv", record);
            return "Success";
        } else {
            return "Already EXIST";
        }
    }

    private boolean AlreadyVoted(PollResult mPollResult) throws Exception, ArrayIndexOutOfBoundsException {
        CSVReader reader = null;
        try {
            reader = FileUtility.getRecords("D://sbchaoa//SBCHAOA_PollResult.csv");
        } catch (Exception e) {
            System.out.println("File does not Exist");
            String[] recordHeader = {"FLAT_NBR", "CANDIDATE_NAME", "CANDIDATE_FLAT_NBR", "CATGORY", "TIME"};
            FileUtility.insertRecords("D://sbchaoa//SBCHAOA_PollResult.csv", recordHeader);
            return true;
        }

        //Read CSV line by line and use the string array as you want
        String[] oldRecord;
        while ((oldRecord = reader.readNext()) != null) {
            if (oldRecord != null) {
                if (mPollResult.getFlatNbr().equalsIgnoreCase(oldRecord[0]) && mPollResult.getCatagoryName().equalsIgnoreCase(oldRecord[3])) {
                    return false;
                }
            }
        }
        return true;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<VotingResult> getNomination() throws IOException {
        Map<String, Integer> countResult = new HashMap<String, Integer>();
        List<VotingResult> mList = new ArrayList<VotingResult>();
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(PollResult.class);
        String[] columns = new String[]{"flatNbr", "candidateName", "candidateFlatNbr", "catagoryName", "dateTime"};
        strat.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        List<String> nomCategory = new ArrayList<String>();
        Map<String, List<String>> nominationMap;

        String csvFileName = "D://sbchaoa//SBCHAOA_PollResult.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List pollingList = csv.parse(strat, csvReader);

        nominationMap = getNominationMap(nomCategory);
        for (String cat : nomCategory) {
            List<String> flatNbrs = (List<String>) nominationMap.get(cat);

            for (String fltNbr : flatNbrs) {
                int count = 0;
                for (Object object : pollingList) {

                    PollResult mPollResult = (PollResult) object;
                    if (cat.equals(mPollResult.getCatagoryName()) && fltNbr.equals(mPollResult.getCandidateFlatNbr())) {
                        count++;
                    }

                }
                System.out.println(cat + " : " + fltNbr + " : " + count);
                VotingResult mVotingResult = new VotingResult();
                mVotingResult.setCatagoryName(cat);
                mVotingResult.setFlatNbr(fltNbr);
                mVotingResult.setStatus(String.valueOf(count));
                mList.add(mVotingResult);
            }

        }
        return mList;
    }

    private Map<String, List<String>> getNominationMap(List<String> nomCategory) throws IOException {

        Map<String, List<String>> nominationMap = new HashMap<String, List<String>>();
        List<String> userList;

        CsvToBean csv = new CsvToBean();
        ColumnPositionMappingStrategy strat;
        ColumnPositionMappingStrategy strat1;
        String[] columns;
        String[] columns1;
        String csvFileName;
        String csvFileName1;
        CSVReader csvReader;
        CSVReader csvReader1;
        List list;
        List list1;

        strat = new ColumnPositionMappingStrategy();
        strat.setType(NominationCatagory.class);
        columns = new String[]{"catagoryName"};
        strat.setColumnMapping(columns);
        csvFileName = "D://sbchaoa//SBCHAOA_NominationCatagory.csv";
        csvReader = new CSVReader(new FileReader(csvFileName));
        list = csv.parse(strat, csvReader);
        for (Object object : list) {
            NominationCatagory nominationCatagory = (NominationCatagory) object;
            nomCategory.add(nominationCatagory.getCatagoryName());
        }
        csvReader.close();

        strat1 = new ColumnPositionMappingStrategy();
        strat1.setType(NominateMyself.class);
        columns1 = new String[]{"name", "flatNbr", "catagoryName", "dateTime"};
        strat1.setColumnMapping(columns1);
        csvFileName1 = "D://sbchaoa//SBCHAOA_Nomination.csv";
        csvReader1 = new CSVReader(new FileReader(csvFileName1));
        list1 = csv.parse(strat1, csvReader1);

        for (String cate : nomCategory) {
            userList = new ArrayList<String>();
            for (Object object : list1) {
                NominateMyself nominationMySelf = (NominateMyself) object;
                if (cate.equals(nominationMySelf.getCatagoryName())) {
                    userList.add(nominationMySelf.getFlatNbr());
                }
            }
            nominationMap.put(cate, userList);
        }

        return nominationMap;
    }
}
