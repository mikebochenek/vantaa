/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.Random;

public class CommonNameUtil {

	final static String[] female = {"MARY","PATRICIA","LINDA","BARBARA","ELIZABETH","JENNIFER",
		"MARIA","SUSAN","MARGARET","DOROTHY","LISA","NANCY","KAREN","BETTY","HELEN","SANDRA",
		"DONNA","CAROL","RUTH","SHARON","MICHELLE","LAURA","SARAH","KIMBERLY","DEBORAH",
		"JESSICA","SHIRLEY","CYNTHIA","ANGELA","MELISSA","BRENDA","AMY","ANNA","REBECCA","VIRGINIA",
		"KATHLEEN","PAMELA","MARTHA","DEBRA","AMANDA","STEPHANIE","CAROLYN","CHRISTINE","MARIE","JANET",
		"CATHERINE","FRANCES","ANN","JOYCE","DIANE","ALICE","JULIE","HEATHER","TERESA","DORIS",
		"GLORIA","EVELYN","JEAN","CHERYL","MILDRED","KATHERINE"};
	
	final static String[] male = {"JAMES","JOHN","ROBERT","MICHAEL","WILLIAM","DAVID","RICHARD",
		"CHARLES","JOSEPH","THOMAS","CHRISTOPHER","DANIEL","PAUL","MARK","DONALD","GEORGE","KENNETH",
		"STEVEN","EDWARD","BRIAN","RONALD","ANTHONY","KEVIN","JASON","MATTHEW","GARY","TIMOTHY",
		"JOSE","LARRY","JEFFREY","FRANK","SCOTT","ERIC","STEPHEN","ANDREW","RAYMOND","GREGORY",
		"JOSHUA","JERRY","DENNIS","WALTER","PATRICK","PETER","HAROLD","DOUGLAS","HENRY","CARL","ARTHUR",
		"RYAN","ROGER","JOE","JUAN","JACK","ALBERT","JONATHAN","JUSTIN","TERRY","GERALD","KEITH","SAMUEL"};
	
	final static String[] company = {"Acme, inc.", "Widget Corp", "123 Warehousing", "Demo Company", "Smith and Co.", 
		"Foo Bars", "ABC Telecom", "Fake Brothers", "QWERTY Logistics", "Demo, inc.", "Sample Company", "Sample, inc", 
		"Acme Corp", "Allied Biscuit", "Ankh-Sto Associates", "Extensive Enterprise", "Galaxy Corp", "Globo-Chem", 
		"Mr. Sparkle", "Globex Corporation", "LexCorp", "LuthorCorp", "North Central Positronics", "Omni Consimer Products", 
		"Praxis Corporation", "Sombra Corporation", "Sto Plains Holdings", "Tessier-Ashpool", "Wayne Enterprises", 
		"Wentworth Industries", "ZiffCorp", "Bluth Company", "Strickland Propane", "Thatherton Fuels", "Three Waters", 
		"Water and Power", "Western Gas & Electric", "Mammoth Pictures", "Mooby Corp", "Gringotts", "Thrift Bank", 
		"Flowers By Irene", "The Legitimate Businessmens Club", "Osato Chemicals", "Transworld Consortium", "Universal Export", 
		"United Fried Chicken", "Virtucon", "Kumatsu Motors", "Keedsler Motors", "Powell Motors", "Industrial Automation", 
		"Sirius Cybernetics Corporation", "U.S. Robotics and Mechanical Men", "Colonial Movers", "Corellian Engineering Corporation", 
		"Incom Corporation", "General Products", "Leeding Engines Ltd.", "Blammo", "Input, Inc.", "Mainway Toys", 
		"Videlectrix", "Zevo Toys", "Ajax", "Axis Chemical Co.", "Barrytron", "Carrys Candles", "Cogswell Cogs", "Spacely Sprockets", 
		"General Forge and Foundry", "Duff Brewing Company", "Dunder Mifflin", "General Services Corporation", "Monarch Playing Card Co.", 
		"Krustyco", "Initech", "Roboto Industries", "Primatech", "Sonky Rubber Goods", "St. Anky Beer", "Stay Puft Corporation", 
		"Vandelay Industries", "Wernham Hogg", "Gadgetron", "Burleigh and Stronginthearm", "BLAND Corporation", "Nordyne Defense Dynamics", 
		"Petrox Oil Company", "Roxxon", "McMahon and Tate", "Sixty Second Avenue", "Charles Townsend Agency", "Spade and Archer", 
		"Megadodo Publications", "Rouster and Sideways", "C.H. Lavatory and Sons", "Globo Gym American Corp", "The New Firm", 
		"SpringShield", "Compuglobalhypermeganet", "Data Systems", "Gizmonic Institute", "Initrode", "Taggart Transcontinental", 
		"Atlantic Northern", "Niagular", "Plow King", "Big Kahuna Burger", "Big T Burgers and Fries", "Chez Quis", 
		"Chotchkies", "The Frying Dutchman", "Klimpys", "The Krusty Krab", "Monks Diner", "Milliways", "Minuteman Cafe", 
		"Taco Grande", "Tip Top Cafe", "Moes Tavern", "Central Perk", "Chasers"};
	
	private static Random random = new Random();
	
	public static String getRandomFemale() {
		//Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive),
		return female[random.nextInt(female.length)];
	}
	
	public static String getRandomMale() {
		return male[random.nextInt(male.length)];
	}
	
	public static String getRandomCompany() {
		return company[random.nextInt(company.length)].toUpperCase();
	}
	
	
	
}
