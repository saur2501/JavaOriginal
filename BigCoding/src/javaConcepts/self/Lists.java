package javaConcepts.self;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lists {
	private static List<String> ll = new LinkedList<>();
	public static void main(String[] args) {
		List<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(1, 2);
		ll.add("Amit");  
		ll.add("Vijay");  
		ll.add("Kumar");  
		ll.add(1,"Sachin");  
		System.out.println("Element at 2nd position: "+ll.get(2));  
		for(String s : ll){  
		 System.out.println(s);  
		}  
	}

}
