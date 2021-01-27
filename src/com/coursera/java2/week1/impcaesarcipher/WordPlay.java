package com.coursera.java2.week1.impcaesarcipher;

public class WordPlay {

    public static boolean isVowel(char ch) {

        char ch2 = Character.isUpperCase(ch) ? Character.toLowerCase(ch) : ch;

        switch (ch2) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public static String replaceVowels(String phrase, char ch) {
        StringBuilder newStringSB = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                newStringSB.setCharAt(i, ch);
            }
        }

        return newStringSB.toString();
    }

    public static String emphasize(String phrase, char ch) {
        StringBuilder newStringSB = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); i++) {

            char ch2 = Character.isUpperCase(phrase.charAt(i)) ?
                    Character.toLowerCase(phrase.charAt(i)) : phrase.charAt(i);

            //if (phrase.charAt(i) == ch) {
            if (ch2 == ch) {
                if (i % 2 == 0) {
                    newStringSB.setCharAt(i, '*');
                } else {
                    newStringSB.setCharAt(i, '+');
                }
            }
        }

        return newStringSB.toString();
    }

    public static void main(String[] args) {
        //System.out.println(replaceVowels("Hello World", '*'));

        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}