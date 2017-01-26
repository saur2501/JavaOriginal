package dbms;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class FileStreams {
	public final String CONTEXT = "C:\\dev\\folder1";
	public void characterwise() throws IOException {
		System.out.println("Character-wise (FileReader)");
		FileReader in = null;		//Java Character streams
		FileWriter out = null;
		try {
			in = new FileReader(CONTEXT+"\\input.txt");
			out = new FileWriter(CONTEXT+"\\output.txt");
			int c;
			System.out.println("Read from input.txt\nWritten to output.txt");
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
    public void asciiWiseStringIO(){
    	System.out.println("Reading bytes from string (stringReader)");
		String input = "Input String here it is- Yo Baby- Krishna is cutest Lord, Man ";
		StringReader stringReader = new StringReader(input);
		try {
			int data = stringReader.read();
			while(data != -1) {
				//do something with data...
				System.out.print(data+",");
				data = stringReader.read();
			}
		} catch (IOException e) {}
		finally {stringReader.close();System.out.println("Stringwise reading finished");}
	}
	public void bufferedCharacters()throws Exception{  	  
		System.out.println("Fast Character Reading (BufferedReader), can concatenate, readLine");
		File file = new File(CONTEXT+"\\input.txt");
		File file2 = new File(CONTEXT+"\\outputBufferedCharacters.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		System.out.println("Read First line from input.txt");
		String name = br.readLine();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file2,true),2*1024);
		bw.write(name);
		System.out.println("Written to outputBufferedCharacters.txt");
		br.close();
		bw.close();
	}
	public void formattedCharacters(){	//not showing result
		System.out.println("Write by Format (PrintWriter)");
		try {
			FileWriter writer = new FileWriter(CONTEXT+"\\outputFormattedCharacters.txt");
			PrintWriter printWriter = new PrintWriter(writer, true);	//true for appending
			printWriter.print(true);
			printWriter.print((char) 23);
			printWriter.print(23);								//23 is interpreted as string
			printWriter.print((float) 123.456);
			printWriter.printf(Locale.UK, "Text + data: %d", 123);
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stringToManyFiles()throws Exception{
		 System.out.println("Write String to Many Files (CharArrayWriter,FileWriter)");
		 CharArrayWriter out = new CharArrayWriter();			//used to write data to multiple files  
		 out.write("Krishna is SPOG\nLCM");  
		 FileWriter f1=new FileWriter("C:\\dev\\folder1\\outputCharArray1.txt");  
		 FileWriter f2=new FileWriter("C:\\dev\\folder1\\outputCharArray2.txt");
		 FileWriter f3=new FileWriter("C:\\dev\\folder1\\outputCharArray3.txt");  
		 FileWriter f4=new FileWriter("C:\\dev\\folder1\\outputCharArray4.txt");
		 out.writeTo(f1);
		 out.writeTo(f2);  
		 out.writeTo(f3);  
		 out.writeTo(f4);  
		 System.out.println("Read from string\nWrote to outputCharArrayi");
		 f1.close();  
		 f2.close();  
		 f3.close();  
		 f4.close();  
	}
	public void binaryBytewise() throws IOException{
		System.out.println("Byte wise reading (FileInputStream)");
		FileInputStream in = null;		//used to read and write bytes
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:\\dev\\folder1\\input.txt");			//to read bytes
			out = new FileOutputStream("C:\\dev\\folder1\\outputBinaryBytewise.txt");
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			System.out.println();
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	public void asciiWiseByteIO()throws IOException {
		System.out.println("ASCII wise byte IO (ByteArrayInputStream)");
		ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
	    while( bOutput.size()!= 10 ) {
	    	//Gets the inputs from the user
	        bOutput.write("hello".getBytes());  
	    }
	    byte b [] = bOutput.toByteArray();
	    System.out.println("Wrote 'hello' on buffer twice- Printing it now-");
	    for(int x = 0; x < b.length; x++) {		//or use write() method of ByteArrayInputStream
	        // printing the characters
	    	System.out.print((char)b[x]  + " "); 
	    }
	    System.out.println("");
	    int c;
	    ByteArrayInputStream bInput = new ByteArrayInputStream(b);
	    System.out.println("Reading bytes and converting cases- " );
    	while(( c = bInput.read())!= -1) {
        	System.out.print(Character.toUpperCase((char)c));
		}
    	System.out.println();
        bInput.reset();
	}
	public void byteArrayToManyFiles()throws Exception{
		System.out.println("ByteArray to many files (ByteArrayOutputStream, FileOutputStream)");
	  FileOutputStream fout1=new FileOutputStream("C:\\dev\\folder1\\outputByteArray.txt");  
	  FileOutputStream fout2=new FileOutputStream("C:\\dev\\folder1\\outputByteArray2.txt");
	  
	  ByteArrayOutputStream bout=new ByteArrayOutputStream();		//used to write to multiple outputstreams.  
	  bout.write(139);  					//139 is interpreted as byte
	  bout.writeTo(fout1);  
	  bout.writeTo(fout2);  
	  System.out.println("Wrote 139 to outByteArrayi");
	  bout.flush();  
	  bout.close();//has no effect  
	 }
	public void hastenForBytes()throws Exception{
		System.out.println("Fast Reading Bytes (BufferedInputReader)\t");
		try{ 
			FileOutputStream fout=new FileOutputStream(CONTEXT+"outputBufferedOutputReader.txt");  
			BufferedOutputStream bout=new BufferedOutputStream(fout);	//uses an internal buffer to store data. It adds more efficiency than to write data directly into a stream. So, it makes the performance fast.  
			String s="Sachin is my favourite player";  
			byte b[]=s.getBytes();
			bout.write(b);  
			bout.flush();  
			bout.close();  
			fout.close();
			
			FileInputStream fin=new FileInputStream("C:\\dev\\folder1\\input.txt");  
		    BufferedInputStream bin=new BufferedInputStream(fin);
		    int i;
		    while((i=bin.read())!=-1){  
		    	System.out.print((char)i);  
		    }
		    System.out.println("\nRead from input.txt\nWrote to console");
	        /*int numByte = bin.available();				//read number of bytes available
	        byte[] buf = new byte[numByte];
	        bin.read(buf, 2, 3);						//read byte into buf , starts at offset 2, 3 bytes to read; we can use mark(), reset() for pinning
	        for (byte b1 : buf) {
	        	System.out.println((char)b1+": " + b1);
	        }
	        fin.close();
	        bin.close();*/
		}catch(Exception e){
		    	e.printStackTrace();
	    }
	}
	public void dataFormatsReading()throws IOException {			//enables you to read Java primitives (int, float, long etc.) from an InputStream instead of only raw bytes
		//writing string to a file encoded as modified UTF-8
		System.out.println("Formatted Reading from files (DataInputStream)");
	    DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(CONTEXT+"\\outputFormatted.txt"));		
	    dataOut.writeUTF("hello");
	    dataOut.writeChars("Hello");
	    dataOut.writeInt(108);
	    System.out.println("Wrote hello 108 to outputFormatted.txt");
	    // Reading data from the same file
	    /*DataInputStream dataIn = new DataInputStream(new FileInputStream(CONTEXT+"\\input.txt"));
    	while(dataIn.available()>0) {
	    	String k = dataIn.readUTF();
	        System.out.print(k+" ");
    	}*/
    	System.out.println("Wrote input.txt on console");
	}
	//ObjectInputStream
	public void fileStreamTest() {
		System.out.println("Bytes Writing (OutputStream, FileOutputStream)");
		try {
			byte bWrite [] = {11,21,3,40,5};
			OutputStream os = new FileOutputStream(CONTEXT+"\\outputOutputStream.txt");
			for(int x = 0; x < bWrite.length ; x++) {
				os.write( bWrite[x] );   // writes the bytes
			}
			os.close();
			System.out.println("Wrote few bytes to outputOutputStream.txt");
			/*InputStream is = new FileInputStream(CONTEXT+"input.txt");
			int size = is.available();

			for(int i = 0; i < size; i++) {
				System.out.print((char)is.read() + "  ");
			}
			is.close();*/
		}catch(IOException e) {
			System.out.println("Exception");
		}	
	}
	public void readFromConsole() throws IOException {
		System.out.println("Console Reading (InputStreamReader)");
		InputStreamReader cin = null;
    	cin = new InputStreamReader(System.in);				//InputStreamReader to read standard input stream until the user types a "q"
    	System.out.println("Enter characters, 'q' to quit.");
        char c;
        do {
        	c = (char) cin.read();
            System.out.print(c);
         } while(c != 'q');
        //Don't you dare close the inputstreamReader.
	}
    public void scanner() throws FileNotFoundException {
    	System.out.println("Scanner for reading from files");
    	File text = new File("C:\\dev\\folder1\\input.txt");
        Scanner scnr = new Scanner(text);			//convenient method to read int, long, String, double etc from source which can be an InputStream, a file or a String itself
        int lineNumber = 1;
        while(scnr.hasNextLine()){					//Reading each line of file using Scanner class
        	String line = scnr.nextLine();
            System.out.println("line " + lineNumber + " :" + line);
        	lineNumber++;
        }
    }    
	public void printStreamTest() throws Exception{
		System.out.println("Printstream for writing");
		FileOutputStream fout=new FileOutputStream("C:\\dev\\folder1\\outputPrintStream.txt");  
		PrintStream pout=new PrintStream(fout);  
		pout.println(1900);  
		pout.println("Hello Java");  
		pout.println("Welcome to Java");  
		pout.close();  
		fout.close();  
	}
	public void sequence() throws Exception{  
		System.out.println("Read from many files (SequenceInputStream)");
		FileInputStream fin1=new FileInputStream(CONTEXT+"\\input.txt");  
		FileInputStream fin2=new FileInputStream(CONTEXT+"\\input1.txt");  
	  
		SequenceInputStream sis=new SequenceInputStream(fin1,fin2);  
		int i;
		while((i=sis.read())!=-1){  
			System.out.print((char)i);
		}
		System.out.println("\nRead from input.txt\nWrote to console");
		sis.close();  
		fin1.close();  
		fin2.close();  
	}
	public void pipedWR()throws Exception{
		System.out.println("PiperRW (1's output is other's input)");
		final PipedOutputStream pout=new PipedOutputStream();  
		final PipedInputStream pin=new PipedInputStream();		//used to read and write data simultaneously  
		pout.connect(pin);										//connecting the streams  
		//creating one thread t1 which writes the data  
		Thread t1=new Thread(){  
			public void run(){  
				for(int i=65;i<=90;i++){  
				try{  
					pout.write(i);  
					Thread.sleep(200);  
				}catch(Exception e){}  
				}  
			}
		};  
		//creating another thread t2 which reads the data  
		Thread t2=new Thread(){  
			public void run(){  
				try{   
					for(int i=65;i<=90;i++)  
						System.out.println(pin.read());  
				}catch(Exception e){}  
			}  
		};  
		//starting both threads  
		t1.start();  
		t2.start();  
	}
	public void pushback(){
		try {
			System.out.println("Look Ahead and unread (PushbackInputStream)");
			PushbackInputStream input = new PushbackInputStream(new FileInputStream("C:\\dev\\folder1\\input.txt"), 8);
			int data = input.read();
			System.out.println(data);
			data = input.read();
			System.out.println(data);
			input.unread(data);
			System.out.println(data);
		} catch (IOException e) {}
	}
	public void execute() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.print("File I/O\n====\n1. ASCII wise byte IO (ByteArrayInputStream)\t2. Reading bytes from string (stringReader)\t3. Console Reading (InputStreamReader)\n");
		System.out.println("4. Character wise (FileReader)\t5. Fast Character Reading (BufferedReader), concatenate, readLine\t6. Write by Format (PrintWriter)");
		System.out.println("7. Write String to Many Files (CharArrayWriter,FileWriter)\t8. Read from many files (SequenceInputStream)");
		System.out.println("9. Byte wise reading (FileInputStream)\t10. Fast Reading Bytes (BufferedInputReader)\t");
		System.out.println("11. ByteArray to many files (ByteArrayOutputStream, FileOutputStream)\t12. Formatted Reading from files (DataInputStream)");
		System.out.println("13. Bytes Writing (OutputStream, FileOutputStream), printStreamTest\t14. PipedWR (1's output is other's input");
		System.out.print("Enter Your Choice : ");
		int choice = sc.nextInt();
		FileStreams fs = new FileStreams();
		while(choice != -1){
			switch(choice){
			case 1:
				fs.asciiWiseByteIO();
				break;
			case 2:
				fs.asciiWiseStringIO();
				break;
			case 3:
				fs.readFromConsole();
				break;
			case 4:
				fs.characterwise();
				fs.scanner();	//not thread safe but token parsing, features all.
				break;
			case 5:
				fs.bufferedCharacters();
				break;
			case 6:
				fs.formattedCharacters();
				break;
			case 7:
				fs.stringToManyFiles();
				break;
			case 8:
				fs.sequence();
				break;
			case 9:
				fs.binaryBytewise();
				break;
			case 10:
				fs.hastenForBytes();
				fs.pushback();		//to unread a file content
				break;
			case 11:
				fs.byteArrayToManyFiles();
				break;
			case 12:
				fs.dataFormatsReading();
				break;
			case 13:
				fs.fileStreamTest();	//not working desirable
				fs.printStreamTest();
				break;
			case 14:
				//fs.readingPassword(); (using console.readPassword())
				fs.pipedWR();
				break;
			}
			System.out.print("File I/O\n====\n1. ASCII wise byte IO (ByteArrayInputStream)\t2. Reading bytes from string (stringReader)\t3. Console Reading (InputStreamReader)\n");
			System.out.println("4. Character wise (FileReader)\t5. Fast Character Reading (BufferedReader), concatenate, readLine\t6. Write by Format (PrintWriter)");
			System.out.println("7. Write String to Many Files (CharArrayWriter,FileWriter)\t8. Read from many files (SequenceInputStream)");
			System.out.println("9. Byte wise reading (FileInputStream)\t10. Fast Reading Bytes (BufferedInputReader)\t");
			System.out.println("11. ByteArray to many files (ByteArrayOutputStream, FileOutputStream)\t12. Formatted Reading from files (DataInputStream)");
			System.out.println("13. Bytes Writing (OutputStream, FileOutputStream), printStreamTest\t14. PipedWR (1's output is other's input");
			System.out.print("Enter Your Choice : ");
			choice = sc.nextInt();
		}
		System.out.println("My working location- "+System.getProperty("user.dir")); 					//my working location
	}
}