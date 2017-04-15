package start;

import java.util.Scanner;

import dbms.DirectoriesRW;
import dbms.fileReads.FileStreams;
import dbms.fileReads.ParseXML;
import dbms.rdbms.mysql;

public class IO {
	private Scanner sc = new Scanner(System.in);
	public void execute(){
		int code;
		System.out.print("\nIO\n===\n1. File Handling\t2. Directories Handling\t3. RDB\t4. XML\t-1. STOP\nEnter the Code : ");
		code = sc.nextInt();
		
		while(code != -1)
		{
			switch(code){
			case 1:
				System.out.println("File Handling");
				FileStreams fs = new FileStreams();
				try {fs.execute();}catch(Exception e){}
				break;
			case 2:
				System.out.println("Directories Handling");
				new DirectoriesRW().execute();
				break;
			case 3:
				System.out.println("RDBMS Handling");
				@SuppressWarnings("unused") mysql rdb = new mysql();
				//rdb.execute();
				break;
			case 4:
				System.out.println("XML Handling");
				/*ReadWriteXML rwx = new ReadWriteXML();
				rwx.execute();
				XMLXPath xx = new XMLXPath();
				xx.execute();*/
				ParseXML px = new ParseXML();
				px.execute();
				break;
			default:
				System.out.println("Wrong Input");
			}
			System.out.print("\nIO\n===\n1. File Handling\t2. Directories Handling\t3. RDB\t4. XML\t-1. STOP\nEnter the Code : ");
			code = sc.nextInt();
		}
	}
}
