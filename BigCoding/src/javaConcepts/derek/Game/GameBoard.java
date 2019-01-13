// Layout used by the JPanel

import java.awt.BorderLayout;

// Define color of shapes
import java.awt.Color;

// Allows me to draw and render shapes on components
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

// Will hold all of my Rock objects

import java.util.ArrayList;

// Runs commands after a given delay
import java.util.concurrent.ScheduledThreadPoolExecutor;

// Defines time units. In this case TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;

// NEW Needed for sound to play

// Stores a predefined audio clip

import javax.sound.sampled.Clip;

// Can convert audio data to different playable formats

import javax.sound.sampled.AudioSystem;

// Works with streams of a definite length

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.*;

import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Polygon;
import java.awt.Rectangle; 
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Rectangle;

public class GameBoard extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	// Height and width of the game board
	
	public static int boardWidth = 1000;
	public static int boardHeight = 800;
	
	// Used to check if a key is being held down
	
	public static boolean keyHeld = false;
	
	// Gets the keycode for the key being held down
	
	public static int keyHeldCode;
	
	// Holds every PhotonTorpedo I create
	
	public static ArrayList<PhotonTorpedo> torpedos = new ArrayList<PhotonTorpedo>();
	
	// NEW Location of sound files
	
	String thrustFile = "file:./src/thrust.au";
	String laserFile = "file:./src/laser.aiff";
	
	public static void main(String [] args)
    {
            new GameBoard();
            
    }
	
	public GameBoard()
    {
    	// Define the defaults for the JFrame
    	
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Java Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Handles executing code based on keys being pressed
        
        addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==87)
			    {
					System.out.println("Forward");
					
					// NEW play thrust noise when w is pressed
					
					GameBoard.playSoundEffect(thrustFile);
					
					keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
					
			    } 
				// Not currently used
				
					else if (e.getKeyCode()==83){
			    	System.out.println("Backward");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	
			    } 
			    
				// Id the d key is pressed set keyHeld as if it
				// was being held down. This will cause the ship to 
				// constantly rotate. keyHeldCode stores the keyCode for d
			    
			    else if (e.getKeyCode()==68){
			    	System.out.println("Rotate Right");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	
			    } 
			    
			    // Same thing is done here as was done with the last
				// 65 is the keyCode for a
			    
			    else if (e.getKeyCode()==65){
			    	System.out.println("Rotate Left");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    }
				
				// NEW Checks if Enter key is pressed ---------------
				
			    else if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	System.out.println("Shoot");
			    	
			    	// NEW play laser sound when enter is hit
			    	
			    	playSoundEffect(laserFile);
			    	
			    	// Creates a new torpedo and passes the ships nose position
			    	// so the torpedo can start there. Also passes the ships
			    	// rotation angle so the torpedo goes in the right direction
			    	
			    	torpedos.add(new PhotonTorpedo(GameDrawingPanel2.theShip.getShipNoseX(),
			    			GameDrawingPanel2.theShip.getShipNoseY(), 
			    			GameDrawingPanel2.theShip.getRotationAngle()));
			    	
			    	System.out.println("RotationAngle " + GameDrawingPanel2.theShip.getRotationAngle());
			    	
			    }
				
			}

			// When the key is released this informs the code that
			// the key isn't being held down
			
			public void keyReleased(KeyEvent e) {
		
				keyHeld = false;
				
			}
        	
        });
        
        GameDrawingPanel2 gamePanel = new GameDrawingPanel2();

     // Make the drawing area take up the rest of the frame
        
        this.add(gamePanel, BorderLayout.CENTER);
        
        // Used to execute code after a given delay
        // The attribute is corePoolSize - the number of threads to keep in 
        // the pool, even if they are idle
        
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		
        // Method to execute, initial delay, subsequent delay, time unit
        
		executor.scheduleAtFixedRate(new RepaintTheBoard2(this), 0L, 20L, TimeUnit.MILLISECONDS);
        
        // Show the frame
        
        this.setVisible(true);
    }
	
	// NEW Handles playing all sound effects
	
	public static void playSoundEffect(String soundToPlay){
    	
    	// Pointer towards the resource to play
		URL soundLocation;
		try {
			soundLocation = new URL(soundToPlay);
		 
        	    // Stores a predefined audio clip
        	    Clip clip = null;
				
				// Convert audio data to different playable formats
				clip = AudioSystem.getClip();
				
				// Holds a stream of a definite length
        	    AudioInputStream inputStream;

				inputStream = AudioSystem.getAudioInputStream(soundLocation);

				// Make audio clip available for play
				clip.open( inputStream );
					
				// Define how many times to loop
				clip.loop(0);
				
				// Play the clip
	        	clip.start();
				} 
				
				catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    
        	    catch (UnsupportedAudioFileException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    
        	    catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	
    }
		
	
}


