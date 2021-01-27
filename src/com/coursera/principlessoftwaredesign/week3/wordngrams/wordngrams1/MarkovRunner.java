package com.coursera.principlessoftwaredesign.week3.wordngrams.wordngrams1;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {

    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\wordngrams\\wordngrams1\\data\\";

    public static void main(String[] args) {
        MarkovRunner markovRunner = new MarkovRunner();
        markovRunner.runMarkov();
    }

    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text);
        for(int k=0; k < 1; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        }
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        //System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    }

    public void runMarkov() { 
        FileResource fr = new FileResource(PATH+"confucius.txt");
        String st = fr.asString(); 
        st = st.replace('\n', ' ');

        //MarkovWordOne markovWord = new MarkovWordOne();
        //markovWord.setRandom(549);

        MarkovWordTwo markovWordTwo = new MarkovWordTwo();
        runModel(markovWordTwo, st, 500, 832);
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
