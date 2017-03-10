package dbms;

import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4J {
	public static void main(String[] args) {
		invokeByDefaults();
		invokeProgrammatically();
		invokeByPropertiesFile();
		invokeByXml();
		invokeByUtils();
		invokeBySlf4j();
	}

	private static void invokeBySlf4j() {
		System.out.println("Slf4j not yet implemented");
	}

	private static void invokeByXml() {
		Logger logger = Logger.getLogger(Log4J.class);
		String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "config" + File.separator + "log4j.xml";
		System.out.println("XML configured to " + log4jConfigFile);
        DOMConfigurator.configure(log4jConfigFile);
 
        logger.debug("this is a debug log message from invokeByXml");
        logger.info("this is a information log message from invokeByXml");
        logger.warn("this is a warning log message from invokeByXml");
	}

	private static void invokeByPropertiesFile() {
		Logger logger = Logger.getLogger(Log4J.class);
		System.out.println("In Java Build Path, Class Folder is set to the one containing log4j.properties");
		logger.info("This is my first log4j's statement from invokeByPropertiesFile");
	}

	private static void invokeByDefaults() {
		Logger logger = Logger.getLogger(Log4J.class);
		BasicConfigurator.configure();
		logger.info("This is my first log4j's statement from invokeByDefault");
	}

	private static void invokeProgrammatically() {
		// creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);
 
        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
 
        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("applog3.txt");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();
 
        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);
 
        // creates a custom logger and log messages
        Logger logger = Logger.getLogger(Log4J.class);
        logger.debug("this is a debug log message from invokeProgrammatically");
        logger.info("this is a information log message from invokeProgrammatically");
        logger.warn("this is a warning log message from invokeProgrammatically");
	}
	public static void invokeByUtils() {
		java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Log4J.class.getName());
		 LOGGER.setLevel(java.util.logging.Level.SEVERE);
         LOGGER.severe("Info Log Invoke by utils");
         LOGGER.warning("Info Log Invoke by utils");
         LOGGER.info("Info Log Invoke by utils");
         LOGGER.finest("Really not important Invoke by utils");
	}
}
