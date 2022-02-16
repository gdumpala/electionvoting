package com.election.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import electioncount.com.election.model.Ballot;

public interface ElectionCounting {
	
		void ballotCount(List<Ballot> ballots, Set<Entry<Character, String>> preference);

}
