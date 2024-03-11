package com.nt.util;

import java.security.SecureRandom;
import java.util.Random;

public class PwdUtils {

	public static String generateRandomPassword() {
		
		        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
		        String digitChars = "0123456789";
		        String specialChars = "!@#$%^&*()_+";
                int length=12;
		        StringBuilder password = new StringBuilder();
		        Random random = new Random();

		        for (int i = 0; i < length; i++) {
		            int choice = random.nextInt(4);
		            switch (choice) {
		                case 0:
		                    password.append(upperCaseChars.charAt(random.nextInt(upperCaseChars.length())));
		                    break;
		                case 1:
		                    password.append(lowerCaseChars.charAt(random.nextInt(lowerCaseChars.length())));
		                    break;
		                case 2:
		                    password.append(digitChars.charAt(random.nextInt(digitChars.length())));
		                    break;
		                case 3:
		                    password.append(specialChars.charAt(random.nextInt(specialChars.length())));
		                    break;
		            }
		        }

		        return password.toString();
		    }
		}

