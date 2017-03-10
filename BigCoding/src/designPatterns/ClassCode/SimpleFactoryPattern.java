package designPatterns.ClassCode;

import java.util.ArrayList;

abstract class Pizza2 {
	String name;
	String dough;
	String sauce;
	ArrayList toppings = new ArrayList();

	public String getName() {
		return name;
	}
	public void prepare() {
		System.out.println("Preparing " + name);
	}
	public void bake() {
		System.out.println("Baking " + name);
	}
	public void cut() {
		System.out.println("Cutting " + name);
	}
	public void box() {
		System.out.println("Boxing " + name);
	}
	public String toString() {
		// code to display pizza name and ingredients
		StringBuffer display = new StringBuffer();
		display.append("---- " + name + " ----\n");
		display.append(dough + "\n");
		display.append(sauce + "\n");
		for (int i = 0; i < toppings.size(); i++) {
			display.append((String )toppings.get(i) + "\n");
		}
		return display.toString();
	}
}
class CheesePizza2 extends Pizza2 {
	public CheesePizza2() {
		name = "Cheese Pizza2";
		dough = "Regular Crust";
		sauce = "Marinara Pizza2 Sauce";
		toppings.add("Fresh Mozzarella");
		toppings.add("Parmesan");
	}
}
class ClamPizza2 extends Pizza2 {
	public ClamPizza2() {
		name = "Clam Pizza2";
		dough = "Thin crust";
		sauce = "White garlic sauce";
		toppings.add("Clams");
		toppings.add("Grated parmesan cheese");
	}
}
class PepperoniPizza2 extends Pizza2 {
	public PepperoniPizza2() {
		name = "Pepperoni Pizza2";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Sliced Pepperoni");
		toppings.add("Sliced Onion");
		toppings.add("Grated parmesan cheese");
	}
}
class VeggiePizza2 extends Pizza2 {
	public VeggiePizza2() {
		name = "Veggie Pizza2";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Shredded mozzarella");
		toppings.add("Grated parmesan");
		toppings.add("Diced onion");
		toppings.add("Sliced mushrooms");
		toppings.add("Sliced red pepper");
		toppings.add("Sliced black olives");
	}
}

class SimplePizzaFactory {

	public Pizza2 createPizza(String type) {
		Pizza2 pizza = null;

		if (type.equals("cheese")) {
			pizza = new CheesePizza2();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza2();
		} else if (type.equals("clam")) {
			pizza = new ClamPizza2();
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza2();
		}
		return pizza;
	}
}

class PizzaStore2 {
	SimplePizzaFactory factory;
 
	public PizzaStore2(SimplePizzaFactory factory) { 
		this.factory = factory;
	}
 
	public Pizza2 orderPizza(String type) {
		Pizza2 pizza;
 
		pizza = factory.createPizza(type);
 
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}

public class SimpleFactoryPattern {
	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore2 store = new PizzaStore2(factory);

		Pizza2 pizza = store.orderPizza("cheese");
		System.out.println("We ordered a " + pizza.getName() + "\n");
 
		pizza = store.orderPizza("veggie");
		System.out.println("We ordered a " + pizza.getName() + "\n");
	}
}
