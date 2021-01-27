package com.coursera.principlessoftwaredesign.week3.generatingrandomtext;

import edu.duke.FileResource;

public class Tester {
    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\generatingrandomtext\\data\\";

    public static void testFollowsWithFile() {
        FileResource fr = new FileResource(PATH+"confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');

        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);

        System.out.println(markov.getFollows("he").size());
    }

    public static void main(String[] args) {
        testFollowsWithFile();
    }
}