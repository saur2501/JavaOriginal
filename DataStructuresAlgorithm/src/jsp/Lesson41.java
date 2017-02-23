/*Install Tomcat for Macs and PCs

The first thing you need to do, is to install Tomcat. I assume you have Apache set up already. You can download Tomcat here. Then depending on your operating system, here are the best Tomcat installation articles:

    Install Tomcat on Mac
    Install Tomcat on PC

Setup Tomcat in Eclipse

In Eclipse click on Preferences in the top menu. Then click the following:

    Click Server
    Click Runtime Environments
    Click Add
    Select your version of Tomcat (Probably 7)
    Locate the Tomcat directory on your computer

Create a Java Servlet in Eclipse

This is how you create a Java Servlet step-by-step:

    Right click on your code directory in the Eclipse Project Explorer
    Click New then Project
    Click Dynamic Web Project
    Give it a name (mine is Lesson41) then click Finish
    Right click on the Java Resources directory
    Select New and then Servlet
    Enter a Package Name (mine is helloservlets)
    Enter a Class Name (mine is Lesson41)
    Click Next and Finish
    Edit the doGet method (My Code is Below)
    Change the web.xml file (My Code is Below)
    Right click on the project and select Run on Server

Shut Off Serializable Errors in Eclipse

    Click Preferences
    Expand Java – Compiler – Errors/Warnings
    Shutoff Serializable class without serialVersionUID

Setup Java Servlet Tooltips in Eclipse

    Download the Tomcat Source Code Distribution Zip
    Open the Java Resources Folder in your project in the Eclipse Project Explorer
    Open the Libraries Folder
    Expand the JRE System Library
    Right click servlet-api.jar
    Click Properties
    Select Java Source Attachement
    Click External File and locate the zip file above


*/
package jsp;



import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



/**

 * Servlet implementation class Lesson41

 */

@WebServlet("/Lesson41")

public class Lesson41 extends HttpServlet {

    private static final long serialVersionUID = 1L;



    /**

     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");



        PrintWriter output = response.getWriter();



        output.println("<html><body><h3>Hello Servlets</h3></body></html>");

    }



    /**

     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Auto-generated method stub

    }



}

/*



<?xml version="1.0" encoding="UTF-8"?>

<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">

  <display-name>Lesson41</display-name>

  <welcome-file-list>

    <welcome-file>Lesson41</welcome-file>

  </welcome-file-list>

</web-app>
*/