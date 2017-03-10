package start;

import java.util.Scanner;
import dbms.*;

public class Start {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int code;
		JsonEncodeDemo jed = new JsonEncodeDemo();
		jed.write();
		jed.read();
		if(true)
			return;
		System.out.print("\nStart\n=====\n1. DSA\t2. IO\t3. Java\t4. Subjects\t-1. STOP\nEnter the Code : ");
		code = sc.nextInt();
		
		while(code != -1)
		{
			switch(code){
			case 1:
				DSA dsa = new DSA();
				dsa.execute();
				break;
			case 2:
				IO io = new IO();
				io.execute();
				break;
			case 3:
				JAVA java = new JAVA();
				java.execute();
				break;
			case 4:
				CSSubjects cs = new CSSubjects();
				cs.execute();
				break;
			default:
				System.out.println("Wrong Input");
			}
			System.out.print("\nStart\n=====\n1. DSA\t2. IO\t3. Java\t4. Subjects\t-1. STOP\nEnter the Code : ");
			code = sc.nextInt();
		}
		//sc.close();
	}
}
