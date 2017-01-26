package javaConcepts;

/* Java doesn't allow you to inherit from more than one 
 * class. So, what do you do when you want do you do
 * when you want to add additional functionality?
 * You create an interface. Interfaces are empty 
 * classes. They provide all of the methods you must
 * use, but none of the code.
 

// This is how you define an interface. They normally
// have a name that is an adjective. Adjectives modify
// nouns. Most objects have noun names
 * 
 */
interface Drivable {
	
	// You can put fields in an interface, but understand 
	// that their values are final and can't be changed
	double PI = 3.14159265;
	
	// All methods in an interface must be implemented
	// They are public and abstract by default
	// An abstract method must be defined by any class 
	// that uses the interface
	int getWheels();
	// You can't define a method as final and abstract
	// final means the method can't be changed and 
	// abstract means it must be changed
	void setWheels(int numWheels);
	double getSpeed();
	void setSpeed(double speed);	
}


/* If you want to create a class in which every method
 * doesn't have to be implemented use abstract classes.
 */
class Vehicle1 extends Crashable implements Drivable {
	
	int numOfWheels = 2;
	double theSpeed = 0;
	
	int carStrength = 0;
	
	// All methods must be as visible as those in the 
	// interface. If they are public in the interface
	// they must be public in the subclass
	public int getWheels(){
		return this.numOfWheels;
	}
	
	public void setWheels(int numWheels){
		this.numOfWheels = numWheels;
	}
	
	public double getSpeed(){
		return this.theSpeed;
	}
	
	public void setSpeed(double speed){
		this.theSpeed = speed;
	}
	
	public Vehicle1(int wheels, double speed){
		this.numOfWheels = wheels;
		this.theSpeed = speed;
	}
	
	public void setCarStrength(int carStrength){
		this.carStrength = carStrength;
	}
	
	public int getCarStrength(){
		return this.carStrength;
	}
	
}
// Create an abstract class with the abstract keyword
abstract class Crashable{
	
	boolean carDrivable = true;
	
	public void youCrashed(){
		this.carDrivable = false;
	}
	
	public abstract void setCarStrength(int carStrength);
	
	public abstract int getCarStrength();
	
}


/* You define that you want a class to use an interface
 * with the implements keyword. This class must create
 * a method for each method defined in Drivable
 * You can implement more than 1 interface like this
 * public class Vehicle implements Drivable, Crashable
 
// You make a class part of an abstract class by using 
//the extends keyword
class Vehicle extends Crashable implements Drivable {
*/


public class Lesson15 {
	
	public static void main(String[] args){
		Vehicle1 car = new Vehicle1(4, 100.0);
		// Using methods from the interface Drivable
		System.out.println("Cars Max Speed: "+car.getSpeed());
		System.out.println("Cars Number of Wheels: "+car.getWheels());
		// Using methods from abstract method Crashable
		car.setCarStrength(10);
		System.out.println("Car Strength: "+car.getCarStrength());	
	}	
}