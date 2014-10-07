package Model;

import java.util.PriorityQueue;

public class Playlist {
	private PriorityQueue<String> playlist;
	public Playlist(){
		playlist = new PriorityQueue<String>();
	}
	public void addFileName(String fileName1){
		playlist.add(fileName1);
	}
	public void removeFileName(String fileName1){
		playlist.remove();
	}
	public String getFileName(){
		return playlist.peek();
	}
	
}
