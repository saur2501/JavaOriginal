package designPatterns.derek;

interface TheATMState {
	void insertCard();
	void ejectCard();
	void insertPin(int pinEntered);
	void requestCash(int cashToWithdraw);	
}

interface GetATMData {															//This interface will contain just those methods that you want the proxy to provide access to
	public TheATMState getATMState();
	public int getCashInMachine();
}

class TheATMMachine implements GetATMData, TheATMState{
	private TheATMState hasCard;
	private TheATMState noCard;
	private TheATMState hasCorrectPin;
	private TheATMState atmOutOfMoney;
	private TheATMState atmState;
	private int cashInMachine;
	public TheATMState getYesCardState() { return hasCard; }
	public TheATMState getNoCardState() { return noCard; }
	public TheATMState getHasPin() { return hasCorrectPin; }
	public TheATMState getNoCashState() { return atmOutOfMoney; }
	public TheATMState getATMState() { return atmState; }						//NEW STUFF
	public int getCashInMachine() { return cashInMachine; }
	@Override
	public void insertCard() {
		// TODO Auto-generated method stub
	}
	@Override
	public void ejectCard() {
		// TODO Auto-generated method stub
	}
	@Override
	public void insertPin(int pinEntered) {
		// TODO Auto-generated method stub
	}
	@Override
	public void requestCash(int cashToWithdraw) {
		// TODO Auto-generated method stub		
	}
}

class ATMProxy implements GetATMData {									//In this situation the proxy both creates and destroys an TheATMMachine Object
	public TheATMState getATMState() {									//Allows the user to access getATMState in the Object TheATMMachine
		TheATMMachine realATMMachine = new TheATMMachine();	
		return realATMMachine.getATMState();
	}
	public int getCashInMachine() {										//Allows the user to access getCashInMachine in the Object TheATMMachine
		TheATMMachine realATMMachine = new TheATMMachine();
		return realATMMachine.getCashInMachine();	
	}	
}

public class ProxyPattern {
	public static void main(String[] args){
		TheATMMachine atmMachine = new TheATMMachine();
		atmMachine.insertCard();
		atmMachine.ejectCard();
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		// NEW STUFF : Proxy Design Pattern Code The interface limits access to just the methods you want made accessible
		GetATMData realATMMachine = new TheATMMachine();
		GetATMData atmProxy = new ATMProxy();
		System.out.println("\nCurrent ATM State " + atmProxy.getATMState());		
		System.out.println("\nCash in ATM Machine $" + atmProxy.getCashInMachine());							//The user can't perform this action because ATMProxy doesn't have access to that potentially harmful method atmProxy.setCashInMachine(10000);
	}
}