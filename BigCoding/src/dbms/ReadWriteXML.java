package dbms;
import java.io.File;									//Class that will represent a system file name
import java.io.FileOutputStream;						//Used to write data to a file
import java.io.IOException;								//Triggered when an I/O error occurs
import org.jdom2.Document;								//Represents your XML document and contains useful methods
import org.jdom2.Element;								//Represents XML elements and contains useful methods
import org.jdom2.JDOMException;							//Top level JDOM exception
import org.jdom2.Text;									//Represents text used with JDOM
import org.jdom2.input.SAXBuilder;						//Creates a JDOM document parsed using SAX Simple API for XML
import org.jdom2.output.Format;							//Formats how the XML document will look
import org.jdom2.output.XMLOutputter;					//Outputs the JDOM document to a file

public class ReadWriteXML{
	public void execute() {
		writeXML();
		readXML();
	}
	
	private static void readXML(){
		
		SAXBuilder builder = new SAXBuilder();
		try {
			File file = new File("C:\\data\\DRM\\DSAJavaCode\\DataStructuresAlgorithm\\Files\\jdom.xml");
			Document readDoc = builder.build(file);									//Parses the file supplied into a JDOM document
			System.out.println("Root: " + readDoc.getRootElement());				//Returns the root element for the document
			System.out.println("Show: " + readDoc.getRootElement().getChild("show").getChildText("name"));		//Gets the text found between the name tags
			System.out.println("Show ID: " + readDoc.getRootElement().getChild("show").getChild("name").getAttributeValue("show_id") + "\n");		//Gets the attribute value for show_id assigned to name
			Element root = readDoc.getRootElement();
			for (Element curEle : root.getChildren("show")) {						//Cycles through all of the children in show and prints them
				System.out.println("Show Name: " + curEle.getChildText("name"));
				System.out.println("Show ID: " + curEle.getChild("name").getAttributeValue("show_id"));
				System.out.print("On " + curEle.getChildText("network") + " in the ");
				System.out.println(curEle.getChild("network").getAttributeValue("country") + "\n");
			}
		} 
		catch (JDOMException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private static void writeXML(){
	try{
		Document doc = new Document();												//Creates a JDOM document
		Element theRoot = new Element("tvshows");									//Creates a element named tvshows and makes it the root
		doc.setRootElement(theRoot);
		Element show = new Element("show");											//Creates elements show and name
		Element name = new Element("name");
		name.setAttribute("show_id", "show_001");									//Assigns an attribute to name and gives it a value
		name.addContent(new Text("Life On Mars"));									//Adds text between the name tags
		Element network = new Element("network");
		network.addContent(new Text("ABC"));
		network.setAttribute("country", "US");
		show.addContent(name);														//Adds name and network to the show tag
		show.addContent(network);
		theRoot.addContent(show);													//Adds the show tag and all of its children to the root
		
		Element show2 = new Element("show");										//Add a new show element like above
		Element name2 = new Element("name");
		name2.setAttribute("show_id", "show_002");
		name2.addContent(new Text("Freaks and Geeks"));
		Element network2 = new Element("network");
		network2.addContent(new Text("ABC"));
		network2.setAttribute("country", "US");
		show2.addContent(name2);
		show2.addContent(network2);
		theRoot.addContent(show2);
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());		//Uses indenting with pretty format
		xmlOutput.output(doc, new FileOutputStream(new File("C:\\data\\DRM\\DSAJavaCode\\DataStructuresAlgorithm\\Files\\jdom.xml")));	//Create a new file and write XML to it
		System.out.println("Wrote to file");
		}		
		catch (Exception e) {			
			e.printStackTrace();
		}
	}
}

/* Installing JDOM 
* 
* Download jdom.jar https://github.com/hunterhacker/jdom/downloads
* Download jdom-2.0.2.zip
* 
* Right click default eclipse directory - Build Path
* 
* Click Java Build Path
* 
* Click Libraries tab - external libraries
* 
* Add the JDOM libraries
* 
* 
* */