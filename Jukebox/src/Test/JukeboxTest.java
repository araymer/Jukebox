package Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.Test;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;
import Controller.Jukebox;
import Model.CardReader;
import Model.JukeboxAccount;
import Model.Playlist;
import Model.Song;
import Model.SongCollection;

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
		assertTrue(allStar.canSelect());
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
	

	@Test
	public void testJukeBoxAccountTimeLeft(){
		JukeboxAccount temp = new JukeboxAccount("Kyle", "Willson");
		
		temp.deductTime(1500);
		assertEquals(temp.getTimeRemaining(), 88500, 0.00001);
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
		temp.dateLastPlayed = new GregorianCalendar(1970, 0, 1);
		temp.addAccountPlays();
		assertTrue(temp.canPlay());
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

		assertEquals("Alli", cR.getAccount("Alli").getID());
	}

	/*
	
	SongCollection
	
	*/
	
	@Test
	public void testSongCollection(){
		SongCollection testColl = new SongCollection();
		
		assertEquals(testColl.getRowCount(), 7);
		
		assertEquals(testColl.getElementAt(0).getFileName(), "songfiles/BlueRidgeMountainMist.mp3");
		
		assertEquals(testColl.getColumnCount(), 3);
		
		assertEquals(testColl.getColumnName(0), "Artist");
		assertEquals(testColl.getColumnName(1), "Song");
		assertEquals(testColl.getColumnName(2), "Length");
		assertEquals(testColl.getColumnName(3), "ERROR");
		
		
		assertEquals(testColl.getColumnClass(0), String.class);
		assertEquals(testColl.getColumnClass(1), String.class);
		assertEquals(testColl.getColumnClass(2), Integer.class);
		assertEquals(testColl.getColumnClass(3), null);
		
		for(int i = 0; i<testColl.getRowCount(); i++) {
			for(int k = 0; k<testColl.getColumnCount(); k++) {
		
		assertEquals(testColl.isCellEditable(i,k), false);
			}
		}
		for(int i = 0; i<testColl.getRowCount(); i++) {
		
		assertEquals(testColl.getValueAt(i,0), testColl.getElementAt(i).getSongArtist());
		}
		for(int i = 0; i<testColl.getRowCount(); i++) {
			
			assertEquals(testColl.getValueAt(i,1), testColl.getElementAt(i).getSongTitle());
		}
		for(int i = 0; i<testColl.getRowCount(); i++) {
			
			assertEquals(testColl.getValueAt(i,2), testColl.getElementAt(i).getSongLength());
		}
		
		assertEquals(testColl.getValueAt(4,4), null);
		
		testColl.setValueAt(new Jukebox(), 0, 0);
		
	
		
		assertEquals(testColl.getElementAt(8), null);
		assertEquals(testColl.getElementAt(-1), null);
		
		
	}
	
	@Test
	public void testPlayer() {
		SongPlayer whatever = new SongPlayer();
		Song song = new Song("Fly Like an Eagle", "Steve Miller Band", 228, "Fly Like an Eagle.mp3");
		whatever.playFile(song.getFileName());
	
	}
	
	@Test
	public void testJukebox() {
		Jukebox whatever = new Jukebox();
		Playlist list = whatever.returnList();
	
		whatever.queueSong(new Song("","",1,""));
		
		
	}
}
