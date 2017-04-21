/*package dbms.nosql;
//TODO- Not running- pom dependencies added but gives org.neo4j.helpers.Settings classNotFoundException
//Ref- https://www.youtube.com/watch?v=-g-vCsZO_3g
//NoSQL- guarrantees CA of CAP.
import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class Neo4JDB {
	public static void main(String[] args) {
		GraphDatabaseService db = null;
		try {
			db = new GraphDatabaseFactory()
					.newEmbeddedDatabaseBuilder(new File("D:\\DBData\\Neo4j"))
					.setConfig( GraphDatabaseSettings.pagecache_memory, "512M" )
				    .setConfig( GraphDatabaseSettings.string_block_size, "60" )
				    .setConfig( GraphDatabaseSettings.array_block_size, "300" )
					.newGraphDatabase();
		} catch (org.neo4j.kernel.lifecycle.LifecycleException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		Transaction tx1 = db.beginTx();
	    try
	    {
	    	db.execute("MATCH(r:STUDENT) return r");
	    	System.out.println("Executing");
	        tx1.success();
	        
	    } finally {
	        tx1.close();
	    }
	    System.out.println("DOne!!");
		db.shutdown();
	}
}
*/