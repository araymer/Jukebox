/*
 * Authors: Kyle Willson, Aaron Raymer
 * Class: SongCollection
 * Params: NONE. Constructs a SongCollection of Song objects. Also implements TableModel for GUI view.
 * 
 * */

package Model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements TableModel {
	
	private ArrayList<Song> songList;
	private LinkedList<TableModelListener> tableModelListeners;
	
	public SongCollection() {
		
		songList = new ArrayList<Song>();
		songList.add(new Song("Blue Ridge Mountain Mist", "Jack Jackson", 38, "songfiles/BlueRidgeMountainMist.mp3"));
		songList.add(new Song("Determined Tumbao", "Your Heinous, The Man", 20, "songfiles/DeterminedTumbao.mp3"));
		songList.add(new Song("Flute", "A Flautist", 5, "songfiles/flute.aif"));
		songList.add(new Song("Space Music", "Space", 6, "songfiles/spacemusic.au"));
		songList.add(new Song("Swing Cheese", "Swing Cheese", 15, "songfiles/SwingCheese.mp3"));
		songList.add(new Song("Ta-Da!", "Microsoft", 5, "songfiles/tada.wav"));
		songList.add(new Song("Untameable Fire", "Guitars", 284, "songfiles/UntameableFire.mp3"));
		tableModelListeners = new LinkedList<TableModelListener>();
		
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
