package com.coursera.arraylistdata.week4.knownlanguageandkeylength.test;

import com.coursera.arraylistdata.week4.knownlanguageandkeylength.CaesarCipher;
import com.coursera.arraylistdata.week4.knownlanguageandkeylength.CaesarCracker;
import com.coursera.arraylistdata.week4.knownlanguageandkeylength.VigenereBreaker;
import com.coursera.arraylistdata.week4.knownlanguageandkeylength.VigenereCipher;
import edu.duke.FileResource;

import java.util.Arrays;

public class Testing {

    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\arraylistdata\\week4\\knownlanguageandkeylength\\vigeneretestdata\\";

    public static void main(String[] args) {
        testBreakVigenere();
    }

    public static void testBreakVigenere() {
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.breakVigenere();
    }

    public static void testVigenereBreaker() {
        FileResource fr = new FileResource(PATH+"athens_keyflute.txt");

        VigenereBreaker vigenereBreaker = new VigenereBreaker();

        int[] tryKeyLen = vigenereBreaker.tryKeyLength(fr.asString(), 5, 'e');
        System.out.println(Arrays.toString(tryKeyLen));
    }

    public static void testVigenereCipher() {
        FileResource fr = new FileResource(PATH+"titus-small.txt");
        VigenereCipher vigenereCipher = new VigenereCipher(new int[]{17, 14, 12, 4});

        String encText = vigenereCipher.encrypt(fr.asString());
        System.out.println(encText);
        System.out.println(vigenereCipher.decrypt(encText));

        CaesarCracker caesarCracker = new CaesarCracker();
        for (int i : caesarCracker.countLetters(fr.asString())) {
            System.out.print(i+" ");
        }
    }

    public static void testCaesarCipher() {
        CaesarCipher caesarCipher = new CaesarCipher(4);

        String message = "Daryll David Dagondon";

        String encMessage = caesarCipher.encrypt(message);
        String decMessage = caesarCipher.decrypt(encMessage);

        System.out.println(encMessage);
        System.out.println(decMessage);
    }

    public static void testCaesarCracker() {
        CaesarCipher caesarCipher = new CaesarCipher(4);

        String message = "Daryll David Dagondon";
        String encMessage = caesarCipher.encrypt(message);

        CaesarCracker caesarCracker = new CaesarCracker();

        System.out.println("encMessage = "+encMessage);
        int key = caesarCracker.getKey(encMessage);
        System.out.println("key of encrypted message = "+key);
    }
}
