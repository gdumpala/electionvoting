package electioncount;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.election.service.ElectionCounting;
import com.election.serviceImpl.ElectionCountingImpl;
import com.election.util.ElectionUtils;

import electioncount.com.election.model.Ballot;

public class electionCountingTest {

	ClassLoader classLoader = this.getClass().getClassLoader();

	@Before
	public void setUp() throws IOException {
		ElectionUtils.addPreference(classLoader.getResource("candidateslist.txt").getFile());
	}


	@Test
	public void testInputFile() {
		File file = new File(classLoader.getResource("candidateslist.txt").getFile());
		assertTrue(file.exists());
	}

		@Test
	public void isValidVote() {
		assertTrue(ElectionUtils.isVoteValid("A"));
	}

	@Test
	public void testElectionCounting() {
		List<Ballot> ballouts = new ArrayList<>();
		ballouts.add(new Ballot("ABDC", 1, false));
		ballouts.add(new Ballot("BAD", 1, false));
		ballouts.add(new Ballot("CABD", 1, false));
		ballouts.add(new Ballot("DA", 1, false));
		ElectionCounting election = new ElectionCountingImpl();
		
		// candidate details ex : A - Whinery Tour
		Set<Entry<Character, String>> preference = ElectionUtils.getPreferenceDetails().entrySet();

		election.ballotCount(ballouts, preference);
	}

}
