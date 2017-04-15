/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
*/
package system.cn.socketProgrammingProject;



import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class UDPClientProject {

    static Scanner sc1 = new Scanner(System.in);

    public static void run() throws Exception{
        
         System.out.println("Connection established successfully with UDP Server....");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("192.168.43.145");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        System.out.println(" UDP Client Running ");
        System.out.println("Enter the functionality ");
        System.out.println("Press 1 for Network Identification ");
        System.out.println("Press 2 for Sorting Function ");
        int ch = sc1.nextInt();

        if (ch == 1) {
            String client_first_ip = "192.126.0.29";//inFromUser.readLine();
            String client_second_ip = "192.127.83.29";//inFromUser.readLine();
            String netmask = "255.255.0.0";//inFromUser.readLine();
            // String client_first_ip = "192.168.1.1";
            String send_ip = "\n" + Integer.toString(ch) + "\n" + client_first_ip + "\n" + client_second_ip + "\n" + netmask + "\n";
            sendData = send_ip.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String val_mod = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + val_mod);
            clientSocket.close();

        } else if (ch == 2) {

            ArrayList<Integer> list = new ArrayList<Integer>();
            System.out.println("Enter the numbers!!");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while ((input = br.readLine()) != null && input.length() != 0) {       //sc.nextLine();
                list.add(Integer.parseInt(input));
            }

            System.out.println("Input taken!\n Printing input");

            for (Integer i : list) {
                System.out.print(i);
                System.out.print(" ");
            }

            String send_ip = "\n" + Integer.toString(ch) + "\n" + list.toString();

            sendData = send_ip.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String val_mod = new String(receivePacket.getData());
            //  System.out.println("FROM SERVER:" + val_mod);

            System.out.println("Sorted Output\n");
            // String msg = br1.readLine();
            val_mod = removeChar(val_mod, ' ');
            int start_index = val_mod.indexOf('['), end_index = val_mod.indexOf(']');

            String s1 = val_mod.substring(start_index + 1, end_index);

          //  System.out.println("Latest String");
            //  System.out.println(s1);
            List<String> list1 = new ArrayList<String>(Arrays.asList(s1.split(",")));

            for (String item : list1) {
                System.out.print(Integer.parseInt(item));
                System.out.print(" ");
            }
            System.out.println("Sorting Functionality Over!");
            //  System.out.println(msg);
            // System.out.println("Client 7 Mai yaha  bhi hoon!");

          
        }
  clientSocket.close();
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
