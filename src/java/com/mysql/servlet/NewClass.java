/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.servlet;

import java.io.*;
import org.json.simple.*;

public class NewClass {  
	  
    public static void main(String[] args) {  
  
        JSONObject headerFile = new JSONObject();  

  
        JSONArray listOfAttrs = new JSONArray();  
        listOfAttrs.add("id"+"");  
        listOfAttrs.add("name"+"");  
        listOfAttrs.add("age"+"");
        listOfAttrs.add("salary"+"");
        listOfAttrs.add("grade" + "");
  
        headerFile.put("Employee", listOfAttrs);  
  
        try {  
              
            // Writing to a file  
            File file=new File("D:\\Employees.json");  
            file.createNewFile();  
            FileWriter fileWriter = new FileWriter(file);  
            System.out.println("Writing JSON object to file");  
            System.out.println("-----------------------");  
            System.out.print(headerFile);  
  
            fileWriter.write(headerFile.toJSONString());  
            fileWriter.flush();  
            fileWriter.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  