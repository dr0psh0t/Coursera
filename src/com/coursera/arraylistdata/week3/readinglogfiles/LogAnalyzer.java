package com.coursera.arraylistdata.week3.readinglogfiles;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {

        FileResource fr = new FileResource(filename);
        LogEntry logEntry;

        for (String line : fr.lines()) {
            logEntry = WebLogParser.parseEntry(line);
            records.add(logEntry);
        }
    }

    /* Alternate Solution
    public int countUniqueIPs() {
        HashMap<String, Integer> counts = countVisitsPerIP();
        return counts.size();
    }*/

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();

            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }

        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() > num) {
                System.out.println(logEntry);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry logEntry : records) {

            Date date = logEntry.getAccessTime();
            String str = date.toString();

            if (str.contains(someday)) {
                if (!uniqueIPs.contains(logEntry.getIpAddress())) {
                    uniqueIPs.add(logEntry.getIpAddress());
                }
            }
        }

        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry logEntry : records) {
            int statusCode = logEntry.getStatusCode();
            String ip = logEntry.getIpAddress();

            if (statusCode >= low && statusCode <= high) {
                if (!uniqueIPs.contains(ip)) {
                    uniqueIPs.add(ip);
                }
            }
        }

        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();

        for (LogEntry le : records) {
            String ip = le.getIpAddress();

            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }

        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> hashMap) {
        int visits = 0;

        for (String key : hashMap.keySet()) {
            int val = hashMap.get(key);

            if (val > visits) {
                visits = val;
            }
        }

        return visits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> hashMap) {
        ArrayList<String> ips = new ArrayList<>();
        int mostVisits = mostNumberVisitsByIP(hashMap);

        for (String key : hashMap.keySet()) {
            if (mostVisits == hashMap.get(key)) {
                ips.add(key);
            }
        }

        return ips;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> ips;

        for (LogEntry logEntry : records) {

            Date date = logEntry.getAccessTime();
            String dateStr = date.toString();

            int space1stIndex = dateStr.indexOf(" ")+1;
            String trimmed = dateStr.substring(space1stIndex);

            int index2 = trimmed.indexOf(" ", trimmed.indexOf(" ")+1);
            String day = trimmed.substring(0, index2);

            if (!hashMap.containsKey(day)) {
                ips = new ArrayList<>();
            } else {
                ips = hashMap.get(day);
            }

            ips.add(logEntry.getIpAddress());
            hashMap.put(day, ips);
        }

        return hashMap;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hashMap) {
        String day = "";
        int size = 0;

        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).size() > size) {
                day = key;
                size = hashMap.get(key).size();
            }
        }

        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hashMap, String day) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String key : hashMap.keySet()) {
            if (key.equals(day)) {

                for (String ip : hashMap.get(key)) {
                    if (!map.containsKey(ip)) {
                        map.put(ip, 1);
                    } else {
                        map.put(ip, map.get(ip) + 1);
                    }
                }

                break;
            }
        }

        ArrayList<String> ips = new ArrayList<>();
        int largest = 0;
        int val;

        for (String key : map.keySet()) {
            val = map.get(key);

            if (val >= largest) {
                if (!ips.contains(key)) {
                    ips.add(key);
                }

                largest = val;
            }
        }

        return ips;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
