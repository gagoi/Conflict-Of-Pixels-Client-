package fr.cop.server;

public class Profil {

	int wins = 52;

	public Profil() {

	}

	public int getWin() {
		return this.wins;
	}

	public String getFormattedKDA() {
		return ("Kills : " + 2 + "\rDeaths : " + 3);
	}
}
