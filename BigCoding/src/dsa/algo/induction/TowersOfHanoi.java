package dsa.algo.induction;

import java.util.Scanner;

public class TowersOfHanoi {
	Scanner sc = new Scanner(System.in);
	public void solve(int n, String start, String auxiliary, String end) {
	    if (n == 1) {
	    	System.out.println(start + " -> " + end);
	    } else {
	    	solve(n - 1, start, end, auxiliary);
	        System.out.println(start + " -> " + end);
	        //we can construct a diagram here to spice things up
	        solve(n - 1, auxiliary, start, end);
	    }
	}

	public void find() {
		
	}
}
