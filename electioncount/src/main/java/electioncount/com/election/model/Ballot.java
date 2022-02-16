package electioncount.com.election.model;

import com.election.util.ElectionUtils;

public class Ballot {
	
	private String preference;
	
	private int currentPreference;
	
	private boolean isEliminate;

	public boolean isEliminate() {
		return isEliminate;
	}

	public Ballot(String preference, int currentPreference, boolean isEliminate) {
		this.preference = preference;
		this.currentPreference = currentPreference;
		this.isEliminate = isEliminate;
	}

	public void setEliminate(boolean isEliminate) {
		this.isEliminate = isEliminate;
	}

	public Ballot(String preference, int currentPreference) {
		this.preference = preference;
		this.currentPreference = currentPreference;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public int getCurrentPreference() {
		return currentPreference;
	}

	public void setCurrentPreference(int currentPreference) {
		this.currentPreference = currentPreference;
	}
	
	public String getNextPreference () {
		if (this.preference != null && this.currentPreference <= this.preference.length()  ) {
			int currentPosition = this.currentPreference;
			this.currentPreference = currentPosition +1;
		    return ElectionUtils.getPreferenceDetails().get(this.preference.charAt((currentPosition)-1));
		}
		else
			return null;
	}

}
