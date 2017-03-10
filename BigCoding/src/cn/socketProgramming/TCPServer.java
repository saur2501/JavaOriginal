/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
 */
package cn.socketProgramming;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.sort;
import java.util.List;

public class TCPServer {

    public static void run() throws Exception {
        //String net = "192.25.25.26";
        //  int ctr = 0;
        ServerSocket serverSocket;
        
        Socket sock;
        InputStreamReader isr;
        BufferedReader br;
        String msg;
      
        //ServerSocket class implements server sockets. A server socket waits for requests to come in over the network
       
        serverSocket = new ServerSocket(53245); //Creates a server socket, bound to the specified port.
        
        sock = serverSocket.accept();//Listens for a connection to be made to this socket and accepts it. 
                                     //The method blocks until a connection is made
        
        isr = new InputStreamReader(sock.getInputStream()); 
                                      // new InputStreamReader() creates an InputStreamReader that uses the default charset.
                                      //getInputStream() Returns an input stream for this socket.
        
        br = new BufferedReader(isr); //Creates a buffering character-input stream that uses a default-sized input buffer.
       
        msg = br.readLine(); //Reads a line of text

        // First line indicates the choice so we store that in choice_functionality integer
        int choice_functionality = Integer.parseInt(msg);

        if (choice_functionality == 1) {

            msg = br.readLine();
            msg = br.readLine();
           
           // We split each string of ip addresses into individual integers
            String[] ip0 = msg.split("\\."); // Storing individual integers in dot separated IP address string
            System.out.println("First Ip-->" + Arrays.toString(ip0));

            msg = br.readLine();
            String[] ip1 = msg.split("\\.");
            System.out.println("Second Ip-->" + Arrays.toString(ip1));

            msg = br.readLine();

            System.out.println("Netmask--> " + msg);
            String[] netmask = new String[4];
            netmask = msg.split("\\.");
            int[] netid1 = new int[4];
            int[] netid2 = new int[4];
            
            // Anding IP Address and Netmask and storing in netid[] array
            for (int i = 0; i < 4; i++) {
                netid1[i] = Integer.parseInt(ip0[i]) & Integer.parseInt(netmask[i]);
                //System.out.println("Computation for first Ip -->"+netid1[i]);
                netid2[i] = Integer.parseInt(ip1[i]) & Integer.parseInt(netmask[i]);
            }

            
            
            //A PrintStream adds functionality to another output stream, 
            //namely the ability to print representations of various data values conveniently.
           // PrintStream() Creates a new print stream. This stream will not flush automatically.
            // getOutputStream() - Returns an output stream for this socket.
            PrintStream ps = new PrintStream(sock.getOutputStream());
           
           
            boolean flag = false;
            String val = "";
            for (int i = 0; i < 4; i++) {
                if (netid1[i] != netid2[i]) {
                    val = "No Ip's are on different network";
                    flag = true;
                    break;
                }

            }
            if (flag == false) {
                val = "Yes Ip's are on same network";

            }

            ps.println(val);


        } else if (choice_functionality == 2) {
            
            
            msg = br.readLine();
            System.out.println(" Received Message!");
            System.out.println(msg);

          // We received arraylist as string 
           // Thus we have to remove the extra characters
            msg = removeChar(msg, ' ');
            int start_index = msg.indexOf('['), end_index = msg.indexOf(']');

            String s1 = msg.substring(start_index + 1, end_index);

            //System.out.println("Latest String");
           // System.out.println(s1);
            List<String> list = new ArrayList<String>(Arrays.asList(s1.split(",")));
            
            
            ArrayList<Integer> integerList = new ArrayList<Integer>();
            
            
            for (String item : list) {
                System.out.print(Integer.parseInt(item));
                integerList.add(Integer.parseInt(item));
                System.out.print(" ");
            }
            //  for (String item : list) {
            //         list.add(Integer.parseInt(item));
            //    }
            sort(integerList);
            
            System.out.println("Sorted List at Server ");
            for (Integer item : integerList) {
                System.out.print(item);
                System.out.print(" ");
            }
            
            // We convert IntegerList back to string and send it back to client
            String send_string = "" + integerList.toString();
            
            PrintStream ps = new PrintStream(sock.getOutputStream());
            ps.println(send_string);
            
            
            
        } else {
            System.out.println(" Invalid Choice");
            return;
        }

        serverSocket.close();
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

//}
