package dbms.oodbms;

import java.io.*;

public class OOSerializer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public String name;
	public String address;
	public transient int SSN;
	public int number;

	public static void main(String[] args) {
		insert();
		retrieve();
	}

	public static void retrieve() {
		OOSerializer e = null;
		System.out.println("Employee retrieval starts");
		try {
			FileInputStream fileIn = new FileInputStream("newFile.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (OOSerializer) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.name);
		System.out.println("Address: " + e.address);
		System.out.println("SSN: " + e.SSN);
		System.out.println("Number: " + e.number);
	}

	public static void insert() {
		System.out.println("Insertion starts!");
		OOSerializer e = new OOSerializer();
		e.name = "Texild";
		e.address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;

		try {
			FileOutputStream fileOut = new FileOutputStream("newFile.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved as newFile.txt");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}