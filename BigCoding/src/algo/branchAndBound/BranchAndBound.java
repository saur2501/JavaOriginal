package algo.branchAndBound;

import java.util.Scanner;

public class BranchAndBound {
	//We have merged this (pruning) into DP. This enumerates Backtracking examples.
	Scanner sc = new Scanner(System.in);
	public void execute() {
		System.out.print("1. NQueen\nChoose your option : ");
		int code = sc.nextInt();
		while(code!=-1){
			switch(code){
			case 1:
				NQueen nqueen = new NQueen();
				nqueen.solveNQ();
				break;
			default:
				break;
			}
			System.out.print("1. NQueen\nChoose your option : ");
			code = sc.nextInt();
		}
	}
}
