package dbms.fileReads;
// Class that will represent a system file name

import java.io.File;

// Used to write data to a file

import java.io.FileOutputStream;

// Triggered when an I/O error occurs

import java.io.IOException;

// Represents your XML document and contains useful methods

import org.jdom2.Document;

// Represents XML elements and contains useful methods

import org.jdom2.Element;

// Top level JDOM exception

import org.jdom2.JDOMException;

// Represents text used with JDOM

import org.jdom2.Text;

// Creates a JDOM document parsed using SAX Simple API for XML

import org.jdom2.input.SAXBuilder;

// Formats how the XML document will look

import org.jdom2.output.Format;

// Outputs the JDOM document to a file

import org.jdom2.output.XMLOutputter;

class Lesson46 {
	public static void main(String[] args) {

		// writeXML();

		readXML();
	}

	private static void readXML() {

		SAXBuilder builder = new SAXBuilder();
		try {

			// Parses the file supplied into a JDOM document

			Document readDoc = builder.build(new File("./src/jdomMade.xml"));

			// Returns the root element for the document

			System.out.println("Root: " + readDoc.getRootElement());

			// Gets the text found between the name tags

			System.out.println("Show: " + readDoc.getRootElement().getChild("show").getChildText("name"));

			// Gets the attribute value for show_id assigned to name

			System.out.println("Show ID: "
					+ readDoc.getRootElement().getChild("show").getChild("name").getAttributeValue("show_id") + "\n");

			Element root = readDoc.getRootElement();

			// Cycles through all of the children in show and prints them

			for (Element curEle : root.getChildren("show")) {
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

	@SuppressWarnings("unused")
	private static void writeXML() {
		try {

			// Creates a JDOM document

			Document doc = new Document();

			// Creates a element named tvshows and makes it the root

			Element theRoot = new Element("tvshows");
			doc.setRootElement(theRoot);

			// Creates elements show and name

			Element show = new Element("show");
			Element name = new Element("name");

			// Assigns an attribute to name and gives it a value

			name.setAttribute("show_id", "show_001");

			// Adds text between the name tags

			name.addContent(new Text("Life On Mars"));

			Element network = new Element("network");

			network.addContent(new Text("ABC"));

			network.setAttribute("country", "US");

			// Adds name and network to the show tag

			show.addContent(name);
			show.addContent(network);

			// Adds the show tag and all of its children to the root

			theRoot.addContent(show);

			// Add a new show element like above

			Element show2 = new Element("show");

			Element name2 = new Element("name");

			name2.setAttribute("show_id", "show_002");

			name2.addContent(new Text("Freaks and Geeks"));

			Element network2 = new Element("network");

			network2.addContent(new Text("ABC"));

			network2.setAttribute("country", "US");

			show2.addContent(name2);
			show2.addContent(network2);

			theRoot.addContent(show2);

			// Uses indenting with pretty format

			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());

			// Create a new file and write XML to it

			xmlOutput.output(doc, new FileOutputStream(new File("./src/jdomMade.xml")));

			System.out.println("Wrote to file");

		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

}

/*
 * Installing JDOM
 * 
 * Download jdom.jar https://github.com/hunterhacker/jdom/downloads Download
 * jdom-2.0.2.zip
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
 */