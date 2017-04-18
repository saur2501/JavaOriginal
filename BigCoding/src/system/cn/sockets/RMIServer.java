package system.cn.sockets;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.rmi.Remote;


public class RMIServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		AddC obj = new AddC();
		Naming.rebind("ADD", obj);			//Association String "ADD" with the obj whose method is exposed to client thru registry 
		/* Registry r = LocateRegistry.getRegistry();
		 * r.bind("myserver", new Server());
		 * System.out.println("");
		 * */
		System.out.println("Server started");
	}

}

interface AddI extends Remote{
	public int add(int x, int y) throws Exception;
}


class AddC extends UnicastRemoteObject implements AddI{
	private static final long serialVersionUID = 1L;
	//compulsory constructor
	public AddC() throws RemoteException {
		super();
	}
	public int add(int x, int y) {
		return x+y;
	}
}
//run rmic AddC after compile to create stub and skeleton- deprecation warning is ok- stub gets added (needs to be with client??) !
//start rmi registry by 'start rmiregistry'- firewall allow! Console pops (let it remain open)
//Run java RMIServer and java RMIClient after this.
//any object passed as parameter has to implement serializable.
//https://www.youtube.com/watch?v=vkw275ptI3E- for rmi plugin 