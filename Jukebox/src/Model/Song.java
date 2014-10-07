/*
Authors: Aaron Raymer, Kyle Willson
Class: Song. Instantiates a song object with information relevant to display and play the file
Params: String Title, String Artist, int length(in seconds), String file
*/
package Model;

import java.util.GregorianCalendar;



public class Song {
	
	private String songTitle;
	private String songArtist;
	private int songLength;
	private String fileName;
	private int numberOfPlays;
	private GregorianCalendar dateLastPlayed;
	
	
	public Song(String title, String art, int length, String file) {
		
		songTitle = title;
		songArtist = art;
		songLength = length;
		numberOfPlays = 0;
		fileName = file;
		dateLastPlayed = new GregorianCalendar();
	}
	
	//getter for songTitle
	public String getSongTitle(){
		
		return songTitle;
	}
	//getter for songArtist
	public String getSongArtist(){
		return songArtist;
	}
	//getter for songLength
	public double getSongLength(){
		return songLength;
	}
	
	//getter for fileName
	public String getFileName(){
		return fileName;
	}
	//either increments numberOfPlays or sets to 1 based on Date. Called from Jukebox controller
	public void setSongPlays(){
		if(dateLastPlayed.compareTo(new GregorianCalendar()) == 0) {
		numberOfPlays++;
		
		}
		else
			numberOfPlays = 1;
		
		dateLastPlayed = new GregorianCalendar();
		
		}
	
	
	//Checks eligibility of song based on number of plays today.
	public boolean canSelect(){
		return (numberOfPlays < 5);
	}
	
	
	//toString outputs only artist and title info.
	@Override
	public String toString() {
		return (getSongArtist() + " - " + getSongTitle());
	}
	
}