// Class implements the runnable interface
// By creating this thread we can continually redraw the screen
// while other code continues to execute

class RepaintTheBoard2 implements Runnable{

	GameBoard theBoard;
	
	public RepaintTheBoard2(GameBoard theBoard){
		this.theBoard = theBoard;
	}

	@Override
	public void run() {
		
		// Redraws the game board
		
		theBoard.repaint();
		
	}
	
}

@SuppressWarnings("serial")

// GameDrawingPanel is what we are drawing on

class GameDrawingPanel2 extends JComponent { 
	
	// Holds every Rock I create
	
	public static ArrayList<Rock> rocks = new ArrayList<Rock>();
	
	// Get the original x & y points for the polygon
	
	int[] polyXArray = Rock.sPolyXArray;
	int[] polyYArray = Rock.sPolyYArray;
	
	 // Create a SpaceShip
	static SpaceShip theShip = new SpaceShip();
	
	// Gets the game board height and weight
	
	int width = GameBoard.boardWidth;
	int height = GameBoard.boardHeight;
	
	// Creates 50 Rock objects and stores them in the ArrayList
	// Suppress warnings when I clone the rocks array

	public GameDrawingPanel2() { 
		
		for(int i = 0; i < 10; i++){
			
			// Find a random x & y starting point
			// The -40 part is on there to keep the Rock on the screen
			
			int randomStartXPos = (int) (Math.random() * (GameBoard.boardWidth - 40) + 1);
			int randomStartYPos = (int) (Math.random() * (GameBoard.boardHeight - 40) + 1);
			
			// Add the Rock object to the ArrayList based on the attributes sent
			
			rocks.add(new Rock(Rock.getpolyXArray(randomStartXPos), Rock.getpolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
			
			Rock.rocks = rocks; 
			
		}
		
	} 
	
	public void paint(Graphics g) { 
		
		// Allows me to make many settings changes in regards to graphics
		
		Graphics2D graphicSettings = (Graphics2D)g; 
		
		AffineTransform identity = new AffineTransform();
		
		// Draw a black background that is as big as the game board
		
		graphicSettings.setColor(Color.BLACK);
		graphicSettings.fillRect(0, 0, getWidth(), getHeight());
		
		// Set rendering rules
		
		graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		// Set the drawing color to white
		
		graphicSettings.setPaint( Color.WHITE ); 
		
		// Cycle through all of the Rock objects
		
		for(Rock rock : rocks){
			
			// NEW Is rock viewable
			
			if(rock.onScreen){
			
			// NEW Move the Rock polygon 
			
			rock.move(theShip, GameBoard.torpedos); 
			
			// Stroke the polygon Rock on the screen
			
			graphicSettings.draw(rock); 
			
			}
			
		} 
		
		// Handles spinning the ship in the clockwise direction when the D key
		// is pressed and held
		
		if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 68){
			
			theShip.increaseRotationAngle();
			
		} else
			
		// Continues to rotate the ship counter clockwise if the A key is held
			
		if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 65){
				
			theShip.decreaseRotationAngle();
				
		} else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 87){
			
			// Set movement angle to the current rotation angle
			// This is done so that the ship rotation can be set by the A & D keys
			// but when the throttle is hit the ship knows what direction to go
			
			theShip.setMovingAngle(theShip.getRotationAngle());
			
			theShip.increaseXVelocity(theShip.shipXMoveAngle(theShip.getMovingAngle())*0.1);
			theShip.increaseYVelocity(theShip.shipYMoveAngle(theShip.getMovingAngle())*0.1);
			
		} 
		
		theShip.move();
		
		// Sets the origin on the screen so rotation occurs properly
		
		graphicSettings.setTransform(identity);
		
		// Moves the ship to the center of the screen
		
		graphicSettings.translate(theShip.getXCenter(),theShip.getYCenter());
		
		// Rotates the ship
		
		graphicSettings.rotate(Math.toRadians(theShip.getRotationAngle()));
		
		graphicSettings.draw(theShip);
		
		// Draw torpedos
		
		for(PhotonTorpedo torpedo : GameBoard.torpedos){
			
			// Move the Torpedo polygon 
			
			torpedo.move(); 
			
			// Make sure the Torpedo is on the screen
			
			if(torpedo.onScreen){
			
				// Stroke the polygon torpedo on the screen
				
				graphicSettings.setTransform(identity);
				
				// Changes the torpedos center x & y vectors
				
				graphicSettings.translate(torpedo.getXCenter(),torpedo.getYCenter());
				
				graphicSettings.draw(torpedo);
			
			}
			
		}
		
		
	} 
	
	
}

// Extending the Polygon class because I'm drawing Polygons

class Rock extends Polygon{
	
	// Upper left hand corner of the Polygon
	
	int uLeftXPos, uLeftYPos;
	
	// Used to change the direction of the asteroid when 
	// it hits something and determines how fast it moves
	
	int xDirection = 1;
	int yDirection = 1;
	
	// Define rock height and width
	
	int rockWidth = 26;
	int rockHeight = 31;
	
	// Copy of the Rock ArrayList
	// Holds every Rock I create
	
	static ArrayList<Rock> rocks = new ArrayList<Rock>();
	
	// For JApplet
	// int width = ExampleBoard.WIDTH;
	// int height = ExampleBoard.HEIGHT;
	
	// Get the board width and height
	
	int width = GameBoard.boardWidth;
	int height = GameBoard.boardHeight;
	
	// Will hold the x & y coordinates for the Polygons
	
	int[] polyXArray, polyYArray;
	
	// x & y positions available for other methods
	// There will be more Polygon points available later
	
	public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
	public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};
	
	// NEW Keep track of whether Rock is on screen
	
	public boolean onScreen = true;
	
	// NEW Sound file names
	// The JavaSound api allows wavs, au, aiff files
	
	String explodeFile = "file:./src/explode.wav";
	
	// Creates a new asteroid 
	
	public Rock(int[] polyXArray, int[] polyYArray, int pointsInPoly, int randomStartXPos, int randomStartYPos){
		
		// Creates a Polygon by calling the super or parent class of Rock Polygon
		
		super(polyXArray, polyYArray, pointsInPoly);
		
		// Randomly generate a speed for the Polygon
		
		this.xDirection = (int) (Math.random() * 4 + 1);
		
		this.yDirection = (int) (Math.random() * 4 + 1);
		
		// Holds the starting x & y position for the Rock
		
		this.uLeftXPos = randomStartXPos;
		
		this.uLeftYPos = randomStartYPos;
		
	}
	
	// Creates a bounding rectangle for collision checking
	
	public Rectangle getBounds() {
		
        return new Rectangle(super.xpoints[0], super.ypoints[0], rockWidth, rockHeight);
        
    }

	// NEW move receives SpaceShip and Torpedos now
	public void move(SpaceShip theShip, ArrayList<PhotonTorpedo> torpedos){
		
		// This rectangle surrounds the rock I'll check against
		// all of the other rocks below
		
		Rectangle rockToCheck = this.getBounds();
		
		// Cycle through all the other rocks and check if they
		// cross over the rectangle I created above
		
		for(Rock rock : rocks){
			
			// NEW Is rock viewable
			
			if(rock.onScreen){
			
			// Creates a bounding rectangle that is used temporarily
			// for each other rock on the board
			
			Rectangle otherRock = rock.getBounds();
			
			// Check to make sure I'm not comparing one rock to itself
			// Check if one rock crosses over another rock
			
			if(rock != this && otherRock.intersects(rockToCheck)){

				// Switch the direction the rocks are moving on impact
				
				int tempXDirection = this.xDirection;
				int tempYDirection = this.yDirection;
				
				this.xDirection = rock.xDirection;
				this.yDirection = rock.yDirection;
				
				rock.xDirection = tempXDirection;
				rock.yDirection = tempYDirection;
				
			}
			
				// NEW Check if theShip hits a Rock
			
				Rectangle shipBox = theShip.getBounds();
				
				if(otherRock.intersects(shipBox)){
					
					// NEW play explosion if ship is hit
					
					GameBoard.playSoundEffect(explodeFile);
					
					theShip.setXCenter(theShip.gBWidth/2);
					theShip.setYCenter(theShip.gBHeight/2);
					
					theShip.setXVelocity(0);
					theShip.setYVelocity(0);
					
					
				}
				
				for(PhotonTorpedo torpedo : torpedos){
					
					// Make sure the Torpedo is on the screen
					
					if(torpedo.onScreen){
					
						// NEW Check if a torpedo hits a Rock
						
						if(otherRock.contains(torpedo.getXCenter(),torpedo.getYCenter())){
							
							rock.onScreen = false;
							torpedo.onScreen = false;
							
							System.out.println("HIT");
							
							// NEW play explosion sound if rock is destroyed
							
							GameBoard.playSoundEffect(explodeFile);
						}
					
					}
					
				}
			
			}
			
		} 
		
		// Get the upper left and top most point for the Polygon
		// This will be dynamic later on
		
		int uLeftXPos = super.xpoints[0]; 
		
		int uLeftYPos = super.ypoints[0];
		
		// If the Rock hits a wall it will go in the opposite direction
		
		if (uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection; 
		
		if (uLeftYPos < 0 || (uLeftYPos + 50) > height) yDirection = -yDirection;
		
		// Change the values of the points for the Polygon
		
		for (int i = 0; i < super.xpoints.length; i++){
			
			super.xpoints[i] += xDirection;
			super.ypoints[i] += yDirection;
			
		}
		
	}
	
	// public method available for creating Polygon x point arrays
	
	public static int[] getpolyXArray(int randomStartXPos){
		
		// Clones the array so that the original shape isn't changed for the asteroid
		
		int[] tempPolyXArray = (int[])sPolyXArray.clone();
		
		for (int i = 0; i < tempPolyXArray.length; i++){
			
			tempPolyXArray[i] += randomStartXPos;
			
		}
		
		return tempPolyXArray;
		
	}
	
	// public method available for creating Polygon y point arrays
	
	public static int[] getpolyYArray(int randomStartYPos){
		
		// Clones the array so that the original shape isn't changed for the asteroid
		
		int[] tempPolyYArray = (int[])sPolyYArray.clone();
		
		for (int i = 0; i < tempPolyYArray.length; i++){
			
			tempPolyYArray[i] += randomStartYPos;
			
		}
		
		return tempPolyYArray;
		
	}
	
}

@SuppressWarnings("serial")
class SpaceShip extends Polygon{

	// Determines the speed the ship moves
	
	private double xVelocity = 0, yVelocity = 0;
	
	// Get the board width and height
	
	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
	
	// Center of space ship
	
	private double centerX = gBWidth/2, centerY = gBHeight/2;
	
	// Will hold the x & y coordinates for the ship
	// Everything is based on coordinates from the center
	// It is done this way so that rotation works properly
		
	public static int[] polyXArray = {-13,14,-13,-5,-13};
	public static int[] polyYArray = {-15,0,15,0,-15};
	
	// Width and height of ship
	
	private int shipWidth = 27, shipHeight = 30;
	
	// Upper left hand corner of space ship
	
	private double uLeftXPos = getXCenter() + this.polyXArray[0];
	private double uLeftYPos = getYCenter() + this.polyYArray[0];
	
	// Defines if the ship should rotate
	
	private double rotationAngle = 0, movingAngle = 0;
	
	// NEW Keep track of whether SpaceShip is on screen
	
	public boolean onScreen = true;
	
	// Creates a new space ship
	
	public SpaceShip(){
			
			// Creates a Polygon by calling the super or parent class Polygon
			
			super(polyXArray, polyYArray, 5);
			
	}
	
	// Gets & sets the values for the x & y center of ship
	
	public double getXCenter(){ return centerX; }
	
	public double getYCenter(){ return centerY; }
	
	public void setXCenter(double xCent){ this.centerX = xCent; }
	
	public void setYCenter(double yCent){ this.centerY = yCent; }
	
	public void increaseXPos(double incAmt) { this.centerX += incAmt; }
	
	public void increaseYPos(double incAmt) { this.centerY += incAmt; }	
	
	// Gets & sets the x & y for upper left hand corner of ship
	
	public double getuLeftXPos(){ return uLeftXPos; }
	
	public double getuLeftYPos(){ return uLeftYPos; }
	
	public void setuLeftXPos(double xULPos){ this.uLeftXPos = xULPos; }
	
	public void setuLeftYPos(double yULPos){ this.uLeftYPos = yULPos; }
	
	// Gets & sets the x & y for upper left hand corner of ship
	
	public int getShipWidth(){ return shipWidth; }
		
	public int getShipHeight(){ return shipHeight; }
	
	// Gets, sets, increases and decreases the ship velocity
	
	public double getXVelocity(){ return xVelocity; }
	
	public double getYVelocity(){ return yVelocity; }
	
	public void setXVelocity(double xVel){ this.xVelocity = xVel; }
	
	public void setYVelocity(double yVel){ this.yVelocity = yVel; }
	
	public void increaseXVelocity(double xVelInc){ this.xVelocity += xVelInc; }
	
	public void increaseYVelocity(double yVelInc){ this.yVelocity += yVelInc; }
	
	public void decreaseXVelocity(double xVelDec){ this.xVelocity -= xVelDec; }
	
	public void decreaseYVelocity(double yVelDec){ this.yVelocity -= yVelDec; }
	
	// Set and increase the ship movement angle
	
	public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }
	
	public double getMovingAngle(){ return movingAngle; }
	
	public void increaseMovingAngle(double moveAngle){ this.movingAngle += moveAngle; }
	
	// Calculate the ship angle of movement
	
	// This is trigonometry at work
	// By taking the cos of the angle I get the opposite value of x
	// Angle * Math.PI / 180 converts degrees into radians
	
	public double shipXMoveAngle(double xMoveAngle){
		
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
		
	}
	
	// By taking the sin of the angle I get the adjacent value of y
	// Angle * Math.PI / 180 converts degrees into radians 
	
	public double shipYMoveAngle(double yMoveAngle){
		
		return (double) (Math.sin(yMoveAngle * Math.PI / 180));
		
	}
	
	
	// Gets & increases or decreases the rotation angle of the ship
	
	// Increase the rotation angle by 5, but don't let it go above 360
	
	public double getRotationAngle(){ return rotationAngle; }
		
	public void increaseRotationAngle(){ 
		
		if(getRotationAngle() >= 355) { rotationAngle = 0; }
		
		else { rotationAngle += 5; }
		
	}
	
	// Decrease the rotation angle by 5, but don't let it go below 0
	
	public void decreaseRotationAngle(){ 
		
		if(getRotationAngle() < 0) { rotationAngle = 355; }
		
		else { rotationAngle -= 5; }
		
	}
	
	// NEW FIX Artificial rectangle that is used for collision detection
	
	public Rectangle getBounds(){
		
		return new Rectangle((int) (getXCenter() - 14), (int) (getYCenter() - 15), getShipWidth(), getShipHeight());
		
	}
	
	// Gets the spaceships nose X vector -------------------
	
	public double getShipNoseX(){
		
		// x2 = this.getXCenter() + cos(rotationAngle) * 14
		
		return this.getXCenter() + Math.cos(rotationAngle) * 14;
		
	}
	
	// Gets the spaceships nose Y vector --------------------
	
	public double getShipNoseY(){
		
		// x2 = this.getXCenter() + cos(rotationAngle) * 14
		// y2 = this.getYCenter() + sin(rotationAngle) * 14
		
		return this.getYCenter() + Math.sin(rotationAngle) * 14;
		
	}
	
	public void move(){
		
		// Increase the x origin value based on current velocity 
		
		this.increaseXPos(this.getXVelocity());
		
		// If the ship goes off the board flip it to the other side of the board
		
		if(this.getXCenter() < 0){
			
			this.setXCenter(gBWidth);
			
		} else
			if (this.getXCenter() > gBWidth){
				
				this.setXCenter(0);
				
			}
		
		// Increase the x origin value based on current velocity 
		
		this.increaseYPos(this.getYVelocity());
		
		// If the ship goes off the board flip it to the other side of the board
		
		if(this.getYCenter() < 0){
			
			this.setYCenter(gBHeight);
			
		} else
			if (this.getYCenter() > gBHeight){
				
				this.setYCenter(0);
				
			}
		
	}
	
	
}

class PhotonTorpedo extends Polygon{
	
	private static final long serialVersionUID = 1L;
	
	// Get the board width and height

	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
		
	// Center of torpedo
		
	private double centerX = 0, centerY = 0;
		
	// Will hold the x & y coordinates for the torpedo
	// Everything is based on coordinates from the center
		
	public static int[] polyXArray = {-3,3,3,-3,-3};
	public static int[] polyYArray = {-3,-3,3,3,-3};
	
	// Width and height of torpedo
	
	private int torpedoWidth = 6, torpedoHeight = 6;
	
	// Keep track of whether torpedo is on screen
	
	public boolean onScreen = false;
	
	// The angle the torpedo moves on the screen
	
	private double movingAngle = 0;
	
	// Determines how quickly the torpedo moves on 
	// its assigned path
	
	private double xVelocity = 5, yVelocity = 5;
	
	public PhotonTorpedo(double centerX, double centerY, double movingAngle){
		
		// Creates a Polygon by calling the super or parent class Polygon
		
		super(polyXArray, polyYArray, 5);
		
		// Defines the center based on the vectors of
		// the ships nose. movingAngle is the same as ship
		
		this.centerX = centerX;
		this.centerY = centerY;
		this.movingAngle = movingAngle;
		
		// Tells program to show the torpedo
		
		this.onScreen = true;
		
		// Sets how quickly the torpedo moves along the path defined
		// by setXVelocity and setYVelocity
		
		this.setXVelocity(this.torpedoXMoveAngle(movingAngle)*10);
		this.setYVelocity(this.torpedoYMoveAngle(movingAngle)*10);
		
	}
	
	// Gets & sets the values for the x & y center of torpedo
	
	public double getXCenter(){ return centerX; }
		
	public double getYCenter(){ return centerY; }
		
	public void setXCenter(double xCent){ this.centerX = xCent; }
		
	public void setYCenter(double yCent){ this.centerY = yCent; }
		
	public void changeXPos(double incAmt) { this.centerX += incAmt; }
		
	public void changeYPos(double incAmt) { this.centerY += incAmt; }
	
	// Gets, sets, the torpedo velocity
	
	public double getXVelocity(){ return xVelocity; }
		
	public double getYVelocity(){ return yVelocity; }
		
	public void setXVelocity(double xVel){ this.xVelocity = xVel; }
		
	public void setYVelocity(double yVel){ this.yVelocity = yVel; }
	
	// Gets & sets the x & y for upper left hand corner of ship
	
	public int getWidth(){ return torpedoWidth; }
			
	public int getHeight(){ return torpedoHeight; }
	
	// Set and increase the torpedo movement angle
	
	public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }
		
	public double getMovingAngle(){ return movingAngle; }
	
	// Artificial rectangle that is used for collision detection
	
	public Rectangle getBounds(){
			
		return new Rectangle((int) (getXCenter() - 6), (int) (getXCenter() - 6), getWidth(), getHeight());
			
	}
	
	// Calculate the torpedo angle of movement
	
	// By taking the cos of the angle I get the opposite value of x
	// Angle * Math.PI / 180 converts degrees into radians
		
	public double torpedoXMoveAngle(double xMoveAngle){
			
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
			
	}
		
	// By taking the sin of the angle I get the adjacent value of y
	// Angle * Math.PI / 180 converts degrees into radians 
		
	public double torpedoYMoveAngle(double yMoveAngle){
			
		return (double) (Math.sin(yMoveAngle * Math.PI / 180));
			
	}
	
	public void move(){
		
		if(this.onScreen){
			
			// Increase the x origin value based on current velocity
			
			this.changeXPos(this.getXVelocity());
		
		// If the ship goes off the board flip it to the other side of the board
		
		if(this.getXCenter() < 0){
			
			this.onScreen = false;
			
		} else
			if (this.getXCenter() > gBWidth){
				
				this.onScreen = false;
				
			}
		
		// Increase the x origin value based on current velocity 
		
		this.changeYPos(this.getYVelocity());
		
		// If the ship goes off the board flip it to the other side of the board
		
		if(this.getYCenter() < 0){
			
			this.onScreen = false;
			
		} else
			if (this.getYCenter() > gBHeight){
				
				this.onScreen = false;
				
			}
		
		} // END OF onScreen CHECK
		
	}

	
}