package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;
import Model.JukeboxAccount;
import Model.Playlist;
import Model.Song;

public class Jukebox implements Observer{
	
	public Playlist songList;
	private JukeboxAccount user;
	private SongPlayer player;

	public Jukebox() {
	songList = new Playlist();
	player = new SongPlayer();
	}

	public Playlist returnList() {
		return songList;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		Song temp = (Song) arg;
		String file = temp.getFileName();
		
		if(file != null) {
			player.playFile(new SongListener(), file);
		}
		else {
			JOptionPane oops = new JOptionPane();
			oops.showMessageDialog(null,"This song has reached its maximum number of plays for today.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

	}
	
	private class SongListener implements EndOfSongListener {


		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
			songList.remove();
			
		}
		
	}

	public void queueSong(Song b) {
		
	songList.addSong(b);
		
	}

	

}
