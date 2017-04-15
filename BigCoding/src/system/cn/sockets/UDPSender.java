package system.cn.sockets;

import java.net.*;

//run UDPReceiver b4 this
public class UDPSender {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket();							//creates a datagram socket and binds it with the available Port Number on the localhost machine
		String str = "Welcome java";
		InetAddress ip = InetAddress.getByName("127.0.0.1");

		DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);		//constructs data packets for sending		
		ds.send(dp);
		ds.close();
	}
}