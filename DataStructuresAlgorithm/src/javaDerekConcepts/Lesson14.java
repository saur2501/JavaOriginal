package javaDerekConcepts;
// Animal will act as a Super class for other Animals
class Animal {
	
	private String name = "Animal";
	public String favFood = "Food";
	
	// You use protected when you want to allow subclasses
	// To be able to access methods or fields
	// If you would have used private their would be no
	// way for subclasses to call this method
	// This is a final method which means it can't be overwritten
	
	protected final void changeName(String newName){
		
		// this is a reference to the object you're creating
		
		this.name = newName;
		
	}
	
	protected final String getName(){
		
		return this.name;
		
	}
	
	public void eatStuff(){
		
		System.out.println("Yum " + favFood);
		
	}
	
	public void walkAround(){
		
		System.out.println(this.name + " walks around");
		
	}
	
	public Animal(){
		
	}
	
	public Animal(String name, String favFood){
		
		this.changeName(name);
		this.favFood = favFood;
		
	}
	
}

// Cat is a Subclass of Animal
// You create subclasses with the extends keyword
// Now Cat has all the Methods and Fields that Animal defined
// This is known as inheritance because Cat inherits all
// the methods and fields defined in Animal

class Cat extends Animal{
	
	// You can add new fields to the subclass
	public String favToy = "Yarn";
	
	// You can add new methods
	public void playWith(){
		
		System.out.println("Yeah " + favToy);
		
	}
	
	// Here I overrode the Animal walkAround method
	public void walkAround(){
		
		// this refers to a specific object created of type Cat
		
		System.out.println(this.getName() + " stalks around and then sleeps");
		
	}
	
	public String getToy(){
		
		return this.favToy;
		
	}
	
	public Cat(){
		
	}
	
	public Cat(String name, String favFood, String favToy){
		
		// super calls the constructor for the super class Animal
		
		super(name, favFood);
		
		// We set the favToy value in Cat because it doesn't 
		// exist in the Animal class
		
		this.favToy = favToy;
		
	}
	
}

public class Lesson14{
	
public static void main(String[] args){
		
		// I create a Animal object named genericAnimal
	
		Animal genericAnimal = new Animal();
		System.out.println(genericAnimal.getName());
		System.out.println(genericAnimal.favFood);
		
	
		// I create a Cat class like any other
		Cat morris = new Cat("Morris", "Tuna", "Rubber Mouse");
		
		// Print out the name, favFood and favToy
		System.out.println(morris.getName());
		System.out.println(morris.favFood);
		System.out.println(morris.favToy);
		
		// You can also create classes based on the super class
		
		Animal tabby = new Cat("Tabby", "Salmon", "Ball");
		
		// You pass objects like any other field
		acceptAnimal(tabby);
		
	}
	
	public static void acceptAnimal(Animal randAnimal){
		
		// Gets the name and favFood for the Animal passed
		System.out.println(randAnimal.getName());
		System.out.println(randAnimal.favFood);
		
		// This is Polymorphism
		// The interpreter automatically figures out what type
		// of Animal it's dealing with and checks to make sure
		// if methods were overwritten that they are called 
		// instead
		randAnimal.walkAround();
		
		// The interpreter won't find anything that doesn't 
		// originally exist in the Animal class however
		// System.out.println(randAnimal.favToy); Throws an ERROR
		
		// If you want access to fields or methods only found
		// in the Cat class you have to cast the object to
		// that specific class first
		Cat tempCat = (Cat) randAnimal;
		
		System.out.println(tempCat.favToy);
		
		// You could also cast the object directly like this
		System.out.println(((Cat) randAnimal).favToy);
		
		// You can use instanceof to check what type of object
		// you have. This results in a positive for Animal 
		// and for Cat
		if (randAnimal instanceof Cat)
		{
			System.out.println(randAnimal.getName() + " is a Cat");
		}
		
	}	
}