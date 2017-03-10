package designPatterns;
import java.util.ArrayList;
import java.util.Iterator;

abstract class SongComponent {														//This acts as an interface for every Song (Leaf) and SongGroup (Composite) we create														
	//We throw UnsupportedOperationException so that if it doesn't make sense for a song, or song group to inherit a method they can just inherit the default implementation
	public void add(SongComponent newSongComponent) {								//This allows me to add components
		throw new UnsupportedOperationException();	
	}
	public void remove(SongComponent newSongComponent) {							//This allows me to remove components
		throw new UnsupportedOperationException();	
	}		
	public SongComponent getComponent(int componentIndex) {							//This allows me to get components
		throw new UnsupportedOperationException();		
	} 	
	public String getSongName() {													//This allows me to retrieve song names
		throw new UnsupportedOperationException();		
	}
	public String getBandName() {													//This allows me to retrieve band names
		throw new UnsupportedOperationException();			
	}	
	public int getReleaseYear() {													//This allows me to retrieve release year
		throw new UnsupportedOperationException();				
	}
	public void displaySongInfo() {													//When this is called by a class object that extends SongComponent it will print out information specific to the Song or SongGroup
		throw new UnsupportedOperationException();			
	}
}

class SongGroup extends SongComponent {
	ArrayList songComponents = new ArrayList();										//Contains any Songs or SongGroups that are added to this ArrayList
	String groupName;
	String groupDescription;
	public SongGroup(String newGroupName, String newGroupDescription){
		groupName = newGroupName;
		groupDescription = newGroupDescription;	
	}
	public String getGroupName() { return groupName; }
	public String getGroupDescription() { return groupDescription; }
	public void add(SongComponent newSongComponent) {
		songComponents.add(newSongComponent);	
	}
	public void remove(SongComponent newSongComponent) {
		songComponents.remove(newSongComponent);
	}
	public SongComponent getComponent(int componentIndex) {
		return (SongComponent)songComponents.get(componentIndex);	
	}
	public void displaySongInfo(){
		System.out.println(getGroupName() + " " + getGroupDescription() + "\n");
		Iterator songIterator = songComponents.iterator();							//Cycles through and prints any Songs or SongGroups added to this SongGroups ArrayList songComponents
		while(songIterator.hasNext()) { 
			SongComponent songInfo = (SongComponent) songIterator.next();
			songInfo.displaySongInfo();	
		}
	}
}

class Song extends SongComponent {
	String songName;
	String bandName;
	int releaseYear;
	public Song(String newSongName, String newBandName, int newReleaseYear){
		songName = newSongName;
		bandName = newBandName;
		releaseYear = newReleaseYear;	
	}
	public String getSongName() { return songName; }
	public String getBandName() { return bandName; }
	public int getReleaseYear() { return releaseYear; }
	public void displaySongInfo(){
		System.out.println(getSongName() + " was recorded by " +
				getBandName() + " in " + getReleaseYear());	
	}	
}

class DiscJockey{
	SongComponent songList;	
	public DiscJockey(SongComponent newSongList){							//newSongList contains every Song, SongGroup, and any Songs saved in SongGroups
		songList = newSongList;		
	}
	public void getSongList(){												//Calls the displaySongInfo() on every Song or SongGroup stored in songList
		songList.displaySongInfo();	
	}	
}

public class CompositePattern{	
	public static void main(String[] args){		
		SongComponent industrialMusic = 
				new SongGroup("Industrial", 
						"is a style of experimental music that draws on transgressive and provocative themes");
		SongComponent heavyMetalMusic = 
				new SongGroup("\nHeavy Metal", 
						"is a genre of rock that developed in the late 1960s, largely in the UK and in the US");
		SongComponent dubstepMusic = 
				new SongGroup("\nDubstep", 
						"is a genre of electronic dance music that originated in South London, England");
		SongComponent everySong = new SongGroup("Song List", "Every Song Available");			//Top level component that holds everything
		everySong.add(industrialMusic);															//Composite that holds individual groups of songs. This holds Songs plus a SongGroup with Songs
		 industrialMusic.add(new Song("Head Like a Hole", "NIN", 1990));
		 industrialMusic.add(new Song("Headhunter", "Front 242", 1988));
		 industrialMusic.add(dubstepMusic);
		  dubstepMusic.add(new Song("Centipede", "Knife Party", 2012));
		  dubstepMusic.add(new Song("Tetris", "Doctor P", 2011));								//This is a SongGroup that just holds Songs
		everySong.add(heavyMetalMusic);
		 heavyMetalMusic.add(new Song("War Pigs", "Black Sabath", 1970));
		 heavyMetalMusic.add(new Song("Ace of Spades", "Motorhead", 1980));
		DiscJockey crazyLarry = new DiscJockey(everySong);
		crazyLarry.getSongList();
	}
}