package designPatterns.derek;
import java.util.ArrayList;
import java.util.List;

interface ElectronicDevice {
	public void on();
	public void off();
	public void volumeUp();	
	public void volumenDown();
}

class Television implements ElectronicDevice {
	private int volume = 0;
	public void on() {
		System.out.println("TV is on");	
	}
	public void off() {
		System.out.println("TV is off");	
	}
	public void volumeUp() {
		volume++;
		System.out.println("TV Volume is at: " + volume);	
	}
	public void volumenDown() {
		volume--;
		System.out.println("TV Volume is at: " + volume);	
	}
}

interface Command {																		//Each command you want to issue will implement the Command interface	
	public void execute();
	public void undo();																	//You may want to offer the option to undo a command
}

class TurnTVOn implements Command {
	ElectronicDevice theDevice;
	public TurnTVOn(ElectronicDevice newDevice){
		theDevice = newDevice;	
	}
	public void execute() {
		theDevice.on();	
	}
	public void undo() {
		theDevice.off();	
	}
}

class TurnTVOff implements Command {
	ElectronicDevice theDevice;
	public TurnTVOff(ElectronicDevice newDevice){
		theDevice = newDevice;	
	}
	public void execute() {
		theDevice.off();	
	}
	public void undo() {																//Used if you want to allow for undo Do the opposite of execute()
		theDevice.on();	
	}
}

class TurnTVUp implements Command {
	ElectronicDevice theDevice;
	public TurnTVUp(ElectronicDevice newDevice){
		theDevice = newDevice;	
	}
	public void execute() {
		theDevice.volumeUp();	
	}
	public void undo() {
		theDevice.volumenDown();	
	}
}

class DeviceButton{																		//This is known as the invoker
	Command theCommand;
	public DeviceButton(Command newCommand){
		theCommand = newCommand;	
	}
	public void press(){																//causes attached command to execute.
		theCommand.execute();															//calls the method assigned in the class that implements the Command interface
	}
	public void pressUndo(){															//Now the remote can undo past commands
		theCommand.undo();	
	}	
}

class TVRemote {
	public static ElectronicDevice getDevice(){
		return new Television();	
	}
}

public class CommandPattern{
	
	public static void main(String[] args){
		ElectronicDevice newDevice = TVRemote.getDevice();								//Gets the ElectronicDevice to use
		TurnTVOn onCommand = new TurnTVOn(newDevice);									//TurnTVOn contains the command to turn on the tv. When execute() is called on this command object it will execute the method on() in Television
		DeviceButton onPressed = new DeviceButton(onCommand);							//Calling the execute() causes on() to execute in Television		
		onPressed.press();																//When press() is called theCommand.execute(); executes
		TurnTVOff offCommand = new TurnTVOff(newDevice);								//Now when execute() is called off() of Television executes
		onPressed = new DeviceButton(offCommand);										//Calling the execute() causes off() to execute in Television
		onPressed.press();																//When press() is called theCommand.execute(); executes
		TurnTVUp volUpCommand = new TurnTVUp(newDevice);								//Now when execute() is called volumeUp() of Television executes
		onPressed = new DeviceButton(volUpCommand);										//Calling the execute() causes volumeUp() to execute in Television
		onPressed.press();																//When press() is called theCommand.execute(); executes
		onPressed.press();
		onPressed.press();
		Television theTV = new Television();											//Creating a TV and Radio to turn off with 1 press
		Radio theRadio = new Radio();
		List<ElectronicDevice> allDevices = new ArrayList<ElectronicDevice>();			//Add the Electronic Devices to a List
		allDevices.add(theTV);
		allDevices.add(theRadio);
		TurnItAllOff turnOffDevices = new TurnItAllOff(allDevices);						//Send the List of Electronic Devices to TurnItAllOff where a call to run execute() on this function will call off() for each device in the list
		DeviceButton turnThemOff = new DeviceButton(turnOffDevices);					//This calls for execute() to run which calls for off() to run for every ElectronicDevice
		turnThemOff.press();
		turnThemOff.pressUndo();														//It is common to be able to undo a command in a command pattern. To do so, DeviceButton will have a method called undo Undo() will perform the opposite action that the normal Command performs. undo() needs to be added to every class with an execute()
		// To undo more than one command add them to a LinkedList using addFirst(). Then execute undo on each item until there are none left. (This is your Homework)	
	}	
}

class Radio implements ElectronicDevice {

	private int volume = 0;
	public void on() {
		System.out.println("Radio is on");	
	}
	public void off() {
		System.out.println("Radio is off");	
	}
	public void volumeUp() {
		volume++;
		System.out.println("Radio Volume is at: " + volume);	
	}
	public void volumenDown() {
		volume--;
		System.out.println("Radio Volume is at: " + volume);	
	}
}

class TurnItAllOff implements Command {
  List<ElectronicDevice> theDevices;
  public TurnItAllOff(List<ElectronicDevice> newDevices) {
	  theDevices = newDevices;
  }
  public void execute() {
    for (ElectronicDevice device : theDevices) {
      device.off();
    }
  }

  public void undo() {
	for (ElectronicDevice device : theDevices) {
	      device.on();
    }	
  }
}