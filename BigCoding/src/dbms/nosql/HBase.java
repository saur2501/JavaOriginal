package dbms.nosql;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;

//NoSQL- Column Based Store, CP of CAP- (May compromise A)
public class HBase {
	static Connection connection;
	static Table table;
	static String tableNameString = "TLG_Wide12";
	static byte[] columnFamilyBytes = Bytes.toBytes("TagsWide");
	static String rowkey = "1";
	public static void main(String[] args) throws IOException {
		connection = getConnection();
		table = getTable(connection);
		//createTable();
		insertRecords();
		//retrieveRecords();
		//scanningRowkeys();
		//totalEntriesCounter();
	}

	@SuppressWarnings("unused")
	private static void totalEntriesCounter() throws IOException {		
		Scan scan = new Scan();
		scan.addFamily(columnFamilyBytes);
		scan.setMaxResultSize(100);
		scan.setFilter(new FirstKeyOnlyFilter());
		scan.setCaching(75);
		
		ResultScanner scanner = table.getScanner(scan);
		int count = 0;
		for (Result result : scanner) {
			String rowkey = Bytes.toString(result.getRow());
			
			Get g = new Get(Bytes.toBytes(rowkey));
	        g.addFamily(Bytes.toBytes("TagsWide"));
	        Result result2 = table.get(g);
	        count += result2.size();
		}
		System.out.println("Total Number of Entries = " + count);
	}

	@SuppressWarnings("unused")
	private static void scanningRowkeys() throws IOException {
		Scan scan = new Scan();
		scan.setMaxVersions(1);
        scan.setTimeRange (0L, 1716083300000L);
		scan.addFamily(columnFamilyBytes);
		scan.setMaxResultSize(100);
		scan.setFilter(new FirstKeyOnlyFilter());
		scan.setCaching(75);
				
		ResultScanner scanner = table.getScanner(scan);
		for (Result result : scanner) {						//for limiting number of records- (Result result : scanner.next(100))
			String rowkey = Bytes.toString(result.getRow());
			System.out.println(rowkey + " is bombastic");
		}
		System.out.println("Scanning rowkeys is done!");
	}

	@SuppressWarnings("unused")
	private static void retrieveRecords() throws IOException {
		Get get = new Get(Bytes.toBytes(rowkey));
		Set<String> columnNameSet = null;
		/*columnNameSet = new HashSet<>();
		columnNameSet.add("Quote");*/
		if (columnNameSet == null) {
	        get.addFamily(columnFamilyBytes);
		} else {
	        for (String columnName : columnNameSet) {
	          get.addColumn(columnFamilyBytes, Bytes.toBytes(columnName));
	        }
		}
		Result result = table.get(get);
		if (result.isEmpty()) {
	      System.out.println("No rows selected");
	    }

		HashMap<String, byte[]> resultMap = new HashMap<> ();;
	    while (result.advance()) {
	      final Cell c = result.current();
	      resultMap.put(Bytes.toString(CellUtil.cloneQualifier(c)),
	          (CellUtil.cloneValue(c)));
	    }
	    System.out.println(resultMap.keySet() + "= Keys\nValues = " + resultMap.values().size());
	}

	@SuppressWarnings("unused")
	private static void insertRecords() throws IOException {
		Put put = new Put(Bytes.toBytes(rowkey));
		
		HashMap<String, byte[]> values = new HashMap<> ();
		String string= "Greet Greatness Again!!";
		byte[] bytes = string.getBytes(Charset.forName("UTF-8"));
		values.put("Quote", bytes);
		for (Entry<String, byte[]> entry : values.entrySet()) {
	      byte[] value = entry.getValue();
	      put.addColumn(columnFamilyBytes, Bytes.toBytes(entry.getKey()), value);
	    }
		table.put(put);
		System.out.println("Point p is added to TLG_Wide's TagsWide");
	}

	private static void createTable() throws IOException {
		Admin admin = connection.getAdmin();
		HTableDescriptor table = new HTableDescriptor(TableName.valueOf("TLG_Wide16"));
		table.addFamily(new HColumnDescriptor("TagsWide"));

		if (!admin.tableExists(table.getTableName())) {
			System.out.print("Creating table.");
			admin.createTable(table);
			System.out.println(" Done.");
		}
	}
	
	private static Table getTable(Connection connection2) throws IOException {
		TableName tableName = TableName.valueOf(tableNameString);
		table = connection.getTable(tableName);
		return table;
	}

	private static Connection getConnection() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.50.147");
		conf.set("hbase.rootdir", "hdfs://192.168.50.147:8020/apps/hbase/data");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		Connection connection = ConnectionFactory.createConnection(conf);
		return connection;
	}
}
//Ignore exception- Could not locate executable null\bin\winutils.exe
//Code doesn't work here but outside cuz of incompatibility with hadoop versions- not the case with no specification