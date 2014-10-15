/*
 * Authors: Kyle Willson, Aaron Raymer
 * Class: SongCollection
 * Params: NONE. Constructs a SongCollection of Song objects. Also implements TableModel for GUI view.
 * 
 * */

package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements TableModel, Serializable {

	private static final long serialVersionUID = -6529535610908686714L;
	private ArrayList<Song> songList;
	private transient LinkedList<TableModelListener> tableModelListeners;
	
	public SongCollection() {
		
		songList = new ArrayList<Song>();
		tableModelListeners = new LinkedList<TableModelListener>();
		songList.add(new Song("Winter in the Desert", "Aaron Raymer", 266, "songfiles/01 Winter in the Desert.mp3"));
		songList.add(new Song("Ta-Da!", "Microsoft", 5, "songfiles/tada.wav"));
		songList.add(new Song("Hey Joe", "Jimi Hendrix", 210, "songfiles/03 Hey Joe.aiff"));
		
		}
	

	public void addSong(Song s) {
		songList.add(s);
		changed();
	}

	//Lets our listeners know that something has changed.
	private void changed() {
		for(TableModelListener l: tableModelListeners) {
			l. tableChanged(new TableModelEvent(this));
		}
	}
	
	
	//FOLLOWING METHODS OVERRIDDEN FROM TABLEMODEL.
	
	@Override
	public int getRowCount() {
		return songList.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0: return "Artist";
		case 1: return "Song";
		case 2: return "Length";
		}
		return "ERROR";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return Integer.class;
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return songList.get(rowIndex).getSongArtist();
		case 1: return songList.get(rowIndex).getSongTitle();
		case 2: return songList.get(rowIndex).getSongLength();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		return;
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		if(tableModelListeners == null)
			tableModelListeners = new LinkedList<TableModelListener>();
		
		tableModelListeners.add(l);
		changed();
		
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		tableModelListeners.remove(l);
		changed();
		
	}


	//returns a song element
	public Song getElementAt(int index) {
		if(index<0 || index>songList.size())
			return null;
		
		return songList.get(index);
	}








}
