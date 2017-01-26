package algo.dac;

import java.util.*;

public class DAC {
	private Scanner sc = new Scanner(System.in);
	public void execute(){
		System.out.print("\nDAC\n===\n1. Sorting\t2. InversionPairs\t3. Closest Pair Points\t4. Towers of Hanoi\nEnter your choice : ");
		int code2 = sc.nextInt();
		while(code2 != -1){
			switch(code2){
			case 1:
				System.out.println("Sorting Routines");
				int startArray[] = {12,9,4,99,120,1,3,10};
				SortingRoutines sr = new SortingRoutines(startArray);
				sr.execute();
				break;
			case 2:
				int[] arr = new int[]{1,2,4,3,6,5,9,8};
				InversionPairs ip = new InversionPairs(arr);
				int count = ip.inversionPair(0, arr.length-1);
				System.out.println("Number of inversion pairs in here = "+count);
				break;
			case 3:
			{
		        // Load your own data for testing       
		        P[] points = new P[] { new P(2, 7), new P(4, 13), new P(5, 7),new P(10, 5),
		                new P(13, 9), new P(15, 5), new P(17, 7), new P(19, 10), 
		                new P(22, 7), new P(25, 10), new P(29, 14), new P(30, 2) };
		                 
		       ClosestPair cp = new ClosestPair();
		       Arrays.sort(points, cp.xComparator); // sort by x, then y
		                     
		        P[] closest = null;
				try {
					closest = cp.findClosest(points);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}      
		        P[] closestx = cp.findMinDist( cp.findMinDistNeighbor(points),closest );      
		                 
		        for (P p : closestx)
		            System.out.println(p);
		        System.out.println("dist: "+cp.distance(closestx[0],closestx[1]));                 
		    }
				break;
			case 4:
				System.out.println("DAC thru Induction (technically not true)");
				TowersOfHanoi towersOfHanoi = new TowersOfHanoi();
				System.out.print("Enter number of discs: ");
				int discs = sc.nextInt();
				towersOfHanoi.solve(discs, "A", "B", "C");
				break;
			default:
				System.out.println("Wrong Entry");
				break;
			}
			System.out.print("\nDAC\n===\n1. Sorting\t2. InversionPairs\t3. Closest Pair Points\t4. Towers of Hanoi\nEnter your choice : ");
			code2 = sc.nextInt();
		}
	}
}
