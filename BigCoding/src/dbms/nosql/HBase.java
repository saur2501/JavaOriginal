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
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HBase {
	public static void main(String[] args) throws IOException {
		createTable();
		insertRecords();
		retrieveRecords();
	}

	@SuppressWarnings("unused")
	private static void retrieveRecords() throws IOException {
		Configuration conf = HBaseConfiguration.create();									//uses hbase-site.xml picked from hadoop fs- gives properties
		//conf.set("hbase.master", "192.168.237.135:60000");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.zookeeper.quorum", "192.168.237.135");
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		//conf.setBoolean("hbase.ipc.client.tcpnodelay", true);
		//reading
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide2");
		//Table table = connection.getTable(tableName);
		//this.durability = Durability.valueOf(HBaseConnection11.getHbaseProperties().getHBaseDurability().toUpperCase());
		String columnFamily = "TagsWide";
		byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);
		Table currentTable = connection.getTable(tableName);
		String key = "1";
		Get g = new Get(Bytes.toBytes(key));
		Set<String> fields = null;
		/*fields = new HashSet<>();
		fields.add("1");
		fields.add("Cutes");*/
		if (fields == null) {
	        g.addFamily(columnFamilyBytes);
		} else {
	        for (String field : fields) {
	          g.addColumn(columnFamilyBytes, Bytes.toBytes(field));
	        }
		}
		
		Result r = currentTable.get(g);
		if (r.isEmpty()) {
	      System.out.println("No rows selected");
	    }

		HashMap<String, byte[]> result = new HashMap<> ();;
	    while (r.advance()) {
	      final Cell c = r.current();
	      result.put(Bytes.toString(CellUtil.cloneQualifier(c)),
	          (CellUtil.cloneValue(c)));
	    }
	    System.out.println(result.keySet() + "-Keys\nValues-" + result.values().size());
	}

	private static void insertRecords() throws IOException {
		Configuration conf = HBaseConfiguration.create();
		//conf.set("hbase.master", "192.168.237.135:60000");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.zookeeper.quorum", "192.168.237.135");
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		//conf.setBoolean("hbase.ipc.client.tcpnodelay", true);
		
		Connection connection = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("TLG_Wide2");
		//Table table = connection.getTable(tableName);
		//this.durability = Durability.valueOf(HBaseConnection11.getHbaseProperties().getHBaseDurability().toUpperCase());
		String columnFamily = "TagsWide";
		byte[] columnFamilyBytes = Bytes.toBytes(columnFamily);
		
		//insert records individually
		Table currentTable = connection.getTable(tableName);
		String key = "1";
		Put p = new Put(Bytes.toBytes(key));
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
		int i = 0;
		System.out.println("Test" + i++);
		Configuration conf = HBaseConfiguration.create();
		//conf.set("hbase.master", "192.168.237.135:60000");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.zookeeper.quorum", "192.168.237.135");
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		//conf.setBoolean("hbase.ipc.client.tcpnodelay", true);
		System.out.println("Test" + i++);
		Connection connection = ConnectionFactory.createConnection(conf);
		Admin admin = connection.getAdmin();
		System.out.println("Test" + i++);
		HTableDescriptor table = new HTableDescriptor(TableName.valueOf("TLG_Wide2"));
		System.out.println("Test" + i++);
		table.addFamily(new HColumnDescriptor("TagsWide"));
		System.out.println("Test" + i++);

		if (!admin.tableExists(table.getTableName())) {
			System.out.print("Creating table. ");
			admin.createTable(table);
			System.out.println(" Done.");
		}
	}
}
//Ignore exception- Could not locate executable null\bin\winutils.exe