/*
 * Authors: Aaron Raymer, Kyle Willson
 * Class: Playlist. Playlist tracks the current selected songs in order and implements ListModel for GUI view.
 * 
 */

package Model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Playlist implements ListModel {
	
	private ArrayList<Song> playlist;
	private LinkedList<ListDataListener> listDataListeners;
	
	
	public Playlist(){
		playlist = new ArrayList<Song>();
		listDataListeners = new LinkedList<ListDataListener>();
	}
	
	//Add a song to the playlist
	public void addSong(Song s){
		playlist.add(s);
		changed();
	}
	//remove a song from playlist (only after song has played)
	public void remove(){
		playlist.remove(0);
		changed();
	}
	//returns fileName of the song currently ready to be played (to send to player)
	public String getFileName(){
		return playlist.get(0).getFileName();
	}
	//Alerts listeners that a song has been added or removed.
	private void changed() {
	
		for(ListDataListener l : listDataListeners) {
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, playlist.size()));
		
		}
	}
	
	//FOLLOWING METHODS OVERRIDDEN FOR LISTMODEL
	
	@Override
	public int getSize() {
		return playlist.size();
	}
	@Override
	public Song getElementAt(int index) {
		if(index>=playlist.size() || index<0)
			return null;
		return playlist.get(index);
	}
	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);
		
	}
	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);
		
	}
	
}
