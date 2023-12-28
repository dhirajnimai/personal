package com.tollplaza.Tollplaza.serviceImpl;

import java.util.Random;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class Utility {
	public String username(String subscribeType, String bankType) {

		Random randomNumber = new Random(System.currentTimeMillis());
		DateTime dt = new DateTime();  // current time
		//int month = dt.dayOfMonth();     // gets the current month
	
		int seconds=dt.getSecondOfMinute();
		System.out.println("================Seconds:"+seconds);
		int number = randomNumber.nextInt(90) + 100;
		Random random = new Random(System.currentTimeMillis());
		
		String snumber = String.valueOf(number);
		String userID = "";
		String initials = null;

				initials = subscribeType.substring(0, 2);
			userID = initials.toUpperCase().concat(String.valueOf(seconds)).concat(snumber);
			
		
	
		return userID.replaceAll("\\s", "");
	}
}
