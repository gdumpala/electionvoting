package com.election.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import com.election.service.ElectionCounting;
import com.election.serviceImpl.ElectionCountingImpl;
import com.election.util.ElectionUtils;

import electioncount.com.election.model.Ballot;

public class ElectionCountApp {

	public static void main(String[] args) {

		List<Ballot> ballouts = new ArrayList<>();
		String consoleInput;

		Scanner console = new Scanner(System.in);
		try {
			System.out.println("Provide the input file of candidates list participating in election: ");
			
			String filePath = console.nextLine();
			
			//String filePath = "C:\\Users\\dumpa\\OneDrive\\Documents\\learning\\electioncountingapp\\candidateslist.txt";
		
			//set preferences for each candidate
			ElectionUtils.addPreference(filePath);

			while (console.hasNextLine()) {
				consoleInput = console.nextLine();
				if(!consoleInput.isBlank()) {
				if (consoleInput.equalsIgnoreCase("tally")) {
					ElectionCounting election = new ElectionCountingImpl();
					//candidate details ex : A - Whinery Tour
					Set<Entry<Character, String>> preference = ElectionUtils.getPreferenceDetails().entrySet();
					
					election.ballotCount(ballouts, preference);
					break;
				}
				// System.out.println(consoleInput);
				if (ElectionUtils.isVoteValid(consoleInput))
					ballouts.add(new Ballot(consoleInput, 1, false));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		console.close();

	}

}
