package dbms;

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

public class XMLXPath{

	public void execute(){
			System.out.println("XML XPath");
	  		DocumentBuilderFactory domFactory = 
	  				DocumentBuilderFactory.newInstance();				//Allows your app to get a parser that turns a xml doc into a DOM tree
	  		domFactory.setNamespaceAware(true);							//Provides support for XML namespaces if needed 
	  		DocumentBuilder builder;									//Turns xml into a DOM tree
	  		Document doc = null;
	  		try {
				builder = domFactory.newDocumentBuilder();				//parses the file supplied
				doc = builder.parse("C:\\data\\DRM\\DSAJavaCode\\DataStructuresAlgorithm\\Files\\tvshows5.xml");
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
			XPath xpath = XPathFactory.newInstance().newXPath();		//Allows you to grab data from the document using the codes below
	  		getNodeNameAndValue(doc, xpath);
	}

	private static void getNodeNameAndValue(Document doc, XPath xpath){
		System.out.println("Applying xpath expression");
		XPathExpression expr;											//XPath Query 
		Object result = null;
		try {
			expr = xpath.compile("//show/actors/actor/character[@profession='Student']//text()");						//Returns characters with the profession Student
			result = expr.evaluate(doc, XPathConstants.NODESET);		//Returns the result of the query
			System.out.println("Size="+((NodeList)result).getLength());
		}		
		catch (XPathExpressionException e) {			
			e.printStackTrace();
		}
		NodeList nodes = (NodeList) result;								//Outputs the results of the query
		for (int i = 0; i < nodes.getLength(); i++) {					//Cycles through the results
			System.out.println(nodes.item(i).getParentNode().getNodeName() + " " + nodes.item(i).getNodeValue());		//Print the matching node name and value 
			
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