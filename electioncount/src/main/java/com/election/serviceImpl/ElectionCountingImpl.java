package com.election.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map.Entry;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.election.service.ElectionCounting;
import com.election.util.ElectionUtils;

import electioncount.com.election.model.Ballot;
import electioncount.com.election.model.Candidate;

public class ElectionCountingImpl implements ElectionCounting {

	@Override
	public void ballotCount(List<Ballot> ballots, Set<Entry<Character, String>> preference) {

		ConcurrentHashMap<String, Candidate> electionCount = new ConcurrentHashMap<>();

		
		int round = 1;
		boolean isWinnerSelected = false;
		Candidate winner = null;
		Candidate eliminator = null;
		long ballottoCount = ballots.size();
		List<Ballot> voteCount = ballots;

		System.out.println("Total number of  valid ballots : " + ballots.size());

		//assign candidate details for each candidate
		for (Entry<Character, String> val : preference) {
			electionCount.put(val.getValue(),
					new Candidate(val.getValue(), val.getKey(), 0, "active", new ArrayList<Ballot>()));
		}
		
		//if winner not selected yet
		while (!isWinnerSelected) {
			System.out.println(round + " round initiated");
			if (eliminator != null)
				System.out.println(eliminator.getName() + " eliminated");
			String nextPreference = null;
			
			//loop through number of ballots votes available and add to the candidate matched
			for (int i = 0; i < voteCount.size(); i++) {
				nextPreference = voteCount.get(i).getNextPreference();
				if (nextPreference != null && electionCount.containsKey(nextPreference)) {
					electionCount.get(nextPreference).addBallout(ballots.get(i));
				} else {
					ballots.get(i).setEliminate(true);
					ballottoCount--;
				}
			}

			List<Candidate> candidateQuotaAllocation = electionCount.values().stream()
					.sorted((c1, c2) -> Long.compare(c2.getVotes(), c1.getVotes())).collect(Collectors.toList());
			long votesTowin = (ballottoCount / 2) + 1;
			System.out.println("The quota Required to win : " + votesTowin);

			for (Candidate candidate : candidateQuotaAllocation) {
				System.out.println(candidate.getName() + " got " + candidate.getVotes() + " votes");
				if (candidate != null && candidate.getVotes() >= votesTowin) {
					winner = candidate;
				}
			}
			System.out.println(round + " round Ended");

			if (winner != null) {
				isWinnerSelected = true;
				System.out.println("Eleceted winner is " + winner.getName());
			} else {
				eliminator = candidateQuotaAllocation.get(candidateQuotaAllocation.size() - 1);
				round = round + 1;
				electionCount.remove(eliminator.getName());
				voteCount = eliminator.getBallouts();
			}
		}
	}

}
