package javaConcepts.self;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class ValidateDataTypeClass {
	public static void main(String[] args) {
		
	}
	
	public static boolean validateDataType(String input, String dataType) {
		boolean isValid = false;
		switch (dataType) {
		case "UUID":
			try {
				UUID uuid = UUID.fromString(input);
				System.out.println("rowkey <" + uuid + "> in correct format");
				isValid = true;
			} catch (Exception e) {
				System.out.println("Expected format is of UUID type. The provided <" + input + "> is not of this format.");
				System.out.println("Please enter in UUID format ********-****-****-****-************");
				isValid = false;
			}
			return isValid;

		case "STRING":
			Scanner scanner = new Scanner(input);
			input = input.trim();

			String validationResult = scanner.findInLine("^[a-zA-Z0-9_]*$"); // [^0-9()+\\-*\\/%]+

			// If the String has any of the above characters, then it is valid.
			// (Numbers are allowed)
			if (validationResult != null) {
				scanner.close();
				System.out.println("The input String: " + input + " is valid");
				isValid = true;
			} else {
				System.out.println("Expected format is of String type. The provided <" + input + "> is not of this format.");
				System.out.println("Please enter in String format");
				isValid = false;
			}
			return isValid;
		case "DATETIME":
			try {
				DateTime date = null;
				org.joda.time.format.DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
				date = parser.parseDateTime(input);
				System.out.println("Date <" + date + "> in correct format");
				isValid = true;
			} catch (Exception e) {
				System.out.println(
						"Expected format is of DateTime type. The provided <" + input + "> is not of this format.");
				System.out.println("Please enter in yyyy-MM-dd'T'HH:mm:ss.SSS format");
				isValid = false;
			}
			return isValid;
		case "DATE":
			try {
				Date date = null;
				SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				date = parser.parse(input);
				System.out.println("Date <" + date + "> in correct format");
				isValid = true;
			} catch (Exception e) {
				System.out.println("Expected format is of Date type. The provided <" + input + "> is not of this format.");
				System.out.println("Please enter in yyyy-MM-dd format");
				isValid = false;
			}
			return isValid;
		case "INTEGER":
			try { 
				Integer.parseInt(input); 
			} catch(NumberFormatException e) { 
				return false; 
			} catch(NullPointerException e) {
				return false;
			}
			// only got here if we didn't return false
			return true;
		//startCol instanceof Date
		default:
			return isValid;

		}
	}
}
