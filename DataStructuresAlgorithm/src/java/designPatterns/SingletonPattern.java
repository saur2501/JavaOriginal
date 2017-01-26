package java.designPatterns;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Singleton {
	private static Singleton firstInstance = null;
	String[] scrabbleLetters =
		    {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "e", "e", "f", "f", "g", "g", "h", "h", "i", "i", "j", "k", "l", 
			"l", "m", "m", "n", "n", "o", "o", "o", "p", "p", "q", "r", "r", "s", "s", "s", "s", "t", "t", "t", "t", "t", "t", "u", "v", "v", "w", "w", "x", "y", "y", "z"};  	
	private LinkedList<String> letterList = new LinkedList<String> (Arrays.asList(scrabbleLetters));
											//Used to slow down 1st thread
	static boolean firstThread = true;								//Created to keep users from instantiation Only Singleton will be able to instantiate this class; unnecessary
	
	private Singleton() { }
	
	public static Singleton getInstance() {							//OR public static synchronized Singleton getInstance()
		if(firstInstance == null) {									//This is here to test what happens if threads try to create instances of this class
			if(firstThread){
				firstThread = false;
				try {
					Thread.currentThread();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized(Singleton.class){							// Here we just use synchronized when the first object is created 
				if(firstInstance == null) {			
					firstInstance = new Singleton();
					Collections.shuffle(firstInstance.letterList);	// Shuffle the letters in the list					
				}			
			}
		}		
		return firstInstance;										//Return instance in any case
	}
	
	public LinkedList<String> getLetterList(){
		return firstInstance.letterList;
	}
	
	public LinkedList<String> getTiles(int howManyTiles){				//Tiles to be returned to the user		
		LinkedList<String> tilesToSend = new LinkedList<String>();		
		for(int i = 0; i <= howManyTiles; i++){							//Cycle through the LinkedList while adding the starting Strings to the to be returned LinkedList while deleting them from letterList		
			tilesToSend.add(firstInstance.letterList.remove(0));
		}
		return tilesToSend;												//Return the number of letter tiles requested		
	}
	
}


class ScrabbleTest {
	public void execute(){
		Singleton newInstance = Singleton.getInstance();									//How you create a new instance of Singleton		
		System.out.println("1st Instance ID: " + System.identityHashCode(newInstance));		//Get unique id for instance object
		System.out.println(newInstance.getLetterList());									//Get all of the letters stored in the List		
		LinkedList<String> playerOneTiles = newInstance.getTiles(7);
		System.out.println("Player 1: " + playerOneTiles);
		System.out.println(newInstance.getLetterList());
		 /*Try to make another instance of Singleton
		 This doesn't work because the constructor is private
		 Singleton instanceTwo = new Singleton();
		 Try getting a new instance using getInstance*/
		
		Singleton instanceTwo = Singleton.getInstance();
		System.out.println("2nd Instance ID: " + System.identityHashCode(instanceTwo));
		System.out.println(instanceTwo.getLetterList());		
		LinkedList<String> playerTwoTiles = newInstance.getTiles(7);		
		System.out.println("Player 2: " + playerTwoTiles);		
	}	
}

public class SingletonPattern{	
	public static void execute(){
		Runnable getTiles = new GetTheTiles();				
		Runnable getTilesAgain = new GetTheTiles();				
		new Thread(getTiles).start();											//create a new thread using runnable interface.
		new Thread(getTilesAgain).start();										//Call for the code in the method run to execute
	}	
}

class GetTheTiles implements Runnable {	
	public void run(){
		Singleton newInstance = Singleton.getInstance();						//How you create a new instance of Singleton			
		System.out.println("1st Instance ID: " + System.identityHashCode(newInstance));		//Get unique id for instance object			
		System.out.println(newInstance.getLetterList());						//Get all of the letters stored in the List
		LinkedList<String> playerOneTiles = newInstance.getTiles(7);			
		System.out.println("Player 1: " + playerOneTiles);		
		System.out.println("Got Tiles");
	}
	
}