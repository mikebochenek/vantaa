/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.tools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.vantaa3.CommonNameUtil;

public class ConvertParagraphs {

	private static int id = 417;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int counter = 0;
			Scanner in = new Scanner(new FileReader("/tmp/input.txt"));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line != null && line.length() > 1) {
					String[] sentences = line.split("\\.");
					for (String sentence : sentences) {
						if (sentence != null && sentence.length() > 5) {
							System.out.println(generateInsertSQL(sentence));
							counter++;
						}
						//System.out.println ( "    " + sentence);
					}
					//System.out.println (line);
				}
			}
			
			//System.out.println("all done: " + counter);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * INSERT INTO Recommendation (`id`,`rating`,`locale`,`sentence`,`tags`) VALUES ('265','5','','$NAME is a fantastic teammate, with positive energy and dedication to work.','teammate,energy,dedication');
	 * 
	 * @param s
	 * @return
	 */
	private static String generateInsertSQL(String s) {
		for (String name : names) {
			s = s.replaceAll(name, "\\$NAME");
		}

		// http://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
		s = s.replaceAll("['\\\\]", "\\\\$0");

		return "INSERT INTO Recommendation (`id`,`rating`,`locale`,`sentence`,`tags`) VALUES ('"+ (id++) + "','5','','" + s.trim() + "','');";
	}
	
	private static List<String> names = CommonNameUtil.getNames();
	
	static {
		names.add("Wayne");
		names.add("Derek");
		names.add("Bob");
	};

}
