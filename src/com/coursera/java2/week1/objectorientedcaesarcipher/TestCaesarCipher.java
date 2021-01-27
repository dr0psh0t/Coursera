package com.coursera.java2.week1.objectorientedcaesarcipher;

import edu.duke.FileResource;

public class TestCaesarCipher {

    public static String preUrl = "D:\\daryll\\coursera\\src\\com\\coursera\\java2\\week1\\objectorientedcaesarcipher\\";

    public static int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alph.indexOf(ch);

            if (index != -1) {
                counts[index] = counts[index] + 1;
            }
        }

        return counts;
    }

    public static int maxIndex(int[] vals) {
        int maxIndex = 0;

        for (int index = 0; index < vals.length; index++) {
            if (vals[index] > vals[maxIndex]) {
                maxIndex = index;
            }
        }

        return maxIndex;
    }

    public static int getKey(String s) {

        int[] frequencies = countLetters(s);
        int maxIndex = maxIndex(frequencies);

        System.out.println(maxIndex+" <<<<<>>>>> "+frequencies[maxIndex]);

        int key = maxIndex - 4;

        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }

        return key;
    }

    public static void breakCaesarCipher(String input) {
        System.out.println("encrypted: "+input);

        int key = getKey(input);
        System.out.println("key used = "+key);

        CaesarCipher cc = new CaesarCipher(key);
        String dec = cc.decryptCI(input);

        System.out.println("decrypted: "+dec);
    }

    public static void simpleTests() {
        CaesarCipher cc = new CaesarCipher(18);
        FileResource fileResource = new FileResource(preUrl+"wordsLotsOfEs.txt");

        String enc = cc.encryptCI(fileResource.asString());

        breakCaesarCipher(enc);
    }

    public static void main(String[] args) {

        CaesarCipher cc = new CaesarCipher(15);

        //String enc = cc.encryptCI("Can you imagine life WITHOUT the internet AND computers in your pocket");
        //String enc = cc.encryptCI("Can you imagine");
        //System.out.println(enc);

        //String input = "Ceaene yeoeue iemeaegine e";
        //String input = "Can you imagine";
        //String input = "aeeiiioooouuuuu";
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        breakCaesarCipher(cc.encryptCI(input));
    }
}
