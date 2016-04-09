package com.transerainc.autoui.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.transerainc.autoui.executor.StartExecution;



/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */


public class HtmlReportGen {
	 static public FileWriter pw;
	 String fileName; 

	 
	 Integer passCount = 0;
	 Integer failCount = 0;
 
     public HtmlReportGen(String file, Integer totalTestCases, long totalExecTime, boolean suite) throws IOException {
    	 		
    	 	
    			pw = new FileWriter(file); 	 
	  	 		fileName = file; 	   
	  	 		String reportHeader="TEST CASE EXECUTION REPORT";
	  	 		String ID = "TestCase_ID";
	  	 		String TotalTest = "Total Test Cases Executed ";
	  	 		
	  	 		if (suite){
	  	 			reportHeader="TEST SUITE EXECUTION REPORT";
	  	 			ID = "TestSuite_ID";
	  	 			TotalTest = "Total Test Suite Executed ";
	  	 		}
	  	 		
    	 		long InSec = totalExecTime / 1000;
    	  		long Hrs = InSec / (60 * 60 );
    	  		long Mins = (InSec % (60 * 60)) / 60;
    	  		long Secs = InSec % 60;
    	  		
  	  	 		
  	  	 		pw.append("<pre>		 " + reportHeader + " 	</pre>");
  	  	 		pw.append("\n");
  	  	 	    pw.append("<p>"+ TotalTest + " :" + totalTestCases + "<br/>");
  	  	 	    pw.append("\n");
  	  	 	    pw.append("<pre>Total Execution Time (Hr:Min:Sec): " + Hrs + ":" + Mins + ":" + Secs + "</pre>");
  	  	 	    pw.append("\n\n");
  	  	 	    pw.append("<pre><TABLE BORDER WIDTH=\"100%\"><TR bgcolor=\"BurlyWood\"><TH>" + ID + "<TH> Browser <TH> Start_Time <TH> End_Time <TH> Description <TH> Result </TR>");
                pw.append("\n");      	  	                
                }
     
  
     public void append(String TestURI, String id, String desc, String Res,ArrayList<String>severeLogs) throws IOException{

         if (Res.equals("Pass")){

                     passCount++;
                     pw.append("<TR bgcolor=\"LightGoldenRodYellow\"><TD><font color=\"green\"><A HREF=\"" + TestURI + "\">" + id + "</A></font><TD><font color=\"green\">" + "---" +  "</font><TD><font color=\"green\">" + "---" +  "</font> <TD><font color=\"green\">" + desc + "</font><TD><font color=\"green\">" + Res+ "</font>");

         }else if (Res.equals("Fail")){

                     failCount++;
                     pw.append("<TR bgcolor=\"#FF4500\"><TD><font color=\"#FFFFFF\"><A HREF=\"" + TestURI + "\">" + id + "</A></font><TD><font color=\"#FFFFFF\">" + "---" + "</font><TD><font color=\"#FFFFFF\">" + "---" + "</font><TD><font color=\"#FFFFFF\">" + desc + "</font><TD><font color=\"#FFFFFF\">" + Res + "</font>");
         }
         pw.append("\n");
}



    
    public void appendURI(String TestURI, String id, ArrayList ReportData) throws IOException{    
   	  
    String S_time = (String) ReportData.get(0);
    String E_time = (String) ReportData.get(1);
    String desc = (String) ReportData.get(3);
    String browser=(String) ReportData.get(4);
     
    String Result;
    
    if ((boolean) ReportData.get(2).equals("true"))
  	  {
  			Result = "Pass";
  			passCount++;
  			pw.append("<TR bgcolor=\"LightGoldenRodYellow\"><TD><font color=\"green\"><A HREF=\"" + TestURI + "\">" + id + "</A></font><TD><font color=\"green\">" + browser + "</font><TD><font color=\"green\">" + S_time + "</font><TD><font color=\"green\">" + E_time + "</font><TD><font color=\"green\">" + desc + "</font><TD><font color=\"green\">" + Result + "</font>");
  	  }else if ((boolean) ReportData.get(2).equals("false")){
	  		Result = "Fail";
	  		failCount++;
	  		pw.append("<TR bgcolor=\"#FF4500\"><TD><font color=\"#FFFFFF\"><A HREF=\"" + TestURI + "\">" + id + "</A></font><TD><font color=\"#FFFFFF\">" + browser + "</font><TD><font color=\"#FFFFFF\">" + S_time + "</font><TD><font color=\"#FFFFFF\">" + E_time + "</font><TD><font color=\"#FFFFFF\">" + desc + "</font><TD><font color=\"#FFFFFF\">" + Result + "</font>");	  		
  	  }
  	  pw.append("\n");
   }
  
    
    public void close(){
    	try {
 
    		pw.append("</TABLE>");  		
       		pw.append("</pre>");
    		pw.flush();
    		pw.close();
    		addByLine();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    
    
    public void addByLine(){
    	try{
    		
    		File inFile = new File(fileName);
    		String outfile = fileName + ".tmp";
    		File outFile = new File(outfile);
    		
    		FileInputStream fis  = new FileInputStream(inFile);
    		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
    		
    		FileOutputStream fos = new FileOutputStream(outFile);
    		PrintWriter out = new PrintWriter(fos);
    		
    		String thisLine = "";
    		int i =1;
    		while ((thisLine = in.readLine()) != null) {
    			 if(i == 3) out.println("Passed : " + passCount + "<br/>Failed  : " + failCount + "</p>");
    			 out.println(thisLine);
    			 i++;
    		   }
    		 
    		out.flush();
    		out.close();
    		in.close();

    		inFile.delete();
    		outFile.renameTo(inFile);
    		}catch(IOException e){
    			e.printStackTrace();
    		}
    }
    
}

