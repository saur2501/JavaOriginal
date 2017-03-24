package designPatterns.derek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

//Will hold all of the info needed for each song

//I told all users to:
//1. create a function named addSong() for adding song, band and release
//2. create a function named getBestSongs() that will return the collection of songs

class SongInfo{
	
	String songName; 
	String bandName; 
	int yearReleased;
	
	public SongInfo(String newSongName, String newBandName, int newYearReleased){
		
		songName = newSongName;
		bandName = newBandName;
		yearReleased = newYearReleased;
		
	}
	
	public String getSongName(){ return songName; }
	public String getBandName(){ return bandName; }
	public int getYearReleased(){ return yearReleased; }
	
}

interface SongIterator {
	
	public Iterator createIterator();
	
}

class SongsOfThe70s implements SongIterator{
	
	// ArrayList holds SongInfo objects
	
	ArrayList<SongInfo> bestSongs;
	
	public SongsOfThe70s() {
		
		bestSongs = new ArrayList<SongInfo>();
		
		addSong("Imagine", "John Lennon", 1971);
		addSong("American Pie", "Don McLean", 1971);
		addSong("I Will Survive", "Gloria Gaynor", 1979);
		
	}
	
	// Add a SongInfo object to the end of the ArrayList
	
	public void addSong(String songName, String bandName, int yearReleased){
		
		SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
		
		bestSongs.add(songInfo);
		
	}
	
	
	// Get rid of this
	// Return the ArrayList filled with SongInfo Objects
	
	public ArrayList<SongInfo> getBestSongs(){
		
		return bestSongs;
		
	}

	// NEW By adding this method I'll be able to treat all
	// collections the same
	
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return bestSongs.iterator();
	}
	
}

class SongsOfThe80s implements SongIterator{
	
	// Create an array of SongInfo Objects
	
	SongInfo[] bestSongs;
	
	// Used to increment to the next position in the array
	
	int arrayValue = 0;
	
	public SongsOfThe80s() {
		
		bestSongs = new SongInfo[3];
		
		addSong("Roam", "B 52s", 1989);
		addSong("Cruel Summer", "Bananarama", 1984);
		addSong("Head Over Heels", "Tears For Fears", 1985);
		
	}
	
	// Add a SongInfo Object to the array and increment to the next position
	
	public void addSong(String songName, String bandName, int yearReleased){
		
		SongInfo song = new SongInfo(songName, bandName, yearReleased);
			
		bestSongs[arrayValue] = song;
		
		arrayValue++;
		
	}
	
	// This is replaced by the Iterator
	
	public SongInfo[] getBestSongs(){
		
		return bestSongs;
		
	}

	// NEW By adding this method I'll be able to treat all
	// collections the same
		
	@Override
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return Arrays.asList(bestSongs).iterator();	
	}
	
}

class SongsOfThe90s implements SongIterator{
	
	// Create a Hashtable with an int as a key and SongInfo Objects 
	
	Hashtable<Integer, SongInfo> bestSongs = new Hashtable<Integer, SongInfo>();
	
	// Will increment the Hashtable key
	
	int hashKey = 0;
	
	public SongsOfThe90s() {
		
		addSong("Losing My Religion", "REM", 1991);
		addSong("Creep", "Radiohead", 1993);
		addSong("Walk on the Ocean", "Toad The Wet Sprocket", 1991);
		
	}
	
	// Add a new SongInfo Object to the Hashtable and then increment the Hashtable key
	
	public void addSong(String songName, String bandName, int yearReleased){
		
		SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
		
		bestSongs.put(hashKey, songInfo);
		
		hashKey++;
			
	}
	
	// This is replaced by the Iterator; Return a Hashtable full of SongInfo Objects
	
	public Hashtable<Integer, SongInfo> getBestSongs(){
		
		return bestSongs;
		
	}
	
	public Iterator createIterator() {					// NEW By adding this method I'll be able to treat all collections the same
		return bestSongs.values().iterator();
	}
	
}

