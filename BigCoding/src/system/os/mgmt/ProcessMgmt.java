package system.os.mgmt;

import java.io.File;

public class ProcessMgmt {

	public static void main(String[] args) {

	}
	public static void contextSwitch(){
		System.out.println("Save current context");
		System.out.println("Load new context (like configuring CPU and registers)");
	}

}
class ProcessContextBlock {
	int pid;	//process id
	int processState;	//Enum
	int PC;	// Program Counter for CPU
	int [] registerData = new int[12];
	//accountingInformation;
	double cpuTimeConsumed;
	String jobType;
	//MemoryMgmt Info
	long memoryOccupied;
	int baseRegisterValue;
	int limitRegisterValue;
	int PMT;	//could be a pointer
	
	File[] filesOpen;
	byte[] IPCInfo;		//what kinda info
	ProcessContextBlock nextProcessToRun;
}