package java.designPatterns.ClassCode;

class Singleton {
	private volatile static Singleton uniqueInstance;
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}

public class SingletonPattern {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
	}
}
