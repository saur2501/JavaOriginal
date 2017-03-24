package dbms.nosql;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;

/*
 * Schema
 * =======
//Using version 3.5 of datastax for windows
CREATE KEYSPACE TLG_V_2 with replication = {'class' : 'SimpleStrategy', 'replication_factor':1};

use TLG_V_2;
CREATE TABLE testCassandraStore(
      MeasUID uuid,
      MeasTimeStamp timestamp,
      ValueFloat double,
      MeasType text,
      PRIMARY KEY ((MeasUID,MeasType), MeasTimeStamp)
) WITH COMPACT STORAGE;
select * from testCassandraStore limit 10;
 *  */

public class Cassandra {
	public static void main(String[] args) throws Exception {
		write();
		query1();
		query2();
	}
	private static void write() throws Exception {
		Builder builder = new Cluster.Builder().addContactPoint("localhost");
		Cluster cluster = builder.withPort(9042)
				.withQueryOptions(new QueryOptions().setConsistencyLevel(
						ConsistencyLevel.valueOf("ONE")))
				.withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build())).build();
			//cluster.connect("TLG_V_2");
		Session session = cluster.connect("TLG_V_2");
		Insert is = QueryBuilder.insertInto("TLG_V_2", "testCassandraStore");
					
		is.value("MeasUID", QueryBuilder.bindMarker());
		is.value("MeasType", QueryBuilder.bindMarker());
		is.value("valuefloat", QueryBuilder.bindMarker());
		is.value("MeasTimeStamp", QueryBuilder.bindMarker());
					
		PreparedStatement statement = session.prepare(is);
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement.setUUID("MeasUID", UUID.randomUUID());
		boundStatement.setString("MeasType", "T");
		boundStatement.setDouble("valuefloat", 13.01);
		boundStatement.setTimestamp("MeasTimeStamp", new Date());
		ResultSetFuture rsf = session.executeAsync(boundStatement.bind());
		rsf.get();
		
		//BatchStatements
		boundStatement = new BoundStatement(statement);
		boundStatement.setUUID("MeasUID", UUID.randomUUID());
		boundStatement.setString("MeasType", "T");
		boundStatement.setDouble("valuefloat", 13.01);
		boundStatement.setTimestamp("MeasTimeStamp", new Date());
		BatchStatement batchStatement = new BatchStatement();
		batchStatement.add(boundStatement);
		rsf = session.executeAsync(batchStatement);
		rsf.get();
		cluster.close();
	}
	public void writeSuperColumn() {
		//BoundStatement boundStatement = boundStatement.setList(entry.getColName(), (List<Object>) fieldDataValue);
	}
	
	public static void query1() {
		Cluster cluster = null;
		try {
		    cluster = Cluster.builder()                                                    // (1)
		            .addContactPoint("localhost")
		            .build();
		    Session session = cluster.connect();                                           // cluster.connect(DBNAME)
		    session.execute("use TLG_V_2;");
		    ResultSet rs = session.execute("Select * from testCassandraStore limit 10");					//SELECT cluster_name, listen_address FROM system.local;
		    		//CREATE TABLE monkeySpecies (species text PRIMARY KEY,common_name text,population varint,average_size int ) WITH comment='Important biological records' AND read_repair_chance = 1.0;");    // (3)
		    Row row = rs.one();
		    System.out.println(row.getString("meastype"));                          	// (4)
		} finally {
		    if (cluster != null) cluster.close();											// (5)
		}
	}
	public static void query2() {
		System.out.println("Inside Query Method");
		String DBNAME = "TLG_V_2";
		Builder builder = new Cluster.Builder().addContactPoint("127.0.0.1");				//builder.addContactPoint(hostIPs[i]);
		Cluster cluster = builder.withPort(9042)
				.withQueryOptions(new QueryOptions().setConsistencyLevel(
						ConsistencyLevel.valueOf("ONE")))
				.withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build())).build();
		Session session = cluster.connect(DBNAME);
		ResultSet results = session.execute("Select * from testCassandraStore limit 10");
		Row row = results.one();
	    System.out.println(row.getString("meastype"));
		System.out.println(results);
		cluster.close();
	}
}
