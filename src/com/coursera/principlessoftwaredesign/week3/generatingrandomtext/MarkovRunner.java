package com.coursera.principlessoftwaredesign.week3.generatingrandomtext;

import edu.duke.FileResource;

public class MarkovRunner {
    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\generatingrandomtext\\data\\";

    public static void main(String[] args) {
        MarkovRunner markovRunner = new MarkovRunner();
        markovRunner.runMarkov();
    }

    public void runMarkov() {
        FileResource fr = new FileResource(PATH+"romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');

        //MarkovZero markov = new MarkovZero();
        //markov.setRandom(1024);

        MarkovModel markov = new MarkovModel(7);
        markov.setRandom(953);
        markov.setTraining(st);

        for(int k=0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
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