class DJ {
	
	SongsOfThe70s songs70s;
	SongsOfThe80s songs80s;
	SongsOfThe90s songs90s;
	
	// NEW Passing in song iterators
	
	SongIterator iter70sSongs;
	SongIterator iter80sSongs;
	SongIterator iter90sSongs;
	
	/* OLD WAY
	public DJ(SongsOfThe70s newSongs70s, SongsOfThe80s newSongs80s, SongsOfThe90s newSongs90s) {
		
		songs70s = newSongs70s;
		songs80s = newSongs80s;
		songs90s = newSongs90s;
		
	}
	*/
	
	// NEW WAY Initialize the iterators	
	
	public DJ(SongIterator newSongs70s, SongIterator newSongs80s, SongIterator newSongs90s) {
		
		iter70sSongs = newSongs70s;
		iter80sSongs = newSongs80s;
		iter90sSongs = newSongs90s;
		
	}
	
	public void showTheSongs(){
		
		// Because the SongInfo Objects are stored in different collections everything must be handled on an individual basis. This is BAD!
		
		ArrayList aL70sSongs = songs70s.getBestSongs();
		
		System.out.println("Songs of the 70s\n");
		
		for(int i=0; i < aL70sSongs.size(); i++){
			
			SongInfo bestSongs = (SongInfo) aL70sSongs.get(i);
			
			System.out.println(bestSongs.getSongName());
			System.out.println(bestSongs.getBandName());
			System.out.println(bestSongs.getYearReleased() + "\n");
			
		}
		
		SongInfo[] array80sSongs = songs80s.getBestSongs();
		
		System.out.println("Songs of the 80s\n");
		
		for(int j=0; j < array80sSongs.length; j++){
			
			SongInfo bestSongs = array80sSongs[j];
			
			System.out.println(bestSongs.getSongName());
			System.out.println(bestSongs.getBandName());
			System.out.println(bestSongs.getYearReleased() + "\n");
			
		}
		
		Hashtable<Integer, SongInfo> ht90sSongs = songs90s.getBestSongs();
		
		System.out.println("Songs of the 90s\n");
		
		for (Enumeration<Integer> e = ht90sSongs.keys(); e.hasMoreElements();)
	    {
			SongInfo bestSongs = ht90sSongs.get(e.nextElement());
			
			System.out.println(bestSongs.getSongName());
			System.out.println(bestSongs.getBandName());
			System.out.println(bestSongs.getYearReleased() + "\n");
			
	    }
		
	}
	
	// Now that I can treat everything as an Iterator it cleans up the code while allowing me to treat all collections as 1
	
	public void showTheSongs2(){
		
		System.out.println("NEW WAY WITH ITERATOR\n");
		
		Iterator Songs70s = iter70sSongs.createIterator();
		Iterator Songs80s = iter80sSongs.createIterator();
		Iterator Songs90s = iter90sSongs.createIterator();
		
		System.out.println("Songs of the 70s\n");
		printTheSongs(Songs70s);
		
		System.out.println("Songs of the 80s\n");
		printTheSongs(Songs80s);
		
		System.out.println("Songs of the 90s\n");
		printTheSongs(Songs90s);
		
	}
	
	public void printTheSongs(Iterator iterator){
		
		while(iterator.hasNext()){
			
			SongInfo songInfo = (SongInfo) iterator.next();
			
			System.out.println(songInfo.getSongName());
			System.out.println(songInfo.getBandName());
			System.out.println(songInfo.getYearReleased() + "\n");
			
		}
		
	}
	
}

public class IteratorPattern {
	public static void main(String[] args){ 
	    SongsOfThe70s songs70s = new SongsOfThe70s();
	    SongsOfThe80s songs80s = new SongsOfThe80s();
	    SongsOfThe90s songs90s = new SongsOfThe90s();     
	    DJ madMike = new DJ(songs70s, songs80s, songs90s);     
	    // madMike.showTheSongs();     
	    madMike.showTheSongs2();
	}

}
