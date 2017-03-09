/**
 * 
 */
package dbms;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.siemens.bam.adm.studio.exception.SchemaException;

public class ClientProperties {

	public static final Properties prop = new Properties();

	private String operation;
	private String mode;
	private Map<String, List<String>> metricList;
	private String queryFilePath;
	private long startTime;
	private long endTime;

	private static String DEFAULT_NUMBER_OF_UNIQUE_KEYS = "10";
	private static String DEFAULT_NUMBER_OF_RECORDS = "-1";
	private static String DEFAULT_BATCH_SIZE = "10";
	private static String DEFAULT_NUMBER_OF_THREADS = "1";
	private static String DEFAULT_METRIC_STEP = "1";
	private static String DEFAULT_METRIC_FUNCTION = "avg";
	private static String DEFAULT_REPORT_SUMMARY_RATE = "10";
	private static String DEFAULT_PAGINATION_LIMIT = "10";

	public ClientProperties(String configurationFileName) throws Exception {
		prop.load(new FileInputStream(configurationFileName));

	}

	public ClientProperties() {
	}

	/*
	 * Client Properties - Common Parameters
	 * 
	 */

	/**
	 * Returns the unique value used to identify a client
	 * 
	 * @return
	 */
	public String getClientId() {
		if (prop.getProperty("ClientId") == null || prop.getProperty("ClientId").length() == 0) {
			throw new RuntimeException("Client ID not set by user. Please set the client ID. System exiting");

		}
		return prop.getProperty("ClientId");
	}

	/**
	 * Returns the operation that the client is configured to execute
	 * 
	 * @return
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Returns the number of threads to be spawned to execute a workload Default
	 * Value: 10
	 * 
	 * @return
	 */
	public int getNumberOfThreads() {

		return Integer.parseInt(prop.getProperty("NumberOfThreads", DEFAULT_NUMBER_OF_THREADS));
	}

	/**
	 * Returns the Data store name
	 * 
	 * @return
	 * @throws SchemaException
	 */
	public String getDataStoreName() {
		return prop.getProperty("DatastoreName");
	}
}
