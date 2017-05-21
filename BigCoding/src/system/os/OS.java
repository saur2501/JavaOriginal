package system.os;

public class OS {

	public static void main(String[] args) {
		bootMachine();
		interruptOrShellCommandListen();
	}

	private static void bootMachine() {
		startBIOS();
		initializeDevices();
		loadKernel();
		startKernel();
	}

	private static void interruptOrShellCommandListen() {
		// TODO Auto-generated method stub
		
	}

	private static void startKernel() {
		// TODO Auto-generated method stub
		
	}

	private static void loadKernel() {
		// TODO Auto-generated method stub
		
	}

	private static void initializeDevices() {
		// TODO Auto-generated method stub
		
	}
	
	private static void startBIOS() {
		// TODO Auto-generated method stub
		
	}

}
