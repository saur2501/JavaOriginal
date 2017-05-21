package system.os.mgmt;

import java.util.HashMap;

public class MemoryMgmt {

	public static void main(String[] args) {
		
	}
	public int getPhysicalAddress(int pid, int address) {
		//can have segmented paged memory- first gotta ascertain segment whose pmt is to be queried.
		int pageNo = address/4096;
		int offset = address%4096;
		if(MemoryModule.processPageMap.get(pid).pageEntry[pageNo].dirtyBit==true){
			System.out.println("Page fault Exception");
			System.out.println("Choose victim frame\nwrite it if modified");
			System.out.println("fetch block and rewrite");
		}
		int framePhysicalStartAddress = MemoryModule.processPageMap.get(pid).pageEntry[pageNo].frameIndex * 4096; 
		return framePhysicalStartAddress + offset;
	}

}

class Memory {
	Frame[] frames = new Frame[4194304];	// = 16GB/4KB
}
class Frame {
	byte page[] = new byte[4096];
}
class MemoryModule {
	static HashMap<Integer,PageTable> processPageMap = new HashMap<>();
}
class PageTable {
	PageEntry[] pageEntry = new PageEntry[365];	//random number
}
class PageEntry {
	int pageNo;
	int frameIndex;
	boolean dirtyBit;
	boolean modifiedBit;
}