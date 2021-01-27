package com.coursera.arraylistdata.week3.readinglogfiles;

import java.util.ArrayList;
import java.util.Date;

public class Tester {

    public static String path = "C:\\Users\\wmdcprog\\Desktop\\Web Server Logs From Logs to Visits\\finding unique ip addresses\\";

    public static void main(String[] args) {

        LogAnalyzer logAnalyzer = new LogAnalyzer();

        String filename = "weblog1_log";

        logAnalyzer.readFile(path+filename);

        //logAnalyzer.printAll();
        //logAnalyzer.printAllHigherThanNum(400);

        //ArrayList<String> ips = logAnalyzer.uniqueIPVisitsOnDay("Mar 17");
        //System.out.println(ips.size()+" "+ips.toString());

        System.out.println(logAnalyzer.countUniqueIPsInRange(200, 299));
    }
}
