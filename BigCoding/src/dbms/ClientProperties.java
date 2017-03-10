package dbms;

import java.io.FileInputStream;
import java.util.Properties;

/*import com.google.common.base.Preconditions;
import com.siemens.bam.adm.studio.exception.SchemaException;*/

public class ClientProperties {
	public static void main(String[] args) throws Exception {
		ClientProperties prop = new ClientProperties("config/property.conf");
		System.out.println(prop.getOperation());
		System.out.println(prop.getNumberOfThreads());
		prop.setProp();
		System.out.println(prop.getNewProp());
	}
	private String getNewProp() {
		return prop.getProperty("NewProp");
	}
	private void setProp() {
		prop.setProperty("NewProp", "Krsna");
	}
	public static final Properties prop = new Properties();
	private static String DEFAULT_NUMBER_OF_THREADS = "1";
	
	public ClientProperties(String configurationFileName) throws Exception {
		prop.load(new FileInputStream(configurationFileName));
	}
	public ClientProperties() {
	}
	public String getClientId() {
		if (prop.getProperty("ClientId") == null || prop.getProperty("ClientId").length() == 0) {
			throw new RuntimeException("Client ID not set by user. Please set the client ID. System exiting");

		}
		return prop.getProperty("ClientId");
	}
	public String getOperation() {
		return prop.getProperty("Operation");
	}
	public int getNumberOfThreads() {

		return Integer.parseInt(prop.getProperty("NumberOfThreads", DEFAULT_NUMBER_OF_THREADS));
	}
	public String getDataStoreName() {
		return prop.getProperty("DatastoreName");
	}
}
