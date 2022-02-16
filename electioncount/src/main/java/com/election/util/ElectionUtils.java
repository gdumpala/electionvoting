package com.election.util;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.ConcurrentHashMap;

public class ElectionUtils {

	private static ConcurrentHashMap<Character, String> preferencesDetails = null;

	public static void addPreference(String filePath) throws IOException {

		FileReader fileReader = new FileReader(filePath);
		
		try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String line;
			preferencesDetails = new ConcurrentHashMap<Character, String>();
			char key = 'A';
			while ((line = bufferedReader.readLine()) != null) {
				preferencesDetails.put(key, line);
				System.out.println(key + ". " + line);
				key++;
			}

		}

	}

	public static ConcurrentHashMap<Character, String> getPreferenceDetails() {

		return preferencesDetails;
	}

	public static boolean isVoteValid(String vote) {

		for (int i = 0; i < vote.length(); i++) {
			if (preferencesDetails != null && !preferencesDetails.containsKey(vote.charAt(i)))
				return false;

		}
		return true;
	}

}
