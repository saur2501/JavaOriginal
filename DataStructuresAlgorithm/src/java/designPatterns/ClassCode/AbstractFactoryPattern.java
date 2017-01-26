package java.designPatterns.ClassCode;

interface Cheese {
	public String toString();
}
class MozzarellaCheese implements Cheese {

	public String toString() {
		return "Shredded Mozzarella";
	}
}
class ParmesanCheese implements Cheese {

	public String toString() {
		return "Shredded Parmesan";
	}
}
class ReggianoCheese implements Cheese {

	public String toString() {
		return "Reggiano Cheese";
	}
}

interface Clams {
	public String toString();
}
class FreshClams implements Clams {

	public String toString() {
		return "Fresh Clams from Long Island Sound";
	}
}
class FrozenClams implements Clams {

	public String toString() {
		return "Frozen Clams from Chesapeake Bay";
	}
}

interface Dough {
	public String toString();
}
class ThickCrustDough implements Dough {
	public String toString() {
		return "ThickCrust style extra thick crust dough";
	}
}
class ThinCrustDough implements Dough {
	public String toString() {
		return "Thin Crust Dough";
	}
}

interface Pepperoni {
	public String toString();
}
class SlicedPepperoni implements Pepperoni {

	public String toString() {
		return "Sliced Pepperoni";
	}
}

interface Sauce {
	public String toString();
}

class MarinaraSauce implements Sauce {
	public String toString() {
		return "Marinara Sauce";
	}
}
class PlumTomatoSauce implements Sauce {
	public String toString() {
		return "Tomato sauce with plum tomatoes";
	}
}

interface Veggies {
	public String toString();
}

class BlackOlives implements Veggies {

	public String toString() {
		return "Black Olives";
	}
}

class Eggplant implements Veggies {

	public String toString() {
		return "Eggplant";
	}
}
class Garlic implements Veggies {

	public String toString() {
		return "Garlic";
	}
}
class Mushroom implements Veggies {

	public String toString() {
		return "Mushrooms";
	}
}
class Onion implements Veggies {

	public String toString() {
		return "Onion";
	}
}
class RedPepper implements Veggies {

	public String toString() {
		return "Red Pepper";
	}
}

class Spinach implements Veggies {

	public String toString() {
		return "Spinach";
	}
}

interface PizzaIngredientFactory {
	 
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
 
}
class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory 
{
	public Dough createDough() {
		return new ThickCrustDough();
	}
	
	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}
	
	public Cheese createCheese() {
		return new MozzarellaCheese();
	}
	
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(), 
		                      new Spinach(), 
		                      new Eggplant() };
		return veggies;
	}
	
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}
	
	public Clams createClam() {
		return new FrozenClams();
	}
}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {
	 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}

abstract class Pizza3 {
	String name;

	Dough dough;
	Sauce sauce;
	Veggies veggies[];
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;

	abstract void prepare();

	void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	void box() {
		System.out.println("Place pizza in official PizzaStore3 box");
	}

	void setName(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("---- " + name + " ----\n");
		if (dough != null) {
			result.append(dough);
			result.append("\n");
		}
		if (sauce != null) {
			result.append(sauce);
			result.append("\n");
		}
		if (cheese != null) {
			result.append(cheese);
			result.append("\n");
		}
		if (veggies != null) {
			for (int i = 0; i < veggies.length; i++) {
				result.append(veggies[i]);
				if (i < veggies.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}
		if (clam != null) {
			result.append(clam);
			result.append("\n");
		}
		if (pepperoni != null) {
			result.append(pepperoni);
			result.append("\n");
		}
		return result.toString();
	}
}

class CheesePizza extends Pizza3 {
	PizzaIngredientFactory ingredientFactory;
 
	public CheesePizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}
class ClamPizza extends Pizza3 {
	PizzaIngredientFactory ingredientFactory;
 
	public ClamPizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		clam = ingredientFactory.createClam();
	}
}
class PepperoniPizza extends Pizza3 {
	PizzaIngredientFactory ingredientFactory;
 
	public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		veggies = ingredientFactory.createVeggies();
		pepperoni = ingredientFactory.createPepperoni();
	}
}
class VeggiePizza extends Pizza3 {
	PizzaIngredientFactory ingredientFactory;
 
	public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		veggies = ingredientFactory.createVeggies();
	}
}

abstract class PizzaStore3 {
	 
	protected abstract Pizza3 createPizza(String item);
 
	public Pizza3 orderPizza(String type) {
		Pizza3 pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}

class ChicagoPizzaStore3 extends PizzaStore3 {

	protected Pizza3 createPizza(String item) {
		Pizza3 pizza = null;
		PizzaIngredientFactory ingredientFactory =
		new ChicagoPizzaIngredientFactory();

		if (item.equals("cheese")) {

			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("Chicago Style Cheese Pizza3");

		} else if (item.equals("veggie")) {

			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("Chicago Style Veggie Pizza3");

		} else if (item.equals("clam")) {

			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("Chicago Style Clam Pizza3");

		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("Chicago Style Pepperoni Pizza3");

		}
		return pizza;
	}
}
class NYPizzaStore3 extends PizzaStore3 {
	 
	protected Pizza3 createPizza(String item) {
		Pizza3 pizza = null;
		PizzaIngredientFactory ingredientFactory = 
			new NYPizzaIngredientFactory();
 
		if (item.equals("cheese")) {
  
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("New York Style Cheese Pizza3");
  
		} else if (item.equals("veggie")) {
 
			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("New York Style Veggie Pizza3");
 
		} else if (item.equals("clam")) {
 
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("New York Style Clam Pizza3");
 
		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("New York Style Pepperoni Pizza3");
 
		} 
		return pizza;
	}
}

public class AbstractFactoryPattern {
	public static void main(String[] args) {
		PizzaStore3 nyStore = new NYPizzaStore3();
		PizzaStore3 chicagoStore = new ChicagoPizzaStore3();
 
		Pizza3 pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}
}
