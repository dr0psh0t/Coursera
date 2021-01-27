package com.coursera.arraylistdata.week3.readinglogfiles;

import java.util.HashMap;

public class CountTester {
    public static String path = "C:\\Users\\wmdcprog\\Desktop\\Web Server Logs From Logs to Visits\\counting website visits\\";

    public static void main(String[] args) {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile(path+"weblog2_log");

        //System.out.println(la.countUniqueIPs());
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
        //System.out.println(la.countUniqueIPsInRange(200,299));

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        //System.out.println(counts);
        //System.out.println(la.mostNumberVisitsByIP(counts));
        //System.out.println(la.iPsMostVisits(counts));

        //System.out.println(la.iPsForDays());
        //System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29"));
    }
}