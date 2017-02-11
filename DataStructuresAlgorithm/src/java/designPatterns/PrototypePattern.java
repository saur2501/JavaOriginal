package java.designPatterns;

//By making this class cloneable you are telling Java
//that it is ok to copy instances of this class
//These instance copies have different results when
//System.identityHashCode(System.identityHashCode(bike))
//is called
interface Animal3 extends Cloneable {
	public Animal3 makeCopy();
}

class Sheep implements Animal3 {
	public Sheep() {
		System.out.println("Sheep is Made");
	}

	public Animal3 makeCopy() {
		System.out.println("Sheep is Being Made");
		Sheep sheepObject = null;
		try {
			// Calls the Animal3 super classes clone()
			// Then casts the results to Sheep
			sheepObject = (Sheep) super.clone();
		}
		// If Animal3 didn't extend Cloneable this error
		// is thrown
		catch (CloneNotSupportedException e) {
			System.out.println("The Sheep was Turned to Mush");
			e.printStackTrace();
		}
		return sheepObject;
	}

	public String toString() {
		return "Dolly is my Hero, Baaaaa";
	}
}

class CloneFactory {
	// Receives any Animal3, or Animal3 subclass and
	// makes a copy of it and stores it in its own
	// location in memory
	// CloneFactory has no idea what these objects are
	// except that they are subclasses of Animal3
	public Animal3 getClone(Animal3 animalSample) {
		// Because of Polymorphism the Sheeps makeCopy()
		// is called here instead of Animals
		return animalSample.makeCopy();
	}
}

public class PrototypePattern {
	public static void main(String[] args) {
		// Handles routing makeCopy method calls to the
		// right subclasses of 
		CloneFactory animalMaker = new CloneFactory();
		// Creates a new Sheep instance
		Sheep sally = new Sheep();
		// Creates a clone of Sally and stores it in its own
		// memory location
		Sheep clonedSheep = (Sheep) animalMaker.getClone(sally);
		// These are exact copies of each other
		System.out.println(sally);
		System.out.println(clonedSheep);
		System.out.println("Sally HashCode: " + System.identityHashCode(System.identityHashCode(sally)));
		System.out.println("Clone HashCode: " + System.identityHashCode(System.identityHashCode(clonedSheep)));
	}

}