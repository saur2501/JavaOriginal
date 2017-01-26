package java.designPatterns.ClassCode;

import java.util.Random;

interface Ducky {
	public void quack();
	public void fly();
}
class DuckAdapter implements Turkey {
	Ducky duck;
	Random rand;
 
	public DuckAdapter(Ducky duck) {
		this.duck = duck;
		rand = new Random();
	}
    
	public void gobble() {
		duck.quack();
	}
  
	public void fly() {
		if (rand.nextInt(5)  == 0) {
		     duck.fly();
		}
	}
}
class MallardDucky implements Ducky {
	public void quack() {
		System.out.println("Quack");
	}
 
	public void fly() {
		System.out.println("I'm flying");
	}
}

interface Turkey {
	public void gobble();
	public void fly();
}

class WildTurkey implements Turkey {
	public void gobble() {
		System.out.println("Gobble gobble");
	}

	public void fly() {
		System.out.println("I'm flying a short distance");
	}
}

class TurkeyAdapter implements Ducky {
	Turkey turkey;
 
	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
    
	public void quack() {
		turkey.gobble();
	}
  
	public void fly() {
		for(int i=0; i < 5; i++) {
			turkey.fly();
		}
	}
}

public class AdapterPattern {
	public static void main(String[] args) {
		MallardDucky duck = new MallardDucky();
 
		WildTurkey turkey = new WildTurkey();
		Ducky turkeyAdapter = new TurkeyAdapter(turkey);
   
		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();
 
		System.out.println("\nThe Ducky says...");
		testDuck(duck);
  
		System.out.println("\nThe TurkeyAdapter says...");
		testDuck(turkeyAdapter);
		
		///////////
		MallardDucky duck1 = new MallardDucky();
		Turkey duckAdapter = new DuckAdapter(duck1);
 
		for(int i=0;i<10;i++) {
			System.out.println("The DuckAdapter says...");
			duckAdapter.gobble();
			duckAdapter.fly();
		}
	}
 
	static void testDuck(Ducky duck) {
		duck.quack();
		duck.fly();
	}
}
