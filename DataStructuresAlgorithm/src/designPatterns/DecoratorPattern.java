package designPatterns;

public class DecoratorPattern {
	public static void main(String[] args){
		// The PlainPizza object is sent to the Mozzarella constructor
		// and then to the TomatoSauce constructor
		Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));
		System.out.println("Ingredients: " + basicPizza.getDescription());
		System.out.println("Price: " + basicPizza.getCost());
    }
}

interface Pizza {					//Blueprint for classes that will have decorators
	public String getDescription();
	public double getCost();
}

/* 
public abstract class Pizza{
	public abstract void setDescription(String newDescription);
	public abstract String getDescription();
	
	public abstract void setCost(double newCost);
	public abstract double getCost();
	
	// I could use getter and setter methods for every potential Pizza topping
	
}
*/

//By going this route I'll have to create a new subclass for an infinite number of pizza.
//I'd also have to change prices in many classes when just 1 Pizza topping cost changes
//Inheritance is static while composition is dynamic. Through composition I'll be able to add new functionality by writing new code rather than by changing current code
class ThreeCheesePizza implements Pizza{
	private String description = "Mozzarella, Fontina, Parmesan Cheese Pizza";
	private double cost = 10.50;
	public void setDescription(String newDescription) {
		description = newDescription;	
	}
	public String getDescription() {
		return description;	
	}
	public void setCost(double newCost) {
		cost = newCost;	
	}
	public double getCost() {
		return cost;		
	}
}

class PlainPizza implements Pizza {						//Rudimentary class- Every Pizza made will start as a PlainPizza
	public String getDescription() {
		return "Thin dough";
	}
	public double getCost() {
		System.out.println("Cost of Dough: " + 4.00);
		return 4.00;
	}
}

abstract class ToppingDecorator implements Pizza {		//Has a "Has a" relationship with Pizza. This is an Aggregation Relationship
	protected Pizza tempPizza;							//Assigns the type instance to this attribute of a Pizza. All decorators can dynamically customize the Pizza instance PlainPizza because of this
	public ToppingDecorator(Pizza newPizza){
		tempPizza = newPizza;
	}
	public String getDescription() {
		return tempPizza.getDescription();
	}
	public double getCost() {
		return tempPizza.getCost();
	}
}

class Mozzarella extends ToppingDecorator {			//Conceive of it as a Mozzarella-topped Pizza object.
	public Mozzarella(Pizza newPizza) {
		super(newPizza);
		System.out.println("Adding Dough");
		System.out.println("Adding Moz");
	}
	public String getDescription(){						//Returns the result of calling getDescription() for PlainPizza and adds " mozzarella" to it
		return tempPizza.getDescription() + ", mozzarella";
	}
	public double getCost(){
		System.out.println("Cost of Moz: " + .50);
		return tempPizza.getCost() + .50;	
	}	
}
class TomatoSauce extends ToppingDecorator {
	public TomatoSauce(Pizza newPizza) {
		super(newPizza);		
		System.out.println("Adding Sauce");
	}
	public String getDescription(){						//Returns the result of calling getDescription() for PlainPizza, Mozzarella and then TomatoSauce
		return tempPizza.getDescription() + ", tomato sauce";
	}
	public double getCost(){
		System.out.println("Cost of Sauce: " + .35);
		return tempPizza.getCost() + .35;
	}
}

