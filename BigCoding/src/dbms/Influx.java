//import java.util.concurrent.TimeUnit;
//
//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.InfluxDB.ConsistencyLevel;
//import org.influxdb.dto.BatchPoints;
//import org.influxdb.dto.Point;
//import org.influxdb.dto.Query;
//import org.influxdb.dto.QueryResult;
//import org.junit.Test;
//
//import com.db.influxdb.Configuration;
//import com.db.influxdb.DataWriter;
//
//public class Influx {
//	public static void main(String[] args){
//		InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.1.26:8086", "influx", "influx");
//		String dbName = "NOAA_water_database";
//		influxDB.createDatabase(dbName);
//
//		BatchPoints batchPoints = BatchPoints
//		                .database(dbName)
//		                .tag("async", "true")
//		                .retentionPolicy("autogen")
//		                .consistency(ConsistencyLevel.ALL)
//		                .build();
//		Point point1 = Point.measurement("cpu")
//		                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//		                    .addField("idle", 90L)
//		                    .addField("user", 9L)
//		                    .addField("system", 1L)
//		                    .build();
//		Point point2 = Point.measurement("disk")
//		                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//		                    .addField("used", 80L)
//		                    .addField("free", 1L)
//		                    .build();
//		batchPoints.point(point1);
//		batchPoints.point(point2);
//		influxDB.write(batchPoints);
//		//Query query = new Query("SELECT \"host\", \"region\", \"value\" FROM \"cpu\";", dbName);
//		//Query query = new Query("show databases", null);
//		//Query query = new Query("select * from /.*/", null);
//		Query query = new Query("select * from /.*/ limit 10", dbName);
//		QueryResult queryResult = influxDB.query(query);
//		//while(queryResult != null) {
//			System.out.println(queryResult.getResults());
//			//System.out.println(queryResult.getResults().get(0).getSeries().get(0).getColumns());
//		//}
//		System.out.println("Time Over");
//		/*if(queryResult != null) {
//			System.out.println("It ain't null!");
//		} else System.out.println("It's null, baby!");*/
//		//influxDB.deleteDatabase(dbName);
//	}
//	public int daneg() {
//		return 8;
//	}
//	@Test
//	public void write() throws Exception {
//		// For Writing Data
//		Configuration configuration = new Configuration("172.17.26.12", "8086", "root", "root", "Localhost");
//		DataWriter writer = new DataWriter(configuration);
//		writer.setMeasurement("sampleMeasurement1");
//
//		// Default is in seconds
//		writer.setTimeUnit(TimeUnit.MILLISECONDS);
//
//		writer.addField("field1", 12212);
//		writer.addField("field2", 22.44);
//		writer.addField("field3", "thisIsString");
//		writer.addField("field4", false);
//		writer.addTag("hostname", "server001");
//
//		// If we'll set time it will set automatically
//		writer.setTime(System.currentTimeMillis());
//		writer.writeData();
//
//		writer.addField("field1", 112);
//		writer.addField("field2", 21.44);
//		writer.addField("field3", "thisIsString1");
//		writer.addField("field4", true);
//		// Influxdb saves one point at one time. To add another point at same
//		// time we can use tags otherwise it will override the previous point.
//		writer.addTag("disk_type", "HDD");
//		writer.setTime(System.currentTimeMillis());
//
//		writer.writeData();
//
//
//	}
//}
