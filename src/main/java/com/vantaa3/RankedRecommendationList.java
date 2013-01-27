/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RankedRecommendationList {

	public final static int MAX_RATING = 10;
	
	private static String locale;
	private /* static */ HashMap<String, List<String>>[] list = new HashMap[MAX_RATING];
	
	
	public void add(String tag, String sentence, int rating) {
		assert (rating < MAX_RATING);

		HashMap<String, List<String>> map = list[rating];
		if (map == null) {
			map = new HashMap<String, List<String>>();
			list[rating] = map;
		}
		List<String> sentenceList = map.get(tag);
		if (sentenceList == null) {
			sentenceList = new ArrayList<String>();
			list[rating].put(tag, sentenceList);
		}
		sentenceList.add(randomizeSentence(sentence).trim());
	}

	// dont forget http://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html#sum
	private String randomizeSentence(String sentence) {
		if (sentence.toLowerCase().contains("her ")) {
			sentence = sentence.replaceAll("\\$NAME", CommonNameUtil.getRandomFemale());
		} else {
			sentence = sentence.replaceAll("\\$NAME", CommonNameUtil.getRandomMale());
		}
		
		return sentence.replaceAll("\\$COMPANY", CommonNameUtil.getRandomCompany());
	}
	
	public List<String> getTags(int minRating, int max) {
		List<String> retVal = new ArrayList<String>();
		
		Map<Integer,String> intermediatery = new HashMap<Integer,String>();
		
		if (list[minRating] == null) {
			return retVal;
		}
		
		Iterator<String> keys = list[minRating].keySet().iterator();
		for (int i = 0; keys.hasNext() && i < max; i++) {
			String tag = keys.next();
			int key = list[minRating].get(tag).size();
			if (intermediatery.get(key) != null) {
				intermediatery.put(key, tag + "," + intermediatery.get(key));
			} else {
				intermediatery.put(key, tag);
			}
//			retVal.add(tag);
		}


		Set<Integer> k = intermediatery.keySet();
		Integer[] karray = k.toArray(new Integer[0]);
		Arrays.sort(karray);
		for (int i = karray.length - 1; i >= 0; i--) {
			String s = intermediatery.get(karray[i]);
			String[] tags = s.split(",");
			for (String tag : tags) {
				if (tag != null && tag.trim().length() > 0) {
					retVal.add(tag.trim());
				}
			}
		}
		
		//TODO should take tags from "following" level if we don't get enough
		
		return retVal;
	}
	
	//TODO probably do not need this one anymore...
	public HashMap<String, List<String>> getTabularData(int minRating, int max) {
		HashMap<String, List<String>> retVal = new HashMap<String, List<String>>();
		
		if (list[minRating] == null) {
			return retVal;
		}
		
		Iterator<String> keys = list[minRating].keySet().iterator();
		for (int i = 0; keys.hasNext() && i < max; i++) {
			String tag = keys.next();
			retVal.put(tag, list[minRating].get(tag));
		}
		
		//TODO should take tags from "following" level if we don't get enough
		
		return retVal;
	}
	
	public List<String> getRecommendationSentence(String tag, int minRating, int max) {
		return list[minRating].get(tag);
	}
	
	//TODO
	public void randomizeAndTrim() {
		//HashMap<String, List<String>>[] list = new HashMap[MAX_RATING]
		
		for (int i = 0; i < list.length; i++) {
			//list[i].keySet().iterator();
		}
	}
}
