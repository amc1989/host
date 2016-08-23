package com.axon.icloud.host;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**

 */
public class AppTest {
	public static void main(String[] args) throws ParseException {
	//	jisuan();
		float liWang =(1-(float)3/4);
		System.out.println(liWang);
	}
    static  void jisuan() throws ParseException{
		String opendate = "2016-5-1";
		String end = "2016-5-31";
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date  start = df.parse(opendate);
    	Date end1 = df.parse(end);
    	long star = start.getTime();
    	long end11 = end1.getTime();
    	long cha = end11-star;
    	int day = (int) (cha/(1000*60*60*24));
    	System.out.println(day);
    }
	static int GetDaysInMonth(int year, int month) {
		int d;
		int day[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (2 == month) {
			d = (((0 == year % 4) && (0 != year % 100) || (0 == year % 400)) ? 29
					: 28);
		} else {
			d = day[month - 1];
		}
		return d;
	}

	public int getScore(int key, float[] scope) {
		int low = 0;
		int high = scope.length - 1;
		while (low < high) {
			int middle = (low + high) / 2;
			if (key < scope[middle]) {
				high = middle - 1;
				if(high-middle==1){
					return (int) ((middle + 1) / 10f * 20);
				}
			} else if (key > scope[middle]) {
				low = middle + 1;
				if(high-middle==1){
					return (int) ((middle + 1) / 10f * 20);
				}
			} else {
				return middle;
			}
		}
		return 20;

	}
}
