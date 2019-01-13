// Memento Design Pattern Tutorial

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

public class MementoDP {
	public static void main(String[] args) {
		TestMemento testMemento = new TestMemento();
		testMemento.run();
	}
}
class TestMemento extends JFrame{
   public void run() {
       
	   new TestMemento();
       
   }
   
   private JButton saveBut, undoBut, redoBut;
   
   // JTextArea(rows, columns)
   
   private JTextArea theArticle = new JTextArea(40,60);
   
   // ---------------------------------------------
   
   // Create a caretaker that contains the ArrayList 
   // with all the articles in it. It can add and
   // retrieve articles from the ArrayList
   
   Caretaker caretaker = new Caretaker();

   // The originator sets the value for the article,
   // creates a new memento with a new article, and 
   // gets the article stored in the current memento
   
   Originator originator = new Originator();
   
   int saveFiles = 0, currentArticle = 0;
   
   // ---------------------------------------------
   
   public TestMemento(){
	   
	   // Set basic information for the panel that will
	   // hold all the GUI elements
	   
	   this.setSize(750,780);
	   this.setTitle("Memento Design Pattern");
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   JPanel panel1 = new JPanel();
	   
	   // Add label to the panel
	   
	   panel1.add(new JLabel("Article"));
	   
	   // Add JTextArea to the panel
	   
	   panel1.add(theArticle);
	   
	   // Add the buttons & ButtonListeners to the panel
	   
	   ButtonListener saveListener = new ButtonListener();
	   ButtonListener undoListener = new ButtonListener();
	   ButtonListener redoListener = new ButtonListener();
	   
	   saveBut = new JButton("Save");
	   saveBut.addActionListener(saveListener);
	   
	   undoBut = new JButton("Undo");
	   undoBut.addActionListener(undoListener);
	   
	   redoBut = new JButton("Redo");
	   redoBut.addActionListener(redoListener);
	   
	   panel1.add(saveBut);
	   panel1.add(undoBut);
	   panel1.add(redoBut);
	   
	   // Add panel to the frame that shows on screen
	   
	   this.add(panel1);
	   
	   this.setVisible(true);
	   
   }
   
   class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == saveBut){
				
				// Get text in JTextArea
				
				String textInTextArea = theArticle.getText();
				
				// Set the value for the current memento
				
				originator.set(textInTextArea);
				
				// Add new article to the ArrayList
				
				caretaker.addMemento( originator.storeInMemento() );
				
				// saveFiles monitors how many articles are saved
				// currentArticle monitors the current article displayed
				
				saveFiles++;
				currentArticle++;
				
				System.out.println("Save Files " + saveFiles);
				
				// Make undo clickable
				
				undoBut.setEnabled(true);
				
			} else 
				
				if(e.getSource() == undoBut){
					
					if(currentArticle >= 1){
						
						// Decrement to the current article displayed
						
						currentArticle--;
						
						// Get the older article saved and display it in JTextArea
					
						String textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
						
						theArticle.setText(textBoxString);
						
						// Make Redo clickable
						
						redoBut.setEnabled(true);
					
					} else {
						
						// Don't allow user to click Undo
						
						undoBut.setEnabled(false);
						
					}
					
				} else
					
					if(e.getSource() == redoBut){
					
					if((saveFiles - 1) > currentArticle){
						
						// Increment to the current article displayed
						
						currentArticle++;
						
						// Get the newer article saved and display it in JTextArea
					
						String textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
						
						theArticle.setText(textBoxString);
						
						// Make undo clickable
						
						undoBut.setEnabled(true);
					
					} else {
						
						// Don't allow user to click Redo
						
						redoBut.setEnabled(false);
						
					}
					
				}
			
		}
		
	}
   
}

//Memento Design Pattern
//Used stores an objects state at a point in time
//so it can be returned to that state later. It
//simply allows you to undo/redo changes on an Object

class Memento {
	
	// The article stored in memento Object
	
	private String article;

	// Save a new article String to the memento Object
	
	public Memento(String articleSave) { article = articleSave; }
	
	// Return the value stored in article 
	
	public String getSavedArticle() { return article; }
	
}

//Memento Design Pattern

class Originator{
	
	private String article;

	// Sets the value for the article
	
	public void set(String newArticle) { 
		System.out.println("From Originator: Current Version of Article\n"+newArticle+ "\n");
	    this.article = newArticle; 
	}

	// Creates a new Memento with a new article
	
	public Memento storeInMemento() { 
	    System.out.println("From Originator: Saving to Memento");
	    return new Memento(article); 
	}
	   
	// Gets the article currently stored in memento
	
	public String restoreFromMemento(Memento memento) {
		   
		article = memento.getSavedArticle(); 
	       
		System.out.println("From Originator: Previous Article Saved in Memento\n"+article + "\n");
		
		return article;
	   
	}
	
}

//Memento Design Pattern Tutorial

class Caretaker {

	// Where all mementos are saved
	
	ArrayList<Memento> savedArticles = new ArrayList<Memento>();

	// Adds memento to the ArrayList
	
	public void addMemento(Memento m) { savedArticles.add(m); }

	// Gets the memento requested from the ArrayList
	
	public Memento getMemento(int index) { return savedArticles.get(index); }
} 

