/*

 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101         

 */
package system.cn.socketProgramming;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TCPClient {

    static Scanner sc1 = new Scanner(System.in);

    public static void run() throws Exception {

        {
            System.out.println("Connection established successfully with TCP Server....");
            Socket soc = new Socket("192.168.43.145", 53245);
            //  Socket soc = new Socket("localhost", 53245);
            // System.out.println("TCPClient 1 Mai yaha  bhi hoon!");
            PrintStream ps = new PrintStream(soc.getOutputStream());
            // System.out.println("TCPClient 2 Mai yaha  bhi hoon!");
            System.out.println("Enter the functionality ");
            System.out.println("Press 1 for Network Identification ");
            System.out.println("Press 2 for Sorting Function ");
            int ch = sc1.nextInt();

            if (ch == 1) {

                // Sending choice towards Server
                ps.print(ch);

                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                // We have hardcoded the two ip's and netmask -- we can take the input from user as well
               // String client_first_ip = inFromUser.readLine();
                String client_first_ip = "192.126.0.29";//inFromUser.readLine();
                //String client_second_ip = inFromUser.readLine();
                String client_second_ip ="192.127.83.29";//inFromUser.readLine();
                //String netmask = inFromUser.readLine();
                String netmask = "255.255.0.0";//inFromUser.readLine();
                // String client_first_ip = "192.168.1.1";
                
                // We combine the choice given by user and the three IP addresses into a single 
                // string send_ips and send it to server using created PrintStream
                String send_ips = "\n" + Integer.toString(ch) + "\n" + client_first_ip + "\n" + client_second_ip + "\n" + netmask + "\n";
                ps.print(send_ips);
                
               // Reading the received input from the server at the specified socket soc
                InputStreamReader isr = new InputStreamReader(soc.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String msg = br.readLine();
                System.out.println(msg);

            } else if (ch == 2) {
                
                ps.println(ch); // Again we pass the choice to print Stream
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Integer> list = new ArrayList<Integer>(); // Create Arraylist of Integers - list

                
                System.out.println("Enter the numbers!!");
                Scanner sc = new Scanner(System.in);
                 String input;
                // Read till endline separated values  as strings and store them into the list
                while ((input = br.readLine()) != null && input.length() != 0) {       //sc.nextLine();
                    list.add(Integer.parseInt(input));
                }

                ps.println(list); // Pass this integer list as string to server
                
              // Reading the received input from the server at the specified socket soc

                InputStreamReader isr = new InputStreamReader(soc.getInputStream());
                BufferedReader br1 = new BufferedReader(isr);

                System.out.println("Sorted Output\n");
                
                // In string msg we store the values space
                String msg = br1.readLine();
                msg = removeChar(msg, ' ');
                int start_index = msg.indexOf('['), end_index = msg.indexOf(']');

                String s1 = msg.substring(start_index + 1, end_index);

                // System.out.println("Latest String");
                // System.out.println(s1);
                List<String> list1 = new ArrayList<String>(Arrays.asList(s1.split(",")));

                for (String item : list1) {
                    System.out.print(Integer.parseInt(item));
                    System.out.print(" ");
                }
                System.out.println("\nSorting Functionality Over!");
                //System.out.println(msg);
                // System.out.println("TCPClient 7 Mai yaha  bhi hoon!");
            }
        }

//          //  ps.print("192.127.83.29" + "\n" + "255.255.0.0\n");
//            System.out.println("TCPClient 3 Mai yaha  bhi hoon!");
//            //InputStreamReader isr = new InputStreamReader(soc.getInputStream());
//            System.out.println("TCPClient 4 Mai yaha  bhi hoon!");
//            //BufferedReader br = new BufferedReader(isr);
//            System.out.println("TCPClient 5 Mai yaha  bhi hoon!");
//            //String msg = br.readLine();
//            System.out.println("TCPClient 6 Mai yaha  bhi hoon!");
//            //System.out.println(msg);
//            System.out.println("TCPClient 7 Mai yaha  bhi hoon!");
    }

    public static String removeChar(String s, char c) {
        StringBuilder buf = new StringBuilder(s.length());
        buf.setLength(s.length());
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur != c) {
                buf.setCharAt(current++, cur);
            }
        }
        return buf.toString();
    }
}
