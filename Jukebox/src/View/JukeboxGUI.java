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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import songplayer.SongPlayer;
import Controller.Jukebox;
import Model.CardReader;
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
	private CardReader saveAcct;




	public JukeboxGUI() {
		
		
		theBox = new Jukebox();
		playList = theBox.returnList();
		player = new SongPlayer();
		
		//Load Data Option:
		int answer = JOptionPane.showConfirmDialog(null, "Load Data?", "No one loves you", JOptionPane.YES_NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION || !loadData()) {
			songCollection = new SongCollection();
			saveAcct = new CardReader();
			
		}
		
		this.addWindowListener(new SaveDataListener());

	
		
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
			Song temp = (songCollection).getElementAt(jTable.getSelectedRow());
			
		
			String file = temp.getFileName();
			
			if(file != null) 
				playList.addSong(temp);
		
			}
		}
	
	
	@SuppressWarnings("unchecked")
	public boolean loadData() {

		try {
		FileInputStream inStream = new FileInputStream(new File("songcoll.dat"));
		ObjectInputStream inObject = new ObjectInputStream(inStream);
		songCollection = (SongCollection) inObject.readObject();
		
		FileInputStream inStream2 = new FileInputStream(new File("saveacct.dat"));
		ObjectInputStream inObject2 = new ObjectInputStream(inStream2);
		saveAcct = (CardReader) inObject2.readObject();
		
		inObject.close();
		inObject2.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problem loading data. Program will re-initialize.", "Unable to load data", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void saveData() {

			try {
				
			FileOutputStream outStream = new FileOutputStream(new File("songcoll.dat"));
			ObjectOutputStream outObject = new ObjectOutputStream(outStream);
			FileOutputStream outStream2 = new FileOutputStream(new File("saveacct.dat"));
			ObjectOutputStream outObject2 = new ObjectOutputStream(outStream2);
			outObject.writeObject(songCollection);
			outObject2.writeObject(saveAcct);
			outObject.close();
			outObject2.close();
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
	
		}
	
	
	private class SaveDataListener implements WindowListener {
		// TODO 4 implement SaveDataListener
		@Override
		public void windowClosing(WindowEvent arg0) {
			int answer = JOptionPane.showConfirmDialog(null, "Save Data?", "Would you like to save the data?", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				saveData();
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	

	}
	
}


