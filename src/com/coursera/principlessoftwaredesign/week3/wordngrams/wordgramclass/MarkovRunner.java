package com.coursera.principlessoftwaredesign.week3.wordngrams.wordgramclass;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {

    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\wordngrams\\wordgramclass\\data\\";

    public static void main(String[] args) {
        MarkovRunner markovRunner = new MarkovRunner();
        markovRunner.runMarkov();
    }

    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        printOut(markov.getRandomText(size));

        /*
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        }*/
    }

    public void testHashMap() {
        //String st = "this is a test yes this is really a test yes a test this is wow";
        String st = "this is a test yes this is really a test";
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        runModel(efficientMarkovWord, st, 50, 42);
        //efficientMarkovWord.printHashMapInfo();
    }

    public void runMarkov() { 
        FileResource fr = new FileResource(PATH+"confucius.txt");

        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200);

        //MarkovWord markovWord = new MarkovWord(3);
        //runModel(markovWord, st, 500, 621);

        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(3);
        runModel(efficientMarkovWord, st, 500, 371);
        efficientMarkovWord.printHashMapInfo();
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
