package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.trash;

import edu.duke.FileResource;

import static java.lang.System.out;

public class MarkovRunner {

    private static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\generatingrandomtext\\data\\";

    public static void main(String[] args) {
        MarkovRunner markovRunner = new MarkovRunner();

        //markovRunner.runMarkovTest();
        //markovRunner.runMarkovZero();
        //System.out.println(markovRunner.getFollows());
        markovRunner.runMarkovOne();
        //markovRunner.runMarkovTwo();
        //markovRunner.runMarkovFour();
    }

    public void runMarkovFour() {
        FileResource fr = new FileResource(PATH+"confucius.txt");

        String text = fr.asString();
        text = text.replace('\n', ' ');

        MarkovFour markov = new MarkovFour();
        markov.setTraining(text);
        markov.setRandom(25);

        for (int k = 0; k < 3; k++) {
            String randomText = markov.getRandomText(500);
            printOut(randomText);
        }
    }

    //  replace \n with space
    public void runMarkovTest() {
        FileResource fr = new FileResource(PATH+"sample.txt");
        String text = fr.asString();
        out.println("text=>"+text);

        text = text.replace('\n', ' ');
        out.println("trainingText=>"+text);

        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);

        out.println();

        String randomText = markov.getRandomText(14);
        out.println("randomText=>"+randomText);

        //printOut(randomText);
    }

    public void runMarkovZero() {
        FileResource fr = new FileResource(PATH+"sample.txt");
        String text = fr.asString();

        text = text.replace('\n', ' ');

        MarkovZero markov = new MarkovZero();
        markov.setTraining(text);

        for (int k = 0; k < 3; k++) {
            String randomText = markov.getRandomText(500);
            printOut(randomText);
        }
    }

    public void runMarkovTwo() {
        FileResource fr = new FileResource(PATH+"romeo.txt");
        String text = fr.asString();

        text = text.replace('\n', ' ');
        //text = "this is a test yes a test";

        MarkovTwo markov = new MarkovTwo();
        markov.setTraining(text);

        for (int k = 0; k < 3; k++) {
            String randomText = markov.getRandomText(500);
            printOut(randomText);
        }
    }

    public void runMarkovOne() {
        FileResource fr = new FileResource(PATH+"confucius.txt");

        String text = fr.asString();
        text = text.replace('\n', ' ');

        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);
        markov.setRandom(42);

        for (int k = 0; k < 3; k++) {
            String randomText = markov.getRandomText(500);
            printOut(randomText);
        }
    }

    //  ================================================================================

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;

        System.out.println("----------------------------------");

        for (int k=0; k < words.length; k++) {
            System.out.print(words[k]+ " ");

            psize += words[k].length() + 1;

            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }

        System.out.println("\n----------------------------------");
    }
}