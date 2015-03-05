/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbchaoa.common;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abinash_s
 */
public class FileUtility {
    public static void insertRecords( String filePath,String [] data) throws IOException{
        
        CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
         writer.writeNext(data);
      writer.close();
    }
    
    public static CSVReader getRecords( String filePath) throws IOException{
        
        return new CSVReader(new FileReader(filePath));
    }
    
//    public static List<String> insertRecord( String filePath,String  data1) throws IOException{
//       List<String> lst = new ArrayList<String>();
//        CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
//         writer.writeNext(data);
//      writer.close();
//        return null;
//    }
}
