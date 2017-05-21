package system.os.mgmt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileMgmt {

	public static void main(String[] args) {
		
	}
	void getInode(){}
	void releaseInode(){}
	void createFile(){}
	void deleteFile(){}
	void allocate(){}

}
class File {
	String filename;
	String filetype;
	
}
class FileMap {
	Map<String, INode> fileINodeMap = new HashMap<> ();
}
class TOC {
	byte[][] directPointersToBlocks = new byte[512][10];
	byte[] singleIndirectPointerToBlocks;
	byte[] doubleIndirectPointerToBlocks;
	byte[] tripleIndirectPointerToBlocks;
}
class INode {
	String individualOwnership;	//uname
	String groupOwnership;		//-g?
	Enum type;	//file or directory or link
	String permissions;		//777
	Date modifiedTime;
	Date InodeModifiedTime;
	long fileSize;
	TOC tableOfContents;
}
class IncoreInode extends INode {
	Enum status;	//locked, waiting, changed
	int deviceNumber;
	int inodeNumber;	//unlike disk number where sequenced, here is needed
	byte[] hashlist;
	byte[] freeInodeList;
	int referenceCount;	//how many procs use it (to avoid overwriting) 
}
class DiskLayout {
	byte[] bootblock;	//to load OS
	byte[] Superblock;	//fs info, where free block, free inodes
	byte[] inodeList;
	byte[] datablocks;
}