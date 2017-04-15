package system.cn.sockets;

import java.net.*;
import java.io.*;

//Run TCPClient after this
public class TCPServer extends Thread {
   private ServerSocket serverSocket;
   
   public TCPServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);		//starts port on machine
      try {
		Thread.sleep(60000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      serverSocket.setSoTimeout(60000);				//suspends if no request from client for 60 sec
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();			//launches a socket on receiving a client requests
            
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