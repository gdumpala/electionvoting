package electioncount.com.election.model;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

	private String name;
	private Character shortName;
	private long votes;
	private String status;
	private List<Ballot> ballouts;

	public Candidate(String name, Character shortName, long votes, String status, List<Ballot> ballouts) {
		this.name = name;
		this.shortName = shortName;
		this.votes = votes;
		this.status = status;
		this.ballouts = ballouts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getShortName() {
		return shortName;
	}

	public void setShortName(Character shortName) {
		this.shortName = shortName;
	}

	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void addVotes() {

		this.votes = votes + 1;
	}

	public List<Ballot> getBallouts() {
		return ballouts;
	}

	public void setBallouts(List<Ballot> ballouts) {
		this.ballouts = ballouts;
	}

	public void addBallout(Ballot ballout) {
		if (this.ballouts != null) {
			this.ballouts.add(ballout);
			this.votes = votes + 1;
		} else {
			this.ballouts = new ArrayList<>();
			this.ballouts.add(ballout);
			this.votes = votes + 1;
		}
	}

	@Override
	public int hashCode() {
		final int temp = 14;
		int ans = 1;
		ans = temp * ans + name.hashCode();
		return ans;
	}

	@Override
	public boolean equals(Object otherCandidate) {
		if (this == otherCandidate) {
			return true;
		}
		if (otherCandidate == null) {
			return false;
		}
		if (this.getClass() != otherCandidate.getClass()) {
			return false;
		}
		Candidate candidate = (Candidate) otherCandidate;
		if (!this.name.equalsIgnoreCase(candidate.getName())) {
			return false;
		}
		return true;
	}

}
