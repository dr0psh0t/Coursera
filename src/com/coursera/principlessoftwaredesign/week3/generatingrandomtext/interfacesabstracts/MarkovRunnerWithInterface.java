package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.interfacesabstracts;

import edu.duke.FileResource;

import java.util.concurrent.TimeUnit;

public class MarkovRunnerWithInterface {
    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\generatingrandomtext\\interfacesabstracts\\data\\";

    public static void main(String[] args) {
        MarkovRunnerWithInterface markov = new MarkovRunnerWithInterface();
        markov.runMarkov();
    }

    public void runMarkov() {
        FileResource fr = new FileResource(PATH+"confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');

        /*
        int size = 200;
        int seed = 42;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);*/

        EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(6);
        runModel(efficientMarkovModel, st, st.length(), 792);
        efficientMarkovModel.printHashMapInfo();
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with "+markov);

        for (int k = 0; k < 1; k++) {
            String randomText = markov.getRandomText(size);
            printOut(randomText);
        }
    }

    public void testHashMap() {
        String text = "yes-this-is-a-thin-pretty-pink-thistle";

        EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(2);
        efficientMarkovModel.setRandom(42);
        efficientMarkovModel.setTraining(text);
        printOut(efficientMarkovModel.getRandomText(50));
        efficientMarkovModel.printHashMapInfo();
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 42;

        EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(2);
        MarkovModel markovModel = new MarkovModel(2);

        long start = System.nanoTime();
        runModel(efficientMarkovModel, st, 1000, seed);
        System.out.println("efficient markov model time = "+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start));

        start = System.nanoTime();
        runModel(markovModel, st, 1000, seed);
        System.out.println("markov model time = "+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start));
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
