/*
 * Authors: Kyle Willson, Aaron Raymer
 * Class: JukeboxAccount
 * Params: String id, password. Constructs a JukeboxAccount with 1500 minutes/90000 seconds of playtime and a unique ID
 * 
 * */
 

package Model;

import java.io.Serializable;
import java.util.GregorianCalendar;


public class JukeboxAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3545713454260034655L;
	private String id;
	private String password;
	private int dailyPlayCount;
	public GregorianCalendar dateLastPlayed;
	private double secondsLeft;
	
	public JukeboxAccount(String id1, String password1){
		id = id1;
		password = password1;
		dailyPlayCount = 0;
		secondsLeft = 90000;
	}
	//This sets the date to today's date. This will be called when a song is played. 
	public void setDateLastPlayed(){ //sets the date last played from JukeBox
		dateLastPlayed = new GregorianCalendar();
	}
	//Either increment dailyPlayCount (if last played is today) else reset back to 1.
	public void addAccountPlays() {		
		
		if(dateLastPlayed.compareTo(new GregorianCalendar()) == 0 ){  //if the day last plated
			
			dailyPlayCount++;
		}
		else{
			dailyPlayCount = 1;
		}
		
	}
	//See if account can play a specific song. 
	public boolean canSelect(int songLength) {
		
		boolean canPlay = (secondsLeft >= songLength && dailyPlayCount < 2);
		if(canPlay)
			deductTime(songLength);
		 
		return canPlay;
	
	}
	
	//Adjust student time when song is successfully selected. 'n' is songLength in seconds.
	public void deductTime(int n) {
		secondsLeft -= n;
		
	}
	
	//getters for variables (mostly for coverage)
		public String getID(){
			return id;
		}
		public String getPassword(){
			return password;
		}
		public double getTimeRemaining(){
			return secondsLeft;
		}
		
		public GregorianCalendar getDateLastPlayed(){
			return dateLastPlayed;
		}
		//checks only if account has reached daily limit
		public boolean canPlay(){
			if(dailyPlayCount <= 2){
				return true;
			}
			return false;
		}
}
