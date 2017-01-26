package algo.greedy;

import java.util.Arrays;
import java.util.*;

import algo.greedy.GreedySortingRoutines;
import algo.greedy.JobSequencing;

public class Greedy {
	private Scanner sc= new Scanner(System.in);
	public void execute(){
		System.out.print("\nWelcome to Greedy!!\n1. Sorting Routines\t2. Job Sequencing\tGive your input : ");
		int code2 = sc.nextInt(), code3;
		GreedySortingRoutines gsr;
		while(code2 != -1){
			switch(code2){
			case 1:
				System.out.print("1. Selection Sort\t2. Bubble Sort\nInput:");
				code3 = sc.nextInt();
				while(code3 != -1){
					gsr = new GreedySortingRoutines(new int[]{10,7,92,106,54,108,32});
					int[] result = null;
					switch(code3){
					case 1:
						result = gsr.selectionSort();
						System.out.println("After Selection Sort");
						break;
					case 2:
						result = gsr.bubbleSort();
						System.out.println("After Bubble Sort");
						break;
					default:
						break;
					}
					System.out.println(Arrays.toString(result));
					System.out.println("1. Selection Sort\t2. Bubble Sort");
					code3 = sc.nextInt();	
				}
				break;
			case 2:
				JobSequencing js = new JobSequencing();
				js.scheduling();
				break;
			case 3:
				JobSequencing js1 = new JobSequencing();
				js1.schedulingOnDeadline();
				break;
			default:
				break;
			}
			System.out.print("\nWelcome to Greedy!!\n1. Sorting Routines\t2. Job Sequencing\tGive your input : ");
			code2 = sc.nextInt();
		}
	}
}
