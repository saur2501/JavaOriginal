package system.os.mgmt;

public class NetworkMgmt {

	public static void main(String[] args) {
		Node node1 = null;
		Node node2 = null;
		byte[] msg = null;
		node1.send(msg, node2);
	}
}
class IP{}
class TCP{}
class DLL{}
class PL{
	Medium medium;
	Enum type;	//LAN,man, wan, home, public
}
class Medium {
	Enum flow;	//simplex, duplex, half duplex
	Enum connectionMode;	//Cable, wireless, optical, USB
	double datarate;
	double bandwidth;
}
class Node{
	public void send(byte[] message, Node node) {
		
	}
}