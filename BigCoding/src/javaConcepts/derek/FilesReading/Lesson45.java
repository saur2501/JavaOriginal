package FilesReading;
//Provides everything you need to work with the DOM
//Document, Element, Node, NodeList, Text, Exceptions, etc.

import org.w3c.dom.*;

//XPath makes it easy to grab information from an XML document

import javax.xml.xpath.*;

//All the parsers for working with XML
//DocumentBuilder, DocumentBuilderFactory, SAXParser

import javax.xml.parsers.*;

import java.io.IOException;

//SAX Simple API for XML

import org.xml.sax.SAXException;

public class Lesson45 {

public static void main(String[] args){

	  		// Allows your app to get a parser that turns a xml doc into a DOM tree
	  
	  		DocumentBuilderFactory domFactory = 
	  				DocumentBuilderFactory.newInstance();
	  		
	  		// Provides support for XML namespaces if needed
	  		
	  		domFactory.setNamespaceAware(true); 
	  		
	  		// Turns xml into a DOM tree
	  		
	  		DocumentBuilder builder;
	  		
	  		Document doc = null;
	  		
			try {
				
				// parses the file supplied
				
				builder = domFactory.newDocumentBuilder();
			
				doc = builder.parse("./src/tvshows5.xml");
			} 
			
			catch (SAXException e) {
				
				e.printStackTrace();
			} 
			
			catch (IOException e) {
				
				e.printStackTrace();
			} 
			
			catch (ParserConfigurationException e) {
				
				e.printStackTrace();
			}
			
			// Allows you to grab data from the document using the codes below
			
	  		XPath xpath = XPathFactory.newInstance().newXPath();
	  		
	  		getNodeNameAndValue(doc, xpath);
	
}

private static void getNodeNameAndValue(Document doc, XPath xpath){
	  
	  // XPath Query 
		XPathExpression expr;
		
		Object result = null;
		
		try {
			
			// Returns characters with the profession Student
			
			expr = xpath.compile("//show/actors/actor/character[@profession='Student']//text()");
	
			// Returns the result of the query
			
			result = expr.evaluate(doc, XPathConstants.NODESET);
		} 
		
		catch (XPathExpressionException e) {
			
			e.printStackTrace();
		}
		
		// Outputs the results of the query
		
		NodeList nodes = (NodeList) result;
		
		// Cycles through the results
		
		for (int i = 0; i < nodes.getLength(); i++) {
			
			// Print the matching node name and value
			
			System.out.println(nodes.item(i).getParentNode().getNodeName() + " " + nodes.item(i).getNodeValue()); 
			
		}
		
		// Display every name
		// : //show/name//text()
		
		// Display everything
		// : //show/*//text()
		
		// Show names based on an attribute 
		// : //show/name[@id_code='show_001']//text()
		
		// Show actors and character names
		// : //show/actors/actor/*//text()
		
		// Show character names if they are Students
		// : //show/actors/actor/character[@profession='Student']//text()
	  
}

}