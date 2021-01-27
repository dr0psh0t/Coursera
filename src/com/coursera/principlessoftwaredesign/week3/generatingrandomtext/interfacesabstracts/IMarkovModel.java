package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.interfacesabstracts;

public interface IMarkovModel {

    void setTraining(String text);
    void setRandom(int seed);
    String getRandomText(int numChars);
}
