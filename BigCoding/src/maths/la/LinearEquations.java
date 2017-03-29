package maths.la;

import java.util.Arrays;

public class LinearEquations {
	public static void main(String[] args) {
		String str = "6x+2y-3z=1;-x+y-z=-1;x+y+z=1";
		String[] eqns = str.split(";");
		int[][] augmentedMatrix = new int[eqns.length][];
		for(int i=0;i<eqns.length;i++) {
			eqns[i] = eqns[i].replaceAll("(\\w)\\-([\\d+]?[a-z])", "$1+-$2");
			eqns[i] = eqns[i].replaceAll("\\+(\\-?)([a-z])", "+$11$2");
			if(eqns[i].substring(0, 1).matches("([a-z])")) {
				eqns[i] = "1" + eqns[i];
				System.out.println("I was here!");
			}
			if(eqns[i].substring(0, 1).matches("(\\-[a-z])"))
				eqns[i] = "-1" + eqns[i].substring(1, eqns[i].length()-1);
			System.out.println(eqns[i]);
			String[] coefficients = eqns[i].split("[\\+\\=]");
			System.out.println(Arrays.toString(coefficients));
		}
		//solve by rref
	}
}
