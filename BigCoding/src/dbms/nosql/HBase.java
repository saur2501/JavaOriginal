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

public class HBase {
	public static void main(String[] args) throws IOException {
		createTable();
		insertRecords();
		retrieveRecords();
		scanningRowkeys();
		totalEntriesCounter();
	}

	private static void totalEntriesCounter() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.237.140");
		
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("TagsWide"));
		scan.setMaxResultSize(100);
		scan.setFilter(new FirstKeyOnlyFilter());
		scan.setCaching(75);
		
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide12");
		Table table = connection.getTable(tableName);
		
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

	private static void scanningRowkeys() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.237.140");
		
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("TagsWide"));
		scan.setMaxResultSize(100);
		scan.setFilter(new FirstKeyOnlyFilter());
		scan.setCaching(75);
		
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide12");
		Table table = connection.getTable(tableName);
		
		ResultScanner scanner = table.getScanner(scan);
		for (Result result : scanner) {						//for limiting number of records- (Result result : scanner.next(100))
			String rowkey = Bytes.toString(result.getRow());
			System.out.println(rowkey + " is bombastic");
		}
	}

	@SuppressWarnings("unused")
	private static void retrieveRecords() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.237.140");
		//reading
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide12");
		String columnFamily = "TagsWide";
		byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);
		Table table = connection.getTable(tableName);
		String rowkey = "1";
		Get g = new Get(Bytes.toBytes(rowkey));
		Set<String> columnNameSet = null;
		/*columnNameSet = new HashSet<>();
		columnNameSet.add("Quote");*/
		if (columnNameSet == null) {
	        g.addFamily(columnFamilyBytes);
		} else {
	        for (String field : columnNameSet) {
	          g.addColumn(columnFamilyBytes, Bytes.toBytes(field));
	        }
		}
		
		Result result = table.get(g);
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
		conf.set("hbase.zookeeper.quorum", "192.168.237.140");
		
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide12");
		//Table table = connection.getTable(tableName);
		//this.durability = Durability.valueOf(HBaseConnection11.getHbaseProperties().getHBaseDurability().toUpperCase());
		String columnFamily = "TagsWide";
		byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);
		
		//insert records individually
		Table currentTable = connection.getTable(tableName);
		String rowkey = "1";
		Put p = new Put(Bytes.toBytes(rowkey));
	    //p.setDurability(durability);
		
		HashMap<String, byte[]> values = new HashMap<> ();
		String string= "Greet Greatness Again!!";
		byte[] bytes = string.getBytes(Charset.forName("UTF-8"));
		values.put("Quote", bytes);
		for (Entry<String, byte[]> entry : values.entrySet()) {
	      byte[] value = entry.getValue();
	      p.addColumn(columnFamilyBytes, Bytes.toBytes(entry.getKey()), value);
	    }
		currentTable.put(p);
		System.out.println("Point p is added to TLG_Wide's TagsWide");
	}

	private static void createTable() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.237.140");
		Connection connection = ConnectionFactory.createConnection(conf);
		Admin admin = connection.getAdmin();
		HTableDescriptor table = new HTableDescriptor(TableName.valueOf("TLG_Wide12"));
		table.addFamily(new HColumnDescriptor("TagsWide"));

		if (!admin.tableExists(table.getTableName())) {
			System.out.print("Creating table.");
			admin.createTable(table);
			System.out.println(" Done.");
		}
	}
}
//Ignore exception- Could not locate executable null\bin\winutils.exe