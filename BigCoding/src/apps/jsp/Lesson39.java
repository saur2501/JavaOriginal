package apps.jsp;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.IOException;

 

// Thrown when a URL doesn't contain http://

// and other rules like that

 

import java.net.MalformedURLException;

import java.net.URL;

 

// A text component that allows for rich text

// and basic html display

 

import javax.swing.JEditorPane;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextField;

 

// Provides information on events triggered

// through interaction with links

 

import javax.swing.event.HyperlinkEvent;

 

// Monitors user activity with links

 

import javax.swing.event.HyperlinkListener;

 

public class Lesson39 extends JFrame implements HyperlinkListener, ActionListener{

 

    public static void main(String[] args){

         

        new Lesson39("file:///Volumes/My%20Book/Presentations/HTML%20Tutorial/htmlexample.html");

         

    }

     

    String defaultURL;

     

    JPanel toolPanel = new JPanel();

    JTextField theURL = new JTextField(25);

     

    // Displays basic html pages

    // Doesn't understand JavaScript

     

    JEditorPane htmlPage;

     

    public Lesson39(String defaultURL){

         

        JFrame frame = new JFrame("Java Browser");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         

        this.defaultURL = defaultURL;

         

        // If the user interacts with the JTextField the

        // actionPerformed method is called

         

        theURL.addActionListener(this);

         

        // Set default text in the JTextField

         

        theURL.setText(defaultURL);

         

        // Add the text field to a panel

         

        toolPanel.add(theURL);

         

        // Add the panel to the northern quadrant of a frame

         

        frame.add(toolPanel, BorderLayout.NORTH);

         

        try {

            htmlPage = new JEditorPane(defaultURL);

             

            // If the user interacts with the JEditorPane

            // actions are triggered. Ex. Click on a link

            // change the JEditorPane page location

             

            htmlPage.addHyperlinkListener(this);

             

            // You can leave this true for rich text documents

            // but it will mess up web page display

             

            htmlPage.setEditable(false);

             

            // Add the JEditorPane to a Scroll pane

             

            JScrollPane scroller = new JScrollPane(htmlPage);

             

            // Add Scroll pane and JEditorPane to the frame

             

            frame.add(scroller, BorderLayout.CENTER);

             

        }

         

        // If something goes wrong with locating the html page

        // this will handle that error

         

        catch (IOException e) {

            e.printStackTrace();

        }

         

        // Set size of the frame and display it

         

        frame.setSize(1200, 800);

        frame.setVisible(true);

         

    }

 

    public void hyperlinkUpdate(HyperlinkEvent e) {

         

        // Checks if a link was clicked

        // EventType.ENTERED : Checks for hovering

        // EventType.EXITED : Checks for leaving link

         

        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){

             

            try {

                 

                // Sets the URL to be displayed

                // getURL gets the URL for the link

                 

                htmlPage.setPage(e.getURL());

                 

                // toExternalForm creates a String representation of the URL

                 

                theURL.setText(e.getURL().toExternalForm());

                 

            }

             

            catch(IOException e1){

                e1.printStackTrace();

            }

             

        }

         

    }

 

    public void actionPerformed(ActionEvent e) {

         

        String pageURL = "";

         

        // Gets the Object that had an event triggered

         

        if(e.getSource() == theURL){

             

            // Get the text in the JTextField

             

            pageURL = theURL.getText();

             

        } else {

             

            pageURL = defaultURL;

             

            // Opens an alert box when an error is made

             

            JOptionPane.showMessageDialog(Lesson39.this,

                    "Please Enter a Web Address", "Error",

                    JOptionPane.ERROR_MESSAGE);

             

        }

         

        try{

             

            // Sets the URL to be displayed

             

            htmlPage.setPage(new URL(pageURL));

            theURL.setText(pageURL);

             

        }

         

        catch(MalformedURLException e2){

            JOptionPane.showMessageDialog(Lesson39.this,

                    "Please use http://", "Error",

                    JOptionPane.ERROR_MESSAGE);

        }

         

        catch(IOException e1){

            e1.printStackTrace();

        }

         

    }

     

}
/*
HTML Source


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta name="generator" content="HTML Tidy for Linux (vers 6 November 2007), see www.w3.org" />

<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1" />

<style type="text/css">

<![CDATA[

 

h1 {color:red}

 

]]>

</style>

<meta name="description" content="Welcome" />

<meta name="keywords" content="Flowers," />

<meta name="author" content="Bob" />

<title>Welcome to Bob's Hardware Store, we sell Flowers, Seeds, Rakes</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">

<![CDATA[

 span.c2 {text-decoration: line-through}

 div.c1 {text-align: center}

]]>

</style>

</head>

<body>

<h1>Text to Draw Attention to</h1>

<abbr title="A">Something Abbreviated</abbr> <acronym title="as">ASAP</acronym> <strong>I Want this to be Bold</strong> <big>This is going to be Big</big>

<blockquote>Indent me and show I'm important</blockquote>

<div class="c1">Center Me Please</div>

<em>I like Being Italicized</em> <em>I like being Italicized too</em>

<p>This is a big paragraph. Really, it is!</p>

<q>You can quote me on that</q> <span class="c2">I've been struck</span> <small>I feel so small</small> <strong>I'm the strongest</strong> <sub>I'm Subscript</sub> <sup>I'm Superscript</sup> <br /><img src="file:///Volumes/My%20Book/Presentations/HTML%20Tutorial/Frame4.jpg" alt="Photo" height="150" width="200" />

<dl>

<dt>Bicycle</dt>

<dd>- Has two Wheels</dd>

<dt>Car</dt>

<dd>- Has Four Wheels</dd>

</dl>

<ol>

<li>Car</li>

<li>Truck</li>

<li>Bicycle</li>

</ol>

<ul>

<li>Car</li>

<li>Truck</li>

<li>Bicycle</li>

</ul>

<a href="http://www.newthinktank.com" target="_blank">The New Think Tank Website</a> <a href="http://www.newthinktank.com#Great">Some Great Stuff</a> <a href="derekbanas@example.com?Subject=Hello%20again">Send Mail</a>

<table summary="Here">

<caption>Here are some things I did</caption>

<tr>

<th>Sat</th>

<th>Sun</th>

<th>Mon</th>

</tr>

<tr>

<td>Played Ball</td>

<td>Watched Football</td>

<td>Worked</td>

</tr>

</table>

<form action="mail2.php" method="post"><strong>Send Message to this Email</strong><br />

<input type="text" name="email" size="40" /><br />

<p><strong>Subject</strong><br />

<input type="text" name="subject" size="40" /><br /></p>

<p><strong>Text Area</strong><br />

<textarea cols="40" rows="10" name="message">

Default Text in Text Area Box

</textarea>

<br /></p>

<p><strong>Text Input</strong><br />

<input type="text" name="textinput" /><br /></p>

<p><strong>Password Input</strong><br />

<input type="password" name="password" /><br /></p>

<p><strong>Radio Input</strong><br />

<input type="radio" name="radioinput" value="Love Radio Buttons" />Love Radio Buttons <input type="radio" name="radioinput" value="Hate Radio Buttons" />Hate Radio Buttons<br /></p>

<p><strong>Checkbox Input</strong><br />

<input type="checkbox" name="checkboxinput" value="Love Checkboxs" />Love Checkboxs <input type="checkbox" name="checkboxinput" value="Hate Checkboxs" />Hate Checkboxs<br /></p>

<p><strong>Select Input</strong><br />

<select name="selectinput">

<option value="loveselect">I love select buttons</option>

<option value="hateselect">I hate select buttons</option>

</select><br /></p>

<p><strong>Option Input</strong><br />

<select name="optioninput">

<option value="loveselect">I love option buttons</option>

<option value="hateselect">I hate option buttons</option>

</select><br /></p>

<p><input type="submit" value="Send" /> <input type="hidden" name="submitted" value="TRUE" /></p>

</form>

</body>

</html>
*/