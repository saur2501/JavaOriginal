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
	private static String hbaseIP = "132.186.179.201";
	private static String tableNameString = "TLG_Wide12";
	private static String columnFamilyNameString = "TagsWide";
	public static void main(String[] args) throws IOException {
		createTable();
		insertRecords();
		retrieveRecords();
		scanningRowkeys();
		totalEntriesCounter();
	}

	private static void totalEntriesCounter() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", hbaseIP);

		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf(tableNameString);
		Table table = connection.getTable(tableName);
		
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes(columnFamilyNameString));
		scan.setMaxResultSize(100);
		scan.setFilter(new FirstKeyOnlyFilter());
		scan.setCaching(75);
		
		ResultScanner scanner = table.getScanner(scan);
		int count = 0;
		for (Result result : scanner) {
			String rowkey = Bytes.toString(result.getRow());
			
			Get g = new Get(Bytes.toBytes(rowkey));
	        g.addFamily(Bytes.toBytes(columnFamilyNameString));
	        Result result2 = table.get(g);
	        count += result2.size();
		}
		System.out.println("Total Number of Entries = " + count);
	}

	private static void scanningRowkeys() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", hbaseIP);

		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf(tableNameString);
		Table table = connection.getTable(tableName);
				
		Scan scan = new Scan();
		scan.setMaxVersions(1);
        scan.setTimeRange (0L, 1716083300000L);
		scan.addFamily(Bytes.toBytes(columnFamilyNameString));
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
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", hbaseIP);
		//reading
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf(tableNameString);
		String columnFamily = columnFamilyNameString;
		byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);
		Table table = connection.getTable(tableName);
		String rowkey = "1";
		Get get = new Get(Bytes.toBytes(rowkey));
		Set<String> columnNameSet = null;
		/*columnNameSet = new HashSet<>();
		columnNameSet.add("Quote");*/
		if (columnNameSet == null) {
	        get.addFamily(columnFamilyBytes);
		} else {
	        for (String field : columnNameSet) {
	          get.addColumn(columnFamilyBytes, Bytes.toBytes(field));
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
	    System.out.println(resultMap.keySet() + "-Keys\nValues-" + resultMap.values().size());
	}

	private static void insertRecords() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", hbaseIP);
		
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide12");
		Table table = connection.getTable(tableName);
		//this.durability = Durability.valueOf(HBaseConnection11.getHbaseProperties().getHBaseDurability().toUpperCase());
		
		String columnFamily = columnFamilyNameString;
		byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);
		
		//make hashmap for insertion
		HashMap<String, byte[]> values = new HashMap<> ();
		String string= "Greet Greatness Again!!";
		byte[] bytes = string.getBytes(Charset.forName("UTF-8"));
		values.put("Quote", bytes);
		
		//Make put object for insertion
		String rowkey = "1";
		Put put = new Put(Bytes.toBytes(rowkey));
	    //p.setDurability(durability);
		for (Entry<String, byte[]> entry : values.entrySet()) {
	      byte[] value = entry.getValue();
	      put.addColumn(columnFamilyBytes, Bytes.toBytes(entry.getKey()), value);
	    }
		
		table.put(put);
		System.out.println("Point p is added to TLG_Wide's TagsWide");
	}

	private static void createTable() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", hbaseIP);
		Connection connection = ConnectionFactory.createConnection(conf);
		Admin admin = connection.getAdmin();
		HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableNameString));
		hTableDescriptor.addFamily(new HColumnDescriptor(columnFamilyNameString));

		if (!admin.tableExists(hTableDescriptor.getTableName())) {
			System.out.print("Creating table.");
			admin.createTable(hTableDescriptor);
			System.out.println(" Done.");
		}
	}
}
//Ignore exception- Could not locate executable null\bin\winutils.exe