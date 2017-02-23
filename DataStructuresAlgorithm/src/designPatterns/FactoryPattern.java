package designPatterns;

import java.util.Scanner;

public class FactoryPattern {

	public static void main(String[] args){
		
		// Create the factory object
		FighterShipFactory shipFactory = new FighterShipFactory();
		
		// Fighter ship object
		
		FighterShip theFighter = null;
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.print("What type of ship? (U / R / B)");
		
		if (userInput.hasNextLine()){
			
			String typeOfShip = userInput.nextLine();
		
			theFighter = shipFactory.makeFighterShip(typeOfShip);
			
			if(theFighter != null){
				
				doStuffFighter(theFighter);
				
			} else System.out.print("Please enter U, R, or B next time");
		
		}
		
		/*
		FighterShip theFighter = null;
		
		// Old way of creating objects
		// When we use new we are not being dynamic
		
		FighterShip ufoShip = new UFOFighterShip();
		
		doStuffFighter(ufoShip);
		
		System.out.print("\n");
		
		// -----------------------------------------
		
		// This allows me to make the program more dynamic
		// It doesn't close the code from being modified
		// and that is bad!
		
		// Defines an input stream to watch: keyboard
		Scanner userInput = new Scanner(System.in);
		
		String FighterShipOption = "";
		
		System.out.print("What type of ship? (U or R)");
		
		if (userInput.hasNextLine()){
			
			FighterShipOption = userInput.nextLine();
			
		}
		
		if (FighterShipOption == "U"){
			
			theFighter = new UFOFighterShip();

			
		} else 
		
		if (FighterShipOption == "R"){
			
			theFighter = new RocketFighterShip();
			
		} else {
			
			theFighter = new BigUFOFighterShip();
			
		}
		
		doStuffFighter(theFighter);
		
		// --------------------------------------------
		*/
		
	}
	
	// Executes methods of the super class
	
	public static void doStuffFighter(FighterShip anFighterShip){
		
		anFighterShip.displayFighterShip();
		
		anFighterShip.followHeroShip();
		
		anFighterShip.fighterShipShoots();
		
	}

}

abstract class FighterShip {
	
	private String name;
	private double speed;
	private double directionX;
	private double directionY;
	private double amtDamage;
	
	public String getName() { return name; }
	public void setName(String newName) { name = newName; }
	
	public double getDamage() { return amtDamage; }
	public void setDamage(double newDamage) { amtDamage = newDamage; }
	
	public void followHeroShip(){
		
		System.out.println(getName() + " is following the hero");
		
	}
	
	public void displayFighterShip(){
		
		System.out.println(getName() + " is on the screen");
		
	}
	
	public void fighterShipShoots() {
		
		System.out.println(getName() + " attacks and does " + getDamage() + " damage to hero");
		
	}
	
}

class UFOFighterShip extends FighterShip {
	
	public UFOFighterShip(){
		
		setName("UFO Fighter Ship");
		
		setDamage(20.0);
		
	}
	
}

class RocketFighterShip extends FighterShip {
	
	public RocketFighterShip(){
		
		setName("Rocket Fighter Ship");
		
		setDamage(10.0);
		
	}
	
}

class BigUFOFighterShip extends UFOFighterShip {
	
	public BigUFOFighterShip(){
		
		setName("Big UFO Fighter Ship");
		
		setDamage(40.0);
		
	}
	
}

//This is a factory thats only job is creating ships
//By encapsulating ship creation, we only have one
//place to make modifications

class FighterShipFactory{
	
	// This could be used as a static method if we
	// are willing to give up subclassing it
	
	public FighterShip makeFighterShip(String newShipType){
		
		FighterShip newShip = null;
		
		if (newShipType.equals("U")){
			
			return new UFOFighterShip();
			
		} else 
		
		if (newShipType.equals("R")){
			
			return new RocketFighterShip();
			
		} else 
		
		if (newShipType.equals("B")){
			
			return new BigUFOFighterShip();
			
		} else return null;
		
	}
	
}

