
/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
*/
package system.cn.socketProgramming;


import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.sort;
import java.util.List;
import java.util.Scanner;

public class UDPServer {

    static Scanner sc1 = new Scanner(System.in);

    public static void run() throws Exception {
       
        
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        int ch;
       // while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String msg = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + msg);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            //String capitalizedSentence = msg.toUpperCase();

            //  System.out.println("Server 1a Mai yaha  bhi hoon!" + msg);
            // msg =  br.readLine();
            //  msg =  br.readLine();
            // System.out.println("Server 1b Mai yaha  bhi hoon!"+msg);
            //String[] ip = new String[4];
            String[] msg1 = msg.split("\\r?\\n");
            ch = Integer.parseInt(msg1[1]);

            if (ch == 1) {

                //String[] msg1 = msg.split("\\r?\\n");
                //  String ip2 = msg1[0];//..split("\\.");
                System.out.println("Split IP 1--> " + msg1[2]);
                System.out.println("Split IP 2--> " + msg1[3]);
                System.out.println("Split IP 3--> " + msg1[4]);
                String[] ip0 = msg1[2].split("\\.");
                System.out.println("First Ip--> " + Arrays.toString(ip0));

                String[] ip1 = msg1[3].split("\\.");
                System.out.println("Second Ip-->" + Arrays.toString(ip1));
                // msg = br.readLine();
                //  System.out.println("Message--> " + msg);
                //String[] netmask = new String[4];
                String[] netmask = msg1[4].split("\\.");
                int[] netid1 = new int[4];
                int[] netid2 = new int[4];
                for (int i = 0; i < 4; i++) {
                    netid1[i] = Integer.parseInt(ip0[i]) & Integer.parseInt(netmask[i]);
                    //System.out.println("Computation for first Ip -->"+netid1[i]);
                    netid2[i] = Integer.parseInt(ip1[i]) & Integer.parseInt(netmask[i]);
                }

//        ps.println("The Network Id  for 1st ip are : ");
//        for(int i = 0;i<4;i++)
//        {
//            ps.println(" "+Integer.toString(netid1[i])+".");    
//            System.out.println("Computation for first Ip -->"+netid1[i]);    
//        }
//         ps.println("The Network Id  for 2nd ip are : ");
//         for(int i = 0;i<4;i++)
//        {
//            ps.println(" "+Integer.toString(netid2[i])+".");   
//            System.out.println("Computation for second Ip -->"+netid2[i]);    
//        }
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
//            ps.println(val);
//            System.out.println(msg + " Olllalaall");    

                sendData = val.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } else if (ch == 2) {
                // System.out.println("Main yahhan hoon!!!!!!!!!!!");

                System.out.println(" Received Message!");
                System.out.println(msg1[2]);

                //  ArrayList<Integer> list = new ArrayList<Integer>();
                msg1[2] = removeChar(msg1[2], ' ');
                int start_index = msg1[2].indexOf('['), end_index = msg1[2].indexOf(']');

                String s1 = msg1[2].substring(start_index + 1, end_index);

                System.out.println("Latest String");
                System.out.println(s1);
                List<String> list = new ArrayList<String>(Arrays.asList(s1.split(",")));
                ArrayList<Integer> l = new ArrayList<Integer>();
                for (String item : list) {
                    System.out.print(Integer.parseInt(item));
                    l.add(Integer.parseInt(item));
                    System.out.print(" ");
                }
                //  for (String item : list) {
                //         list.add(Integer.parseInt(item));
                //    }
                sort(l);
                String val = "" + l.toString();
                System.out.println("\nSorted List at Server ");
                for (Integer item : l) {
                    System.out.print(item);
                    System.out.print(" ");
                }
                System.out.println(" Final_ Value -->");
                System.out.println(val);
                sendData = val.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);

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
