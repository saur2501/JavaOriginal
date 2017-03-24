package dsa.algo;

import java.util.*;

import dsa.algo.bab.BranchAndBound;
import dsa.algo.dp.DP;
import dsa.algo.greedy.Greedy;

public class Backtracking {
	private Scanner sc= new Scanner(System.in);
	public void execute(){
		System.out.print("\nBacktracking\n========\n1. Greedy\t2. Dynamic Programming\t3. Branch and Bound (pruning)\tGive your input : ");
		int code2 = sc.nextInt();
		while(code2 != -1){
			switch(code2){
			case 1:
				System.out.println("Greedy!!");
				Greedy g = new Greedy();
				g.execute();
				break;
			case 2:
				DP dp = new DP();
				dp.execute();
				break;
			case 3:
				BranchAndBound bab = new BranchAndBound();
				bab.execute();
				break;
			default:
				break;
			}
			System.out.print("\nBacktracking\n========\n1. Greedy\t2. Dynamic Programming\t3. Branch and Bound (pruning)\tGive your input : ");
			code2 = sc.nextInt();
		}
	}
}
