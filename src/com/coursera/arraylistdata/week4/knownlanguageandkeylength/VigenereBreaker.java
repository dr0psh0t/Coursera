package com.coursera.arraylistdata.week4.knownlanguageandkeylength;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\arraylistdata\\week4\\knownlanguageandkeylength\\messages\\";
    //public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\arraylistdata\\week4\\knownlanguageandkeylength\\vigeneretestdata\\";
    public static String DIRPATH = "D:\\daryll\\coursera\\src\\com\\coursera\\arraylistdata\\week4\\knownlanguageandkeylength\\dictionaries\\";

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int k = whichSlice; k < message.length(); k += totalSlices) {
            stringBuilder.append(message.charAt(k));
        }

        return stringBuilder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {

        int[] key = new int[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);

        for (int k = 0; k < klength; k++) {
            String slice = sliceString(encrypted, k, klength);
            int key1 = caesarCracker.getKey(slice);
            key[k] = key1;
        }

        return key;
    }

    public void breakVigenere() {

        FileResource frDanish = new FileResource(DIRPATH+"Danish");
        FileResource frDutch = new FileResource(DIRPATH+"Dutch");
        FileResource frEnglish = new FileResource(DIRPATH+"English");
        FileResource frFrench = new FileResource(DIRPATH+"French");
        FileResource frGerman = new FileResource(DIRPATH+"German");
        FileResource frItalian = new FileResource(DIRPATH+"Italian");
        FileResource frPortuguese = new FileResource(DIRPATH+"Portuguese");
        FileResource frSpanish = new FileResource(DIRPATH+"Spanish");

        HashMap<String, HashSet<String>> languages = new HashMap<>();

        languages.put("Danish", readDictionary(frDanish));
        languages.put("Dutch", readDictionary(frDutch));
        languages.put("English", readDictionary(frEnglish));
        languages.put("French", readDictionary(frFrench));
        languages.put("German", readDictionary(frGerman));
        languages.put("Italian", readDictionary(frItalian));
        languages.put("Portuguese", readDictionary(frPortuguese));
        languages.put("Spanish", readDictionary(frSpanish));

        FileResource fr = new FileResource(PATH+"secretmessage4.txt");
        String encText = fr.asString();
        String decText = breakForAllLanguage(encText, languages);
        System.out.println(decText);

        /*
        System.out.println(languages.get("Danish").size());
        System.out.println(languages.get("Dutch").size());
        System.out.println(languages.get("French").size());
        System.out.println(languages.get("German").size());
        System.out.println(languages.get("Italian").size());
        System.out.println(languages.get("Portuguese").size());
        System.out.println(languages.get("Spanish").size());
         */

        /*
        FileResource fr = new FileResource(PATH+"secretmessage2.txt");
        FileResource frDir = new FileResource(DIRPATH+"English");
        HashSet<String> dictionary = readDictionary(frDir);
        String encText = fr.asString();
        String breakForLanguage = breakForLanguage(encText, dictionary);
        System.out.println(breakForLanguage);*/

        /*
        FileResource fr = new FileResource();
        String encText = fr.asString();

        int[] keyLength = tryKeyLength(encText, 4, 'e');
        System.out.println(Arrays.toString(keyLength));

        VigenereCipher vigenereCipher = new VigenereCipher(keyLength);
        System.out.println(vigenereCipher.decrypt(encText));*/
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<String, Integer> charMap = new HashMap<>();

        int eachWordLen;
        int k;

        String let;

        for (String eachWord : dictionary) {
            eachWordLen = eachWord.length();

            for (k = 0; k < eachWordLen; ++k) {
                let = String.valueOf(eachWord.charAt(k));

                if (charMap.containsKey(let)) {
                    charMap.put(let, charMap.get(let) + 1);
                } else {
                    charMap.put(let, 1);
                }
            }
        }

        int charCount = 0;
        int loopCount;
        char mostCommonCharIn = ' ';

        for (String letter : charMap.keySet()) {
            loopCount = charMap.get(letter);

            if (loopCount > charCount) {
                charCount = loopCount;
                mostCommonCharIn = letter.charAt(0);
            }
        }

        return mostCommonCharIn;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hashSet = new HashSet<>();

        for (String line : fr.lines()) {
            hashSet.add(line.toLowerCase());
        }

        return hashSet;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;

        for (String word : message.split("\\W")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public String breakForAllLanguage(String encrypted, HashMap<String, HashSet<String>> languages) {

        String decText = "";
        HashSet<String> langSet;

        for (String eachlang : languages.keySet()) {

            System.out.println(eachlang);
            langSet = languages.get(eachlang);
            decText = breakForLanguage(encrypted, langSet);
        }

        return decText;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {

        int countWords = 0;
        int loopCount;
        //int keyLengthUsed = 0;

        String loopDecText;
        String decText = "";

        VigenereCipher vigenereCipher;

        int keyLength;

        for (keyLength = 1; keyLength <= 100; ++keyLength) {
            //vigenereCipher = new VigenereCipher(tryKeyLength(encrypted, keyLength, 'e'));

            vigenereCipher = new VigenereCipher(tryKeyLength(encrypted, keyLength, mostCommonCharIn(dictionary)));
            loopDecText = vigenereCipher.decrypt(encrypted);
            loopCount = countWords(loopDecText, dictionary);

            if (loopCount > countWords) {
                countWords = loopCount;
                decText = loopDecText;
                //keyLengthUsed = keyLength;
            }
        }

        //System.out.println("keyLengthUsed="+keyLengthUsed);
        System.out.println("valid words="+countWords);
        return decText;
    }


}
