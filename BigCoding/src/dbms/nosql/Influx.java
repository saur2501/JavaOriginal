package dbms.nosql;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class Influx {
	public static void main(String[] args){
		InfluxDB influxDB = InfluxDBFactory.connect("http://132.186.179.201:8086", "influx", "influx");
		String dbName = "newz";
		influxDB.createDatabase(dbName);

		BatchPoints batchPoints = BatchPoints
		                .database(dbName)
		                .tag("async", "true")
		                .retentionPolicy("autogen")
		                .consistency(ConsistencyLevel.ALL)
		                .build();
		Point point1 = Point.measurement("cpu")
		                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
		                    .addField("idle", 90L)
		                    .addField("user", 9L)
		                    .addField("system", 1L)
		                    .build();
		Point point2 = Point.measurement("disk")
		                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
		                    .addField("used", 80L)
		                    .addField("free", 1L)
		                    .build();
		batchPoints.point(point1);
		batchPoints.point(point2);
		influxDB.write(batchPoints);
		
		Query query = new Query("select * from /.*/ limit 10", dbName);				//dbname compulsory for queries- except show databases kind.
		QueryResult queryResult = influxDB.query(query);
		System.out.println(queryResult.getResults());
		System.out.println("With formatting \n");
		System.out.println(queryResult.getResults().get(0).getSeries().get(0).getColumns());		//column names, also can use getTags, etc
		int count = 0;
		while(count != queryResult.getResults().get(0).getSeries().get(0).getValues().size()) {
			System.out.println(queryResult.getResults().get(0).getSeries().get(0).getValues().get(count));		//for first series (aka table)
			System.out.println(queryResult.getResults().get(0).getSeries().get(1).getValues().get(count));		//for second series (aka table)
			count++;
		}
		System.out.println("Execution ends!");
		//influxDB.deleteDatabase(dbName);
	}
}
//create database in influx and update credentials b4 exec