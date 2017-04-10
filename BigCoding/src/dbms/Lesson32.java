package dbms;
import java.io.*;
/* A character stream is just a series of characters.
 *  Important information is normally separated by a comma, space, or tab.*/

public class Lesson32{
    public static void main(String[] args) throws IOException{
        Customer[] customers = getCustomers();		//Create an array of type Customer
        PrintWriter custOutput = createFile("/Users/derekbanas/Documents/workspace3/Java Code/src/customers.txt");		//PrintWriter is used to write characters to a file in this situation
        for(Customer person : customers){			//Enhanced for loop for arrays; Cycles through all of the people in the customers array
            createCustomers(person, custOutput);
        }
        custOutput.close();							//Closes the connection to the PrintWriter
        getFileInfo();
    }

    private static class Customer{					//class that defines all the fields for my customers
        public String firstName, lastName;
        public int custAge;
        public Customer(String firstName, String lastName, int custAge){			//constructor that's called when a customer is made
            this.firstName = firstName;
            this.lastName = lastName;
            this.custAge = custAge;
        }
    }
    private static Customer[] getCustomers(){		//Creates an array of Customer Objects
        Customer[] customers = new Customer[5];
        customers[0] = new Customer("John", "Smith", 21);
        customers[1] = new Customer("Sally", "Smith", 30);
        customers[2] = new Customer("Paul", "Ryan", 21);
        customers[3] = new Customer("Mark", "Jacobs", 21);
        customers[4] = new Customer("Steve", "Nash", 21);
        return customers;
    }
    private static PrintWriter createFile(String fileName){							//Create the file and the PrintWriter that will write to the file
        try{
            File listOfNames = new File(fileName);	//Creates a File object that allows you to work with files on the hardrive		
            PrintWriter infoToWrite = new PrintWriter(new BufferedWriter(new FileWriter(listOfNames)));
            										//FileWriter is used to write streams of characters to a file. BufferedWriter gathers a bunch of characters and then writes them all at one time (Speeds up the Program). PrintWriter is used to write characters to the console, file
            return infoToWrite;
        }
        catch(IOException e){						//You have to catch this when you call FileWriter
            System.out.println("An I/O Error Occurred");
            System.exit(0);							//Closes the program
        }
        return null;
    }

    private static void createCustomers(Customer customer, PrintWriter custOutput){		//Create a string with the customer info and write it to the file
        String custInfo = customer.firstName + " " + customer.lastName + " ";			//Create the String that contains the customer info
        custInfo += Integer.toString(customer.custAge);
        custOutput.println(custInfo);				//Writes the string to the file
    }

    private static void getFileInfo() throws IOException{				//Read info from the file and write it to the screen
        System.out.println("Info Written to File\n");
        File listOfNames = new File("/Users/derekbanas/Documents/workspace3/Java Code/src/customers.txt");		//Open a new connection to the file
        BufferedReader getInfo = null;
        try {
            getInfo = new BufferedReader(new FileReader(listOfNames));	//FileReader reads character files. BufferedReader reads as many characters as possible
            String custInfo = getInfo.readLine();	//Reads a whole line from the file and saves it in a String
            while(custInfo != null){				//readLine returns null when the end of the file is reached
                // System.out.println(custInfo);
                String[] indivCustData = custInfo.split(" ");							//Break lines into pieces
                int custAge = Integer.parseInt(indivCustData[2]);						//Convert the String into an integer with parseInt
                System.out.print("Customer " + indivCustData[0] + " is " + custAge +"\n");
                custInfo = getInfo.readLine();
            }
        }
    	catch (FileNotFoundException e) {			//Can be thrown by FileReader
            System.out.println("Couldn't Find the File");
            System.exit(0);
        }
        catch(IOException e){
            System.out.println("An I/O Error Occurred");
            System.exit(0);
        } finally {
        	getInfo.close();
        }
    }
}
