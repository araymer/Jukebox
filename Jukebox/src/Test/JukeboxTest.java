package Test;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.ListModel;
import javax.xml.transform.Templates;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import Model.CardReader;
import Model.JukeboxAccount;
import Model.Playlist;
import Model.Song;
import Model.SongCollection;
import View.JukeboxGUI;

public class JukeboxTest {


	@Test
	public void testAddSong(){
		Playlist playlist = new Playlist();
		Song song = new Song("Fly Like an Eagle", "Steve Miller Band", 228, "Fly Like an Eagle.mp3");
		playlist.addSong(song);
		assertEquals(1, playlist.getSize());
		
	}
	
	/*
	SONG
	
	*/
	@Test
	public void testRemoveSong(){
		Playlist playlist = new Playlist();
		Song song = new Song("Fly Like an Eagle", "Steve Miller Band", 228, "Fly Like an Eagle.mp3");
		playlist.addSong(song);
		playlist.remove();
		assertEquals(0, playlist.getSize());
	}
	
	@Test
	public void testPlaylistIndex(){
		Playlist playlist = new Playlist();
		Song flyEagle = new Song("Fly Like an Eagle", "Steve Miller Band", 228, "Fly Like an Eagle.mp3");
		playlist.addSong(flyEagle);
		Song levels = new Song("Levels", "Avicci", 219, "Levels.mp3");
		playlist.addSong(levels);
		Song loveElevator = new Song("Love in an Elavator", "Aerosmith", 345, "Love in an Elevator.mp3");
		playlist.addSong(loveElevator);
		assertEquals(levels, playlist.getElementAt(1));
		
	}
	
	@Test
	public void testSongName(){
		Song runningToStandStill = new Song("Running to Stand Still", "U2", 307, "Running to Stand Still.mp3");
		assertEquals("Running to Stand Still", runningToStandStill.getSongTitle());
	}
	
	@Test
	public void testSongArtist(){
		Song runningToStandStill = new Song("Running to Stand Still", "U2", 307, "Running to Stand Still.mp3");
		assertEquals("U2", runningToStandStill.getSongArtist());
	}
	
	@Test
	public void testSongLength(){
		Song runningToStandStill = new Song("Running to Stand Still", "U2", 307, "Running to Stand Still.mp3");
		assertEquals(307, runningToStandStill.getSongLength());
	}
	
	@Test
	public void testFileName(){
		Song runningToStandStill = new Song("Running to Stand Still", "U2", 307, "Running to Stand Still.mp3");
		assertEquals("Running to Stand Still.mp3", runningToStandStill.getFileName());
	}
	@Test
	public void testSongPlayCountUnder5() { //found error canSelect was: numPlays < 5 Now: numPlays <= 5
		Song allStar = new Song("All Star", "Smash Mouth", 275, "All Star.mp3");
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		assertTrue(allStar.canSelect());
	}

	@Test
	public void testSongPlayCountOver5() {
		Song allStar = new Song("All Star", "Smash Mouth", 275, "All Star.mp3");
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		allStar.setSongPlays();
		assertFalse(allStar.canSelect());
	}
	
	@Test
	public void testSongToString(){
		Song song = new Song("Fly Like an Eagle", "Steve Miller Band", 228, "Fly Like an Eagle.mp3");
		assertEquals("Steve Miller Band - Fly Like an Eagle", song.toString());
	}
	
	
	
	@Test
	public void testDayReset(){
		
	}
	
	/*
	PLAYLIST
	
	*/
	
	@Test
	public void testGetFileName(){
		Playlist pL = new Playlist();
		ArrayList<Song> playlist = new ArrayList<Song>();
		Song song = new Song("Fly Like an Eagle", "Steve Miller Band", 228, "Fly Like an Eagle.mp3");
		playlist.add(song);
		pL.addSong(song);
		assertEquals("Fly Like an Eagle.mp3", pL.getFileName());
	}
	
	@Test
	public void testPlaylistBelowZero(){
		Playlist pL = new Playlist();
		assertEquals(null, pL.getElementAt(-1));
	}
	
	/*@Test
	public void testPlaylistOutOfBounds(){
		Playlist pL = new Playlist();
		ArrayList<Song> playlist = new ArrayList<Song>();
		assertEquals(null, playlist.size() +3);
	}*/

	@Test
	public void testGetElementAt(){
		Song spaceJam = new Song("Space Jam", "Michael Jordan", 212, "Space Jam.mp3");
		Playlist pL = new Playlist();
		pL.addSong(spaceJam);
		assertEquals(spaceJam, pL.getElementAt(0));
	}
	
	
	/*
	JukeboxAccount
	
	*/
	
	@Test
	public void testIDJukeBoxAccount(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		assertEquals("Kyle", temp.getID());
	}
	
	@Test
	public void testPasswordJukeBoxAccount(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		assertEquals("Willson", temp.getPassword());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testJukeBoxAccountTimeLeft(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.deductTime(1500);
		assertEquals(75000, temp.getTimeRemaining());
	}
	
	
	
	@Test
	public void testDateLastPlayedJukeBoxAccount(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.setDateLastPlayed();
		assertEquals(new GregorianCalendar(), temp.getDateLastPlayed());
	}
	
	@Test
	public void testAddAccountPlaysSameDay(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.setDateLastPlayed();
		temp.addAccountPlays();
		temp.addAccountPlays();
		temp.addAccountPlays();
		assertFalse(temp.canPlay());
	}
	
	@Test
	public void testAddAccountPlaysNotSameDay(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.setDateLastPlayed();
		temp.addAccountPlays();
		assertTrue(temp.canPlay());
	}
	
	@Test
	public void testCanNOTSelectSeconds(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.deductTime(89999);
		temp.canSelect(3);
		assertTrue(temp.canPlay());
		
	}
	
	@Test
	public void testCANSelectSeconds(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.canSelect(3);
		assertTrue(temp.canPlay());
		
	}
	
	@Test
	public void testCanNOTSelectPlayCount(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		temp.setDateLastPlayed();
		temp.addAccountPlays();
		temp.addAccountPlays();
		temp.canSelect(3);
		assertTrue(temp.canPlay());
		
	}
	
	/*
	
	CardReader
	
	*/
	
	
	
	@Test
	public void testCardReader(){
		HashMap<String, JukeboxAccount> accountList = new HashMap<String, JukeboxAccount>();
		accountList.put("Ali", new JukeboxAccount("Ali", "1111"));
		assertEquals("Ali", accountList.get("Ali").getID());
	}
	
	@Test
	public void testCardReaderID(){
		CardReader cR = new CardReader();
		JukeboxAccount kyle = new JukeboxAccount("Kyle", "Willson");
		HashMap<String, JukeboxAccount> accountList = new HashMap<String, JukeboxAccount>();
		accountList.put("key", kyle);
		assertEquals("Kyle", cR.getAccount("key"));
	}
	
	
	
	
	/*
	
	SongCollection
	
	*/
	
	@Test
	public void testSongCollection(){
		SongCollection songCollec = new SongCollection();
		ArrayList<Song> songs = new ArrayList<Song>();
		Song spaceJam = new Song("Space Jam", "Michael Jordan", 212, "Space Jam.mp3");
		songs.add(spaceJam);
		assertEquals(spaceJam.equals(songCollec.getElementAt(0)), 0);
	}
}
