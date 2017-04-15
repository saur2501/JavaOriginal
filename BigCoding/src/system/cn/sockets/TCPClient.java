package system.cn.sockets;

import java.net.*;
import java.io.*;

//Run TCPServer b4 this
public class TCPClient {

   public static void main(String [] args) {
      String serverName = "127.0.0.1";
      int port = Integer.parseInt("5775");
      try {
         Socket client = new Socket(serverName, port);					//Connecting to server on port
         
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         
         out.writeUTF("Hello from " + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
         System.out.println("Server says " + in.readUTF());
         client.close();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}
//Use netstat after this to confirm that active connection was being made