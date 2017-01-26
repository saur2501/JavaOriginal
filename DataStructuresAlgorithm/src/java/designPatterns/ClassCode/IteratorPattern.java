package java.designPatterns.ClassCode;

import java.util.ArrayList;
import java.util.Calendar;

interface Iterator {
	boolean hasNext();
	Object next();
}
class AlternatingDinerMenuIterator implements Iterator {
	DishItem[] list;
	int position;

	public AlternatingDinerMenuIterator(DishItem[] list) {
		this.list = list;
		Calendar rightNow = Calendar.getInstance();
		position = rightNow.DAY_OF_WEEK % 2;
	}
	public Object next() {
		DishItem dishItem = list[position];
		position = position + 2;
		return dishItem;
	}
	public boolean hasNext() {
		if (position >= list.length || list[position] == null) {
			return false;
		} else {
			return true;
		}
	}
	public String toString() {
		return "Alternating Diner Dish Iterator";
	}
}
class ArrayIterator implements Iterator {
	DishItem[] items;
	int position = 0;
	public ArrayIterator(DishItem[] items) {
		this.items = items;
	}
 
	public Object next() {
		DishItem dishItem = items[position];
		position = position + 1;
		return dishItem;
	}
 
	public boolean hasNext() {
		if (position >= items.length || items[position] == null) {
			return false;
		} else {
			return true;
		}
	}
}
class ArrayListIterator implements Iterator {
	ArrayList items;
	int position = 0;
	public ArrayListIterator(ArrayList items) {
		this.items = items;
	}
 
	public Object next() {
		Object object = items.get(position);
		position = position + 1;
		return object;
	}
 
	public boolean hasNext() {
		if (position >= items.size()) {
			return false;
		} else {
			return true;
		}
	}
}

class DinerMenuIterator implements Iterator {
	DishItem[] items;
	int position = 0;
 
	public DinerMenuIterator(DishItem[] items) {
		this.items = items;
	}
 
	public Object next() {
		DishItem dishItem = items[position];
		position = position + 1;
		return dishItem;
	}
 
	public boolean hasNext() {
		if (position >= items.length || items[position] == null) {
			return false;
		} else {
			return true;
		}
	}
}
class PancakeHouseMenuIterator implements Iterator {
	ArrayList items;
	int position = 0;
	public PancakeHouseMenuIterator(ArrayList items) {
		this.items = items;
	}
	public Object next() {
		Object object = items.get(position);
		position = position + 1;
		return object;
	}
	public boolean hasNext() {
		if (position >= items.size()) {
			return false;
		} else {
			return true;
		}
	}
}

class DishItem {
	String name;
	String description;
	boolean vegetarian;
	double price;
	public DishItem(String name, String description, boolean vegetarian, double price) {
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public double getPrice() {
		return price;
	}
	public boolean isVegetarian() {
		return vegetarian;
	}
	public String toString() {
		return (name + ", $" + price + "\n   " + description);
	}
}

interface Dish {
	public Iterator createIterator();
}

class DinerMenu implements Dish {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	DishItem[] menuItems;
  
	public DinerMenu() {
		menuItems = new DishItem[MAX_ITEMS];
 
		addItem("Vegetarian BLT",
			"(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99);
		addItem("BLT",
			"Bacon with lettuce & tomato on whole wheat", false, 2.99);
		addItem("Soup of the day",
			"Soup of the day, with a side of potato salad", false, 3.29);
		addItem("Hotdog",
			"A hot dog, with saurkraut, relish, onions, topped with cheese",
			false, 3.05);
		addItem("Steamed Veggies and Brown Rice",
			"Steamed vegetables over brown rice", true, 3.99);
		addItem("Pasta",
			"Spaghetti with Marinara Sauce, and a slice of sourdough bread",
			true, 3.89);
	}
  
	public void addItem(String name, String description, boolean vegetarian, double price) {
		DishItem dishItem = new DishItem(name, description, vegetarian, price);
		if (numberOfItems >= MAX_ITEMS) {
			System.err.println("Sorry, menu is full!  Can't add item to menu");
		} else {
			menuItems[numberOfItems] = dishItem;
			numberOfItems = numberOfItems + 1;
		}
	}
	public DishItem[] getMenuItems() {
		return menuItems;
	}
	public Iterator createIterator() {
		return new DinerMenuIterator(menuItems);
	}
	// other menu methods here
}
class PancakeHouseMenu implements Dish {
	ArrayList menuItems;
	public PancakeHouseMenu() {
		menuItems = new ArrayList();
		addItem("K&B's Pancake Breakfast", 
			"Pancakes with scrambled eggs, and toast", 
			true,
			2.99);
 
		addItem("Regular Pancake Breakfast", 
			"Pancakes with fried eggs, sausage", 
			false,
			2.99);
		addItem("Blueberry Pancakes",
			"Pancakes made with fresh blueberries",
			true,
			3.49);
		addItem("Waffles",
			"Waffles, with your choice of blueberries or strawberries",
			true,
			3.59);
	}
	public void addItem(String name, String description, boolean vegetarian, double price) {
		DishItem dishItem = new DishItem(name, description, vegetarian, price);
		menuItems.add(dishItem);
	}
	public ArrayList getMenuItems() {
		return menuItems;
	}
	public Iterator createIterator() {
		return new PancakeHouseMenuIterator(menuItems);
	}
	public String toString() {
		return "Objectville Pancake House Dish";
	}
	// other menu methods here
}

class Waiter {
	PancakeHouseMenu pancakeHouseMenu;
	DinerMenu dinerMenu;
	public Waiter(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}
	public void printMenu() {
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerIterator = dinerMenu.createIterator();

		System.out.println("MENU\n----\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerIterator);
	}
	private void printMenu(Iterator iterator) {
		while (iterator.hasNext()) {
			DishItem dishItem = (DishItem)iterator.next();
			System.out.print(dishItem.getName() + ", ");
			System.out.print(dishItem.getPrice() + " -- ");
			System.out.println(dishItem.getDescription());
		}
	}
	public void printVegetarianMenu() {
		printVegetarianMenu(pancakeHouseMenu.createIterator());
		printVegetarianMenu(dinerMenu.createIterator());
	}
	public boolean isItemVegetarian(String name) {
		Iterator breakfastIterator = pancakeHouseMenu.createIterator();
		if (isVegetarian(name, breakfastIterator)) {
			return true;
		}
		Iterator dinnerIterator = dinerMenu.createIterator();
		if (isVegetarian(name, dinnerIterator)) {
			return true;
		}
		return false;
	}
	private void printVegetarianMenu(Iterator iterator) {
		while (iterator.hasNext()) {
			DishItem dishItem = (DishItem)iterator.next();
			if (dishItem.isVegetarian()) {
				System.out.print(dishItem.getName());
				System.out.println("\t\t" + dishItem.getPrice());
				System.out.println("\t" + dishItem.getDescription());
			}
		}
	}

	private boolean isVegetarian(String name, Iterator iterator) {
		while (iterator.hasNext()) {
			DishItem dishItem = (DishItem)iterator.next();
			if (dishItem.getName().equals(name)) {
				if (dishItem.isVegetarian()) {
					return true;
				}
			}
		}
		return false;
	}
}

public class IteratorPattern {
	public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
		Waiter waiter = new Waiter(pancakeHouseMenu, dinerMenu); 
		waiter.printMenu();
	}

	public static void printMenu() {
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		DinerMenu dinerMenu = new DinerMenu();
		ArrayList breakfastItems = pancakeHouseMenu.getMenuItems();
		for (int i = 0; i < breakfastItems.size(); i++) {
			DishItem dishItem = (DishItem)breakfastItems.get(i);
			System.out.print(dishItem.getName());
			System.out.println("\t\t" + dishItem.getPrice());
			System.out.println("\t" + dishItem.getDescription());
		}
		DishItem[] lunchItems = dinerMenu.getMenuItems();
		for (int i = 0; i < lunchItems.length; i++) {
			DishItem dishItem = lunchItems[i];
			System.out.print(dishItem.getName());
			System.out.println("\t\t" + dishItem.getPrice());
			System.out.println("\t" + dishItem.getDescription());
		}
	}
}
