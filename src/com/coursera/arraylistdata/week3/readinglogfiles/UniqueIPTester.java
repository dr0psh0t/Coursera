package com.coursera.arraylistdata.week3.readinglogfiles;

public class UniqueIPTester {
    public static String path = "C:\\Users\\wmdcprog\\Desktop\\Web Server Logs From Logs to Visits\\finding unique ip addresses\\";

    public static void main(String[] args) {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile(path+"short-test_log");;
        int uniqueIPs = la.countUniqueIPs();

        System.out.println("There are "+uniqueIPs+" IPs");
    }
}
