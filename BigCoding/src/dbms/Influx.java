package dbms;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

/*import com.db.influxdb.Configuration;
import com.db.influxdb.DataWriter;*/

public class Influx {
	public static void main(String[] args){
		InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.1.26:8086", "influx", "influx");
		String dbName = "new";
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
		//Query query = new Query("SELECT \"host\", \"region\", \"value\" FROM \"cpu\";", dbName);
		//Query query = new Query("show databases", null);
		//Query query = new Query("select * from /.*/", null);
		Query query = new Query("select * from /.*/ limit 10", dbName);
		QueryResult queryResult = influxDB.query(query);
		//while(queryResult != null) {
			System.out.println(queryResult.getResults());
			//System.out.println(queryResult.getResults().get(0).getSeries().get(0).getColumns());
		//}
		System.out.println("Execution ends!");
		/*if(queryResult != null) {
			System.out.println("It ain't null!");
		} else System.out.println("It's null, baby!");*/
		//influxDB.deleteDatabase(dbName);
	}
}