package Controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Model.Song;
import Model.SongCollection;

public class Jukebox {
	
	private JList<Song> songList;
	private SongCollection songCollection;
	public Jukebox(){
		
	
		
	}
	private class PlaySongListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			
		}
	}


}
