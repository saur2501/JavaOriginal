package dbms.fileReads;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVWriter {

	public static void saveInCSV(Map<String, LinkedHashMap<String, String>> allmetricsmap, String nameOfFile)
			throws Exception {
		System.out.println("Context- CSVReporter/saveInCSVMetricsMap; name of file- " + nameOfFile
				+ "; $prop; \nQueryMetricMap : \nKeyset: " + allmetricsmap.keySet() + "\nValues"
				+ allmetricsmap.values());
		// Setting up header-names for the CSV
		String[] header = null;
		int i = 0;
		System.out.println("Size of metricsMap = " + allmetricsmap.size());
		for (Entry<String, LinkedHashMap<String, String>> metricsInstance : allmetricsmap.entrySet()) { // Single
																										// iteration
																										// of
																										// first
																										// elt
			String metricKey = metricsInstance.getKey();
			System.out.println(metricKey);
			Map<String, String> metricsList = metricsInstance.getValue();
			header = new String[metricsList.size() + 1];
			for (Entry<String, String> nextMetric : metricsList.entrySet()) {
				header[i] = nextMetric.getKey();
				i++;
			}
			break;
		}
		header[i] = "queryName";
		System.out.println("Header size = " + header.length + " " + Arrays.toString(header));

		ICsvMapWriter mapWriter = null;
		try {
			mapWriter = new CsvMapWriter(new FileWriter("output/abc.csv"),
					CsvPreference.STANDARD_PREFERENCE);
			mapWriter.writeHeader(header);

			for (Entry<String, LinkedHashMap<String, String>> metricsInstance : allmetricsmap.entrySet()) {
				String query = metricsInstance.getKey();
				Map<String, String> metricsList = metricsInstance.getValue();
				metricsList.put("queryName", query);
				System.out.println("Query- " + query + "\nMetrics Keys- " + metricsList.keySet() + "\nMetricValues- "
						+ metricsList.values());
				mapWriter.write(metricsList, header);
			}

		} catch (Exception e) {
			System.out.println("Some error happened while writing csv");
		} finally {
			if (mapWriter != null) {
				mapWriter.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		LinkedHashMap<String, LinkedHashMap<String, String>> map = new LinkedHashMap<>();
		LinkedHashMap<String, String> mapper = new LinkedHashMap<>();
		mapper.put("key1", "value1");
		mapper.put("key2", "value2");
		map.put("HyperKey1", mapper);
		saveInCSV(map, "Hi");
		LinkedHashMap<String, String> mapper2 = new LinkedHashMap<>();
		mapper2.put("key1", "value3");
		mapper2.put("key4", "value4");
		map.put("HyperKey2", mapper2);
		saveInCSV(map, "Hi");
	}
}
