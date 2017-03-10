package cn.socketProgramming;

import java.util.Scanner;


/*
ABHINAV SHRIVASTAVA - MT2015003
SAURABH DEVGUN - MT2015101

Step 1) Run TCP Server from RUN_TCP_SERVER.java
Step 2) Run TCP Server from RUN_UDP_SERVER.java
Step 3) Run Client choice file from CLIENT_CHOICE_FOR_UDP_OR_TCP


 */
public class CLIENT_CHOICE_FOR_UDP_OR_TCP {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        System.out.println("Please Choose the Protocol : ");
        System.out.println("Press 1 for TCP Client ");
        System.out.println("Press 2 for UDP Client");
        Scanner sc = new Scanner(System.in);
        int choice;
        choice = sc.nextInt(); // Taking choice as input from User about the client

        if (choice == 1) {
            // Innvoking TCP
            System.out.println("Invoking TCP Client....");
            // TCPClient tcpclient = new TCPClient();
            System.out.println("Eastablishing connection with TCP Server....");
            TCPClient.run();

            //System.out.println("TCP Client Running....");

        } else if (choice == 2) {
            System.out.println("Invoking UDP Client....");
            //udpclient = new UDPClient();
            UDPClient.run();

        } else {
            System.out.println("Invalid Choice!");
        }





    }
}
