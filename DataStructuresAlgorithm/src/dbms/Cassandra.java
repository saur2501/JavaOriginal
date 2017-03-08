/*import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Cassandra {
	public static void main(String[] args) {
		Cluster cluster = null;
		try {
		    cluster = Cluster.builder()                                                    // (1)
		            .addContactPoint("localhost")
		            .build();
		    Session session = cluster.connect();                                           // (2)
		    //session.execute("use Excelsior;");
		    ResultSet rs = session.execute("SELECT cluster_name, listen_address FROM system.local");
		    		//CREATE TABLE monkeySpecies (species text PRIMARY KEY,common_name text,population varint,average_size int ) WITH comment='Important biological records' AND read_repair_chance = 1.0;");    // (3)
		    Row row = rs.one();
		    System.out.println(row.getString("cluster_name"));                          // (4)
		} finally {
		    if (cluster != null) cluster.close();                                          // (5)
		}
	}
}
*/