/*
 * Authors: Aaron Raymer, Kyle Willson
 * Class: GUI. Our Jukebox GUI. displays the tablemodel from SongCollection and ListModel from Playlist
 * and an "Add" button. 
 */

package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import songplayer.SongPlayer;
import Controller.Jukebox;
import Model.Playlist;
import Model.Song;
import Model.SongCollection;

public class JukeboxGUI extends JFrame {

	public static void main(String[] args) {
		
		new JukeboxGUI().setVisible(true);

	}


	private JList<Song> songList;
	private JTable jTable;
	private JButton addButton;
	private SongCollection songCollection;
	private Playlist playList;
	private SongPlayer player;
	public Jukebox theBox;
	



	public JukeboxGUI() {
		theBox = new Jukebox();
		playList = theBox.returnList();
		songCollection = new SongCollection();
		player = new SongPlayer();
		jTable = new JTable(songCollection);
		jTable.setRowSorter(new TableRowSorter<TableModel>(jTable.getModel()));
		
		registerObservers();
		
		this.setLayout(new GridLayout(1, 2));
		
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(new JScrollPane(jTable), BorderLayout.CENTER);
		//Gets the Top Panel for the label
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Catalog"));
		left.add((topPanel), BorderLayout.NORTH);
		// add button
		JButton add = new JButton("Add Song");
		add.addActionListener(new AddSongButtonListener());
		left.add(add, BorderLayout.SOUTH);
		this.add(left);

		
		// right side
		
		
		songList = new JList<Song>(playList);
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());



		// centered label in the north of right side
		JPanel north = new JPanel();
		north.add(new JLabel("Playlist"));
		right.add(new JScrollPane(songList), BorderLayout.CENTER); // add the list
																// to the center
																// of the right
																// side
		right.add(north, BorderLayout.NORTH);

		this.add(right);

		// frame setup
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}


	
	private void registerObservers() {
		playList.addObserver(theBox);	
	}



	private class AddSongButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			Song temp = songCollection.getElementAt(jTable.getSelectedRow());
			
		
			String file = temp.getFileName();
			
			if(file != null) 
				playList.addSong(temp);
		
			}
		}
	}




