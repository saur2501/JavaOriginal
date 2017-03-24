package dbms.fileReads;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.GreekList;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.RomanList;
import com.itextpdf.text.ZapfDingbatsList;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PDFWriter
{
   public static void main(String[] args)
   {
	   Document document = new Document();
	   try
	   {
	       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SetAttributeExample.pdf"));
	       document.open();
	       document.add(new Paragraph("Some content here"));
	    
	       //Set attributes here
	       document.addAuthor("Gaur Sharma");
	       document.addCreationDate();
	       document.addCreator("HowToDoInJava.com");
	       document.addTitle("Set Attribute Example");
	       document.addSubject("An example to show how attributes can be added to pdf files.");
	    
	       //Add Image
	       Image image1 = Image.getInstance("temp.jpg");
	       //Fixed Positioning
	       image1.setAbsolutePosition(100f, 550f);
	       //Scale to new height and new width of image
	       image1.scaleAbsolute(200, 200);
	       //Add to document
	       document.add(image1);
	    
	       /*String imageUrl = "http://www.eclipse.org/xtend/images/java8_logo.png";
	       Image image2 = Image.getInstance(new URL(imageUrl));
	       document.add(image2);*/
	    
	       PdfPTable table = new PdfPTable(3); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {1f, 1f, 1f};
	        table.setWidths(columnWidths);
	 
	        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
	        cell1.setBorderColor(BaseColor.BLUE);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
	        cell2.setBorderColor(BaseColor.GREEN);
	        cell2.setPaddingLeft(10);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
	        cell3.setBorderColor(BaseColor.RED);
	        cell3.setPaddingLeft(10);
	        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        //To avoid having the cell border and the content overlap, if you are having thick cell borders
	        //cell1.setUserBorderPadding(true);
	        //cell2.setUserBorderPadding(true);
	        //cell3.setUserBorderPadding(true);
	 
	        table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	 
	        document.add(table);
	       
	      //Add ordered list
	        List orderedList = new List(List.ORDERED);
	        orderedList.add(new ListItem("Item 1"));
	        orderedList.add(new ListItem("Item 2"));
	        orderedList.add(new ListItem("Item 3"));
	        document.add(orderedList);
	     
	        //Add un-ordered list
	        List unorderedList = new List(List.UNORDERED);
	        unorderedList.add(new ListItem("Item 1"));
	        unorderedList.add(new ListItem("Item 2"));
	        unorderedList.add(new ListItem("Item 3"));
	        document.add(unorderedList);
	     
	        //Add roman list
	        RomanList romanList = new RomanList();
	        romanList.add(new ListItem("Item 1"));
	        romanList.add(new ListItem("Item 2"));
	        romanList.add(new ListItem("Item 3"));
	        document.add(romanList);
	     
	        //Add Greek list
	        GreekList greekList = new GreekList();
	        greekList.add(new ListItem("Item 1"));
	        greekList.add(new ListItem("Item 2"));
	        greekList.add(new ListItem("Item 3"));
	        document.add(greekList);
	     
	        //ZapfDingbatsList List Example
	        ZapfDingbatsList zapfDingbatsList = new ZapfDingbatsList(43, 30);
	        zapfDingbatsList.add(new ListItem("Item 1"));
	        zapfDingbatsList.add(new ListItem("Item 2"));
	        zapfDingbatsList.add(new ListItem("Item 3"));
	        document.add(zapfDingbatsList);
	     
	        //List and Sublist Examples
	        List nestedList = new List(List.UNORDERED);
	        nestedList.add(new ListItem("Item 1"));
	     
	        List sublist = new List(true, false, 30);
	        sublist.setListSymbol(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6)));
	        sublist.add("A");
	        sublist.add("B");
	        nestedList.add(sublist);
	     
	        nestedList.add(new ListItem("Item 2"));
	     
	        sublist = new List(true, false, 30);
	        sublist.setListSymbol(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6)));
	        sublist.add("C");
	        sublist.add("D");
	        nestedList.add(sublist);
	     
	        document.add(nestedList);
	     
	        
	       document.close();
	       writer.close();
	   } catch (Exception e)
	   {
	       e.printStackTrace();
	   }
   }
}

/*http://howtodoinjava.com/apache-commons/create-pdf-files-in-java-itext-tutorial/ for more (and reference)
also- http://www.vogella.com/tutorials/JavaPDF/article.html*/