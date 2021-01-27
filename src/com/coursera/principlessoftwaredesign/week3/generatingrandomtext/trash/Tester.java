package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.trash;

import edu.duke.FileResource;

import static java.lang.System.out;

public class Tester {

    private static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\generatingrandomtext\\data\\";

    public static void main(String[] args) {
        testGetFollowsWithFile();
    }

    public static void testGetFollows() {
        String text = "this is a test yes this is a test.";

        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);

        out.println(markov.getFollows("t."));
    }

    public static void testGetFollowsWithFile() {
        FileResource fr = new FileResource(PATH+"confucius.txt");
        String text = fr.asString();
        //System.out.println(text);
        text = text.replace('\n', ' ');

        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);

        System.out.println(markov.getFollows("t").size());
    }
}
