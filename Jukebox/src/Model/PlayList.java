/*
 * Authors: Kyle Willson, Aaron Raymer
 * Class: SongCollection
 * Params: NONE. Constructs a SongCollection of Song objects. Also implements TableModel and ListModel.
 * 
 * */

package Model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;

public class PlayList implements ListModel {
	
	private ArrayList<Song> songList;
	private LinkedList<ListDataListener> listDataListeners;
	
	public PlayList() {
		songList = new ArrayList<Song>();
		
		listDataListeners = new LinkedList<ListDataListener>();
		}
	
	

	//Lets our listeners know that something has changed.
	private void changed() {
		for(ListDataListener l : listDataListeners) {
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, songList.size()));
		}
		
	}

	public void add(Song s) {
		songList.add(s);
		changed();
	}

	public void remove(Song s) {
		songList.remove(songList.indexOf(s));
		changed();
	}
	
	@Override
	public int getSize() {
	
		return songList.size();
	}

	@Override
	public Song getElementAt(int index) {
		if(index<0 || index>songList.size())
			return null;
		
		return songList.get(index);
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
