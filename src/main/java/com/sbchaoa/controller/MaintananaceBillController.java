/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbchaoa.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.sbchaoa.model.NominationCatagory;
import com.sbchaoa.model.PaymentStatus;
import com.wordnik.swagger.annotations.Api;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abinash_s
 */
@RestController
@RequestMapping(value = "/api/mntnbillstatus")
@Api(value = "MaintananaceBill Services")
@Path(value= "maintananaceBill")
public class MaintananaceBillController {
    
   @RequestMapping(value = "{flatNbr}",method = RequestMethod.GET)
    public List<PaymentStatus> getNomination(@PathVariable String flatNbr) throws IOException{    
     List<PaymentStatus> mList = new ArrayList<PaymentStatus>();
     ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
     strat.setType(PaymentStatus.class);
       String[] columns = new String[]{"flatNbr","datetime","paymentStatus","modeOfPayment"};
     strat.setColumnMapping(columns);
     CsvToBean csv = new CsvToBean();
     String csvFileName = "D://sbchaoa//SBCHAOA_MNTNBill.csv";
     CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
       List list = csv.parse(strat, csvReader);
        for (Object object : list) {
           PaymentStatus mStatus = (PaymentStatus) object;
            if(flatNbr.equalsIgnoreCase(mStatus.getFlatNbr())){
            mStatus.getFlatNbr();
            mStatus.getDatetime();
            mStatus.getPaymentStatus();
            mStatus.getModeOfPayment();
           mList.add(mStatus);
            }
        }    
        return mList;
    }   
    
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public List<PaymentStatus> getNomination() throws IOException{    
     List<PaymentStatus> mList = new ArrayList<PaymentStatus>();
     ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
     strat.setType(PaymentStatus.class);
       String[] columns = new String[]{"flatNbr","status","datetime","paymentStatus","modeOfPayment"};
     strat.setColumnMapping(columns);
     CsvToBean csv = new CsvToBean();
     String csvFileName = "D://sbchaoa//SBCHAOA_MNTNBill.csv";
     CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
       List list = csv.parse(strat, csvReader);
        for (Object object : list) {
           PaymentStatus mStatus = (PaymentStatus) object;
            mStatus.getFlatNbr();
            mStatus.getStatus();
            mStatus.getDatetime();
            mStatus.getPaymentStatus();
            mStatus.getModeOfPayment();
           mList.add(mStatus);
            }
        return mList;
    }   
}
