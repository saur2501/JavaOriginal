package start;

import java.util.Scanner;

public class DSA {
	public void execute(){
		Scanner sc = new Scanner(System.in);
		int code;
		System.out.print("\nDSA\n===\n1. ADT\t2. DS\t3. Algo\t-1. STOP\nEnter the Code : ");
		code = sc.nextInt();
		
		while(code != -1){
			switch(code){
			case 1:
				ADT adt = new ADT();
				adt.execute();
				break;
			case 2:
				DS ds = new DS();
				ds.execute();
				break;
			case 3:
				ALGO algo = new ALGO();
				algo.execute();
				break;
			default:
				System.out.println("Wrong Input");
			}
			System.out.print("\nDSA\n===\n1. ADT\t2. DS\t3. Algo\t-1. STOP\nEnter the Code : ");
			code = sc.nextInt();
		}
		//sc.close();
	}
}
