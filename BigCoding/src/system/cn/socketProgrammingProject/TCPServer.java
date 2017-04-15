package system.cn.socketProgrammingProject;

import java.net.*;
import java.io.*;

public class TCPServer extends Thread {
   private ServerSocket serverSocket;
   
   public TCPServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);		//opens a port on the machine- use netstat, telnet, etc to confirm
      serverSocket.setSoTimeout(60000);				//suspends if no request from client for 60 sec
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            server.close();
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = Integer.parseInt("5775");
      try {
         Thread t = new TCPServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}