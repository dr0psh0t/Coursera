package com.coursera.arraylistdata.week2.storiesfromtemplates;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> namesOfCharacters;
    private ArrayList<Integer> countsOfEachCharacter;

    public CharactersInPlay() {
        namesOfCharacters = new ArrayList<>();
        countsOfEachCharacter = new ArrayList<>();
    }

    public void update(String person) {
        if (!namesOfCharacters.contains(person)) {
            namesOfCharacters.add(person);
            countsOfEachCharacter.add(1);
        } else {
            int index = namesOfCharacters.indexOf(person);
            countsOfEachCharacter.set(index, countsOfEachCharacter.get(index) + 1);
        }
    }

    public void findAllCharacters() {
        FileResource resource = new FileResource();

        for (String lines : resource.lines()) {
            int firstPeriodIndex = lines.indexOf(".");

            //  to find a character, the period should be found and the period index
            //  should be less than the length-1 of the line
            if (firstPeriodIndex != -1 && firstPeriodIndex < (lines.length() - 1)) {

                //  if line dont have four spaces at start and first character is not a letter,
                //  a character is found

                /*
                if (!lines.substring(0, 4).equals("    ") && !Character.isLetter(lines.charAt(0))) {
                    String character = lines.substring(0, firstPeriodIndex);
                    update(character);
                }*/

                if (!lines.substring(0, 2).equals("  ") && Character.isLetter(lines.charAt(0))) {
                    String character = lines.substring(0, firstPeriodIndex);
                    update(character);
                }
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < namesOfCharacters.size(); i++) {
            int count = countsOfEachCharacter.get(i);

            if (count >= num1 && count <= num2) {
                System.out.println(namesOfCharacters.get(i)+" "+count);
            }
        }
    }

    public void printAll() {
        for (int i = 0; i < namesOfCharacters.size(); i++) {
            System.out.println(namesOfCharacters.get(i)+" "+countsOfEachCharacter.get(i));
        }
    }

    public void mostSpeakingParts() {
        int index = 0;
        for (String s : namesOfCharacters) {

        }
    }

    public void tester() {
        findAllCharacters();

        printAll();
        //charactersWithNumParts(10, 15);
    }

    public static void main(String[] args) {
        CharactersInPlay characters = new CharactersInPlay();
        characters.tester();
    }
}
