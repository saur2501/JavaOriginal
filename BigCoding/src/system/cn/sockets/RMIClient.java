package system.cn.sockets;

import java.rmi.Naming;
//import java.rmi.RMISecurityManager;

public class RMIClient {

	public static void main(String[] args) throws Exception {
		//System.setSecurityManager(new RMISecurityManager());
		AddI obj = (AddI) Naming.lookup("ADD");	//rmi://localhost/ADD
		int n = obj.add(5,4);
		System.out.println("Addition is " + n);
	}

}
