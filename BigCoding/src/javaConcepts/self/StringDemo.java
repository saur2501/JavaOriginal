package javaConcepts.self;

public class StringDemo {
	public static void main(String[] args) {
		String name1 = new String("Vinod");
		System.out.println(name1.toUpperCase() + name1);
		String name2= "Vinod";
		String name3 = "Vin" + "od";
		if(name2 == name3) {System.out.println("Interns are used.");}
		String rev = null;
		for(int i=name1.length()-1;i>=0;--i)
			rev += name1.charAt(i);
		System.out.println(rev);
		if(name1.compareTo(name2) < 0)
			System.out.println("name1 is less than name2");
		else if(name1.equals(name3))	//overridden of object class; equalsIgnoreCase(String)
			System.out.println("name1 = name3");
		System.out.println(name1.concat(" Hi"));
		if(name1.indexOf('@') == -1) System.out.println("Not a valid email");
		System.out.println(name2.substring(2));
	}
}
