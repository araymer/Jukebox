package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Model.PlayList;
import Model.Song;
import Model.SongCollection;

public class GUI extends JFrame {

	public static void main(String[] args) {
		new GUI().setVisible(true);

	}

	private JList<Song> songList;
	private JTable jTable;
	private JButton addButton;
	private SongCollection songCollection;
	private PlayList playList;
	private LinkedList<ListDataListener> listDataListeners;
	
	//private Player player;

	public GUI() {
		listDataListeners = new LinkedList<ListDataListener>();
		playList = new PlayList();
		songCollection = new SongCollection();
		//player = new Player();
		jTable = new JTable(songCollection);
		jTable.setRowSorter(new TableRowSorter<TableModel>(jTable.getModel()));
		
		this.setLayout(new GridLayout(1, 2));
		
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(new JScrollPane(jTable), BorderLayout.CENTER);
		//Gets the Top Panel for the label
		JPanel topPanel = new JPanel();
		new JLabel("Songs List");
		topPanel.add(new JLabel("Catalog"));
		left.add((topPanel), BorderLayout.NORTH);
		// add button
		JButton add = new JButton("Add");
		add.addActionListener(new AddSongListener());
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

	private class AddSongListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			Song temp = songCollection.getElementAt(jTable.getSelectedRow());
			
			playList.add(temp);
			
			
			}
	}
//
//	@Override
//	public Song getElementAt(int index) {
//		return playListDisplay.get(index);
//	}
//



}