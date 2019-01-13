import java.lang.reflect.*;

import javax.swing.*;

public class InterpreterDP {
	public static void main(String[] args) {
		MeasurementConversion measurementConversion = new MeasurementConversion();
		measurementConversion.run();
	}
}

class MeasurementConversion {
	
	public void run(){
		
		// Create pop up window that asks for a question
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String questionAsked = JOptionPane.showInputDialog(frame, "What is your question");
		
		// Add the question to the context for analysis
		
		ConversionContext question = new ConversionContext(questionAsked);
		
		String fromConversion = question.getFromConversion();
		
		String toConversion = question.getToConversion();
		
		double quantity = question.getQuantity();
		
		try {
			
			// Get class based on the from conversion string
			
			Class tempClass = Class.forName(fromConversion);
			
			// Get the constructor dynamically for the conversion string
			
			Constructor con = tempClass.getConstructor();
			
			// Create a new instance of the object you want to convert from
			
			Object convertFrom = (Expression) con.newInstance();
			
			// Define the method parameters expected by the method that
			// will convert to your chosen unit of measure
			
			Class[] methodParams = new Class[]{Double.TYPE};
			
			// Get the method dynamically that will be needed to convert
			// into your chosen unit of measure

			Method conversionMethod = tempClass.getMethod(toConversion, methodParams);
			
			// Define the method parameters that will be passed to the above method
			
			Object[] params = new Object[]{new Double(quantity)};
			
			// Get the quantity after the conversion
			
			String toQuantity = (String) conversionMethod.invoke(convertFrom, params);
			
			// Print the results
			
			String answerToQues = question.getResponse() + 
					toQuantity + " " + toConversion;
			
			JOptionPane.showMessageDialog(null,answerToQues);
			
			// Closes the frame after OK is clicked
			
			frame.dispose();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

class ConversionContext {

    private String conversionQues = "";
    private String conversionResponse = "";
    private String fromConversion = "";
    private String toConversion = "";
    private double quantity;
    
    String[] partsOfQues;

    public ConversionContext(String input)
    {
      this.conversionQues = input;
      
      partsOfQues = getInput().split(" ");
      
      fromConversion = getCapitalized(partsOfQues[1]);
      
      toConversion = getLowercase(partsOfQues[3]);
      
      quantity = Double.valueOf(partsOfQues[0]);
      
      conversionResponse = partsOfQues[0] + " "+ partsOfQues[1] + " equals ";
    }

    public String getInput() { return conversionQues; }
    
    public String getFromConversion() { return fromConversion; }
    
    public String getToConversion() { return toConversion; }
    
    public String getResponse() { return conversionResponse; }
    
    public double getQuantity() { return quantity; }
    
    // Make String lowercase
    
    public String getLowercase(String wordToLowercase){
    	
    	return wordToLowercase.toLowerCase();
    	
    }
    
    // Capitalizes the first letter of a word
    
    public String getCapitalized(String wordToCapitalize){
    	
    	// Make characters lower case
    	
    	wordToCapitalize = wordToCapitalize.toLowerCase();
    	
    	// Make the first character uppercase
    	
    	wordToCapitalize = Character.toUpperCase(wordToCapitalize.charAt(0)) + wordToCapitalize.substring(1);
    	
    	// Put s on the end if not there
    	
    	int lengthOfWord = wordToCapitalize.length();
    	
    	if((wordToCapitalize.charAt(lengthOfWord -1)) != 's'){
    		
    		wordToCapitalize = new StringBuffer(wordToCapitalize).insert(lengthOfWord, "s").toString();
    		
    	}
    	
    	return wordToCapitalize;
    	
    }

}

abstract class Expression {
	
	public abstract String gallons(double quantity);
	public abstract String quarts(double quantity);
	public abstract String pints(double quantity);
	public abstract String cups(double quantity);
	public abstract String tablespoons(double quantity);
	
}

class Gallons extends Expression{

	public String gallons(double quantity) {
		
		return Double.toString(quantity);
	}

	public String quarts(double quantity) {
		return Double.toString(quantity*4);
	}

	public String pints(double quantity) {
		return Double.toString(quantity*8);
	}

	public String cups(double quantity) {
		return Double.toString(quantity*16);
	}

	public String tablespoons(double quantity) {
		return Double.toString(quantity*256);
	}
	
}

class Quarts extends Expression{

	public String gallons(double quantity) {
		
		return Double.toString(quantity/4);
	}

	public String quarts(double quantity) {
		return Double.toString(quantity);
	}

	public String pints(double quantity) {
		return Double.toString(quantity*2);
	}

	public String cups(double quantity) {
		return Double.toString(quantity*4);
	}

	public String tablespoons(double quantity) {
		return Double.toString(quantity*64);
	}
	
}

class Pints extends Expression{

	public String gallons(double quantity) {
		
		return Double.toString(quantity/8);
	}

	public String quarts(double quantity) {
		return Double.toString(quantity/2);
	}

	public String pints(double quantity) {
		return Double.toString(quantity);
	}

	public String cups(double quantity) {
		return Double.toString(quantity*2);
	}

	public String tablespoons(double quantity) {
		return Double.toString(quantity*32);
	}
	
}

class Cups extends Expression{

	public String gallons(double quantity) {
		
		return Double.toString(quantity/16);
	}

	public String quarts(double quantity) {
		return Double.toString(quantity/4);
	}

	public String pints(double quantity) {
		return Double.toString(quantity/2);
	}

	public String cups(double quantity) {
		return Double.toString(quantity);
	}

	public String tablespoons(double quantity) {
		return Double.toString(quantity*16);
	}
	
}

class Tablespoons extends Expression{

	public String gallons(double quantity) {
		
		return Double.toString(quantity/256);
	}

	public String quarts(double quantity) {
		return Double.toString(quantity/64);
	}

	public String pints(double quantity) {
		return Double.toString(quantity/32);
	}

	public String cups(double quantity) {
		return Double.toString(quantity/16);
	}

	public String tablespoons(double quantity) {
		return Double.toString(quantity);
	}
	
}