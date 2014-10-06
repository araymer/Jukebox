/*
 * Authors: Kyle Willson, Aaron Raymer
 * Class: JukeboxAccount
 * Params: String id, password. Constructs a JukeboxAccount with 1500 minutes/90000 seconds of playtime and a unique ID
 * 
 * */
 

package Model;

import java.util.GregorianCalendar;


public class JukeboxAccount {
	private String id;
	private String password;
	private int dailyPlayCount;
	private GregorianCalendar dateLastPlayed;
	private double secondsLeft;
	public JukeboxAccount(String id1, String password1){
		id = id1;
		password = password1;
		dailyPlayCount = 0;
		secondsLeft = 90000;
	}
	
	public void setDateLastPlayed(Date date){ //sets the date last played from JukeBox
		dateLastPlayed = new GregorianCalendar();
	}
	public void addAccountPlays(){			//PlayCounter
		if(dateLastPlayed.compareTo(new GregorianCalendar()) == 0 ){  //if the day last plated
			
			dailyPlayCount++;
		}
		else{
			dailyPlayCount = 1;
		}
	}
	
	public boolean canSelect(int songLength) {
		boolean canPlay = (secondsLeft >= songLength && dailyPlayCount < 2);
		if(canPlay)
			deductTime(songLength);
		
		return canPlay;
	
	}
	private void deductTime(int n) {
		secondsLeft -= n;
		
	}
}
