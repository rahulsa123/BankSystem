package com.icic.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateUtils {
public static java.util.Date acceptDate(){
	java.util.Date d= null;
	Scanner sc = new Scanner(System.in);
	SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
	String s = sc.nextLine();
	try {
		d =sdf.parse(s);
	}catch(ParseException e) {
		System.out.println("Invalid date Formate "+e);
	}
	sc.close();
	return d;
}

public static java.util.Date convertDate(String s){
	java.util.Date d= null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	try {
		d=sdf.parse(s);
	}catch(ParseException p) {
		System.out.println("Invalid date Formate "+p);
	}
	return d;
}



}
