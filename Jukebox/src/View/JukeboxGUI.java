/*
 * Authors: Aaron Raymer, Kyle Willson
 * Class: GUI. Our Jukebox GUI. displays the tablemodel from SongCollection and ListModel from Playlist
 * and an "Add" button. 
 */

package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import songplayer.SongPlayer;
import Controller.Jukebox;
import Model.CardReader;
import Model.JukeboxAccount;
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
	private JTextField userName;
	private JPasswordField password;
	private JLabel userPlays;
	private JLabel userMin;
	private JLabel userDisp;
	private JButton btnLogin;
	private SongCollection songCollection;
	private Playlist playList;
	private SongPlayer player;
	public Jukebox theBox;
	private CardReader saveAcct;
	private LoginWindow loginPanel;
	private boolean loginSuccess;
	private JukeboxAccount user;




	public JukeboxGUI() {
		
		loginSuccess = false;
		theBox = new Jukebox();
		loginPanel = new LoginWindow(theBox);
		playList = theBox.returnList();
		player = new SongPlayer();
		
		//Load Data Option:
		int answer = JOptionPane.showConfirmDialog(null, "Load Data?", "No one loves you", JOptionPane.YES_NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION || !loadData()) {
			songCollection = new SongCollection();
			saveAcct = new CardReader();
			
		}
		
		//Listener for window close to save data
		this.addWindowListener(new SaveDataListener());

	
		
		jTable = new JTable(songCollection);
		jTable.setRowSorter(new TableRowSorter<TableModel>(jTable.getModel()));
		
		registerObservers();
		
		this.setLayout(new GridLayout(1, 3));
		
		//login panel (far left)
		
		userName = new JTextField(20);
		userName.setText("User Name");
		password = new JPasswordField(20);
		password.setText("Password");
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new LoginButtonListener());
		JPanel loginLeft = new JPanel();
		loginLeft.setLayout(new FlowLayout());
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(300,15));
		btnLogin.setPreferredSize(new Dimension(100, 20));
		loginLeft.add(spacer);
		loginLeft.add(userName);
		loginLeft.add(password);
		loginLeft.add(btnLogin);

		
		userPlays = new JLabel();
		userPlays.setText("Plays Left Today:\t");
		userMin = new JLabel();
		userMin.setText("Total Seconds Left:\t");
		userDisp = new JLabel();
		userDisp.setText("Current User:\t");
		
		userPlays.setPreferredSize(new Dimension(250, 15));
		userMin.setPreferredSize(new Dimension(250, 15));
		userDisp.setPreferredSize(new Dimension(250, 15));

		JPanel userInfo = new JPanel();
		userInfo.setPreferredSize(new Dimension(300, 100));
		userInfo.add(userDisp);
		userInfo.add(userPlays);
		userInfo.add(userMin);
		loginLeft.add(userInfo);
	


		//loginLeft.add(loginPanel, BorderLayout.CENTER);
		this.add(loginLeft);
		
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

		 
		// right side (play queue)
		
		
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
		this.setSize(900, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		

	}

	
	
	private void registerObservers() {
		playList.addObserver(theBox);	
	}

	private class AddSongButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(loginSuccess) {
			Song temp = (songCollection).getElementAt(jTable.getSelectedRow());
			
		
			String file = temp.getFileName();
			
			if(file != null) 
				playList.addSong(temp);
		
			}
			else {
				JOptionPane.showMessageDialog(null, "Please LOGIN to play songs");
			}
		}
	}
	
	private class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(Arrays.equals(saveAcct.getAccount(userName.getText().trim()).getPassword().toCharArray(),password.getPassword())) {
				user = saveAcct.getAccount(userName.getText());
				userDisp.setText("Current User:\t\t" + user.getID());
				userMin.setText("Total Seconds Left:\t" + user.getTimeRemaining());
				userPlays.setText("Plays Left Today:\t" + user.getPlays());
				loginSuccess = true;
				
			}
			else {
				System.out.println(password.getPassword());
				System.out.println(saveAcct.getAccount(userName.getText().trim()).getPassword().toCharArray());
				//JOptionPane.showMessageDialog(null, "Username and/or password do not match our records");
			}
		}
	}
	
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


