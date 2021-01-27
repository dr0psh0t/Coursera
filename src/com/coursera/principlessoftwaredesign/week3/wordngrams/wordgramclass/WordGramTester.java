package com.coursera.principlessoftwaredesign.week3.wordngrams.wordgramclass;

import com.coursera.principlessoftwaredesign.week3.wordngrams.wordgramclass.IMarkovModel;
import edu.duke.FileResource;

import java.util.*;

public class WordGramTester {

	public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week3\\wordngrams\\wordgramclass\\data\\";

	public static void main(String[] args) {
		WordGramTester wordGramTester = new WordGramTester();
		//wordGramTester.testShiftAdd();
		wordGramTester.runMarkovWord();
	}

	public void runMarkovWord() {
		FileResource fr = new FileResource(PATH+"confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovWord markovWord = new MarkovWord(3);
		runModel(markovWord, st, 500, 643);
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

	public void testShiftAdd() {
		String source = "this is a test";
		String[] words = source.split("\\s+");

		WordGram wordGram = new WordGram(words, 0, 4);

		for (int k = 0; k < words.length; k++) {
			System.out.print(wordGram.wordAt(k)+" ");
		}

		WordGram newWordGram = wordGram.shiftAdd("yes");
		System.out.println();
		for (int k = 0; k < words.length; k++) {
			System.out.print(newWordGram.wordAt(k)+" ");
		}

		System.out.println();
		for (int k = 0; k < words.length; k++) {
			System.out.print(wordGram.wordAt(k)+" ");
		}
	}

	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;

		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;

		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}

		WordGram first = list.get(0);
		System.out.println("checking "+first);

		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
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
