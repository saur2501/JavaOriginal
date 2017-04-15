package dbms;

import java.io.*;
import java.util.*;

public class DirectoriesRW {
	public void createDir() {
		String dirname = "C:\\dev\\try\\first";
    	File d = new File(dirname);
	    d.mkdirs();
	    System.out.println("Directory Created!!");
	}
	public void createDirectories(){
		List<File> directories = new ArrayList<File>();
		directories.add(new File("C:\\dev\\folder1"));
		directories.add(new File("C:\\dev\\folder2"));
		directories.add(new File("C:\\dev\\folder3"));
		directories.add(new File("C:\\dev\\folder4"));
		directories.add(new File("C:\\dev\\folder5"));
		for(File f : directories){
			if(f.mkdir()){
				System.out.println(f.getPath()+" folder was created successfully");
			} else {
				System.out.println(f.getPath()+" folder already exists!");
			}
		}
	}
	public void readDir() {
		File file = null;
    	String[] paths;
    	try {      
    		//create new file object
    		file = new File("C:\\dev");
	        // array of files and directory
	        paths = file.list();
	         // for each name in the path array
	        for(String path:paths) {
	            // prints filename and directory name
	            System.out.println(path);
	        }
    	}catch(Exception e) {
    		// if any error occurs
	        e.printStackTrace();
    	}
	}
	public void createFileDemo(){
		File newFile = new File("C:\\dev\\try.txt");
		try{
			if(newFile.createNewFile())
				System.out.println("try.txt has been created successfully");
			else System.err.println("try.txt already exists");
		} catch(IOException ioEx){
			System.err.println("try.txt could not be created for some reason");
		}
	}
	public void deleteFileDemo(){
		File file = new File("C:\\dev\\workspace\\tuts");
		if(file.delete()){
			System.out.println("the file was deleted successfully!");
		} else { 
			System.err.println("the file could not be deleted: where is it?");
		}
	}
	public void copyFile(){
		File file = new File("C:\\dev\\try");
		File copyFile = new File("C:\\dev\\copyTry");
		BufferedReader reader;
		PrintWriter writer;
		String line;
		try{
			if(copyFile.createNewFile() || !copyFile.createNewFile()){
				reader = new BufferedReader(new FileReader(file));
				writer = new PrintWriter(new FileWriter(copyFile));
				line = reader.readLine();
				while(line != null)
					writer.println(line);
				reader.close();
				writer.close();
				//file.delete() for move
			}
		} catch(IOException ioEx){
			System.err.println("Some Error occurred");
		}
	}
	public void renameFile(){
		File file = new File("C:\\dev\\try");
		File renameFile = new File("C:\\dev\\renamedTry");
		if(file.renameTo(renameFile))
			System.out.println("Rename Done successfully!");
		else System.err.println("Some Error in renaming.");
	}
	public void getDiskSpace(){
		File cdrive = new File("C:\\");
		double totalSpace = cdrive.getTotalSpace();
		double availableSpace = cdrive.getFreeSpace();
		double occupiedSpace = totalSpace - availableSpace;
		System.out.println("Space in C: Drive- "+totalSpace+" "+availableSpace+" "+occupiedSpace);
	}
	public void execute(){
		DirectoriesRW df = new DirectoriesRW();
		df.getDiskSpace();
		df.createDir();
		df.readDir();
		df.createDirectories();
		df.createFileDemo();
		df.renameFile();
		df.copyFile();
		df.deleteFileDemo();
	}
}