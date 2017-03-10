package start;
import algo.dac.*;
import algo.Backtracking;
import algo.generalPrinciples.GeneralPrinciples;


import java.util.*;

public class ALGO {
	private Scanner sc = new Scanner(System.in);
	public void execute(){
		//int i,j,k;
		System.out.print("\nALGO\n====\n1. General Principles\t2. DAC\t3. Optimization/Backtracking\t-1. STOP\nEnter your choice : ");
		int code = sc.nextInt();
		System.out.println(code);
		while(code != -1){
			switch(code){
				case 1:
					System.out.println("Basic Lessons\n==========");
					System.out.println("Brute Force OR first solution you will solve in real life \n\t(what you can do- you can explain (give a method for); what you can explain, you can code");
					System.out.println("Reduction\n\t((sort or reducing problem to another problem whose solution is known OR using subproblems as a substep (ladder) to current solution (eg-sorting would make it easy)))");		
					System.out.println("Minimizing effort : avoiding repetetive (or extra/unnecessary) work");
					GeneralPrinciples gp = new GeneralPrinciples(); 
					gp.execute();
					break;
				case 2:
					System.out.println("DAC : ");
					DAC dac = new DAC();
					dac.execute();
					break;
				case 3:
					System.out.println("Combinations and Optimization : \n\tGreedy Approach (wisely chooses current best option- not tree) ; \n\tBacktracking : Branch and Bound (Pruning thru cost function), Dynamic Programming (remember previous solutions; smart thru greedy)");
					System.out.println("Focus on how final solution may look : Uses combinations and optimization");
					Backtracking bt = new Backtracking();
					bt.execute();
					break;
				default:
					System.out.println("Wrong Value");
					break;
			}
			System.out.print("\nALGO\n====\n1. General Principles\t2. DAC\t3. Optimization/Backtracking\t-1. STOP\nEnter your choice : ");
			code = sc.nextInt();
		}
		//sc.close();		//sc.close() will close the input stream and thus cause NoSuchElementException
	}
}
