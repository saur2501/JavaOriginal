package dbms.oodbms;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class DB4oDemo {
	public static void main(String[] args) {
		// accessDb4o
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "output/Pilot.data");
		try {
			insert(db);
			select(db);
			update(db);
			delete(db);
		} catch (Exception e) {
		} finally {
			db.close();
		}
	}

	private static void delete(ObjectContainer db) {
		ObjectSet<Object> result = db.queryByExample(new Pilot("Michael Schumacher", 0));
		Pilot found = (Pilot) result.next();
		db.delete(found);
		System.out.println("Deleted " + found);
		select(db);

	}

	private static void update(ObjectContainer db) {
		ObjectSet<Object> result = db.queryByExample(new Pilot("Michael Schumacher", 0));
		Pilot found = (Pilot) result.next();
		found.addPoints(11);
		db.store(found);
		System.out.println("Added 11 points for " + found);
		result = db.queryByExample(new Pilot("Michael Schumacher", 0));
		while (result.hasNext()) {
			System.out.println(result.next());
		}
	}

	private static void select(ObjectContainer db) {
		Pilot proto = new Pilot(null, 0); // OR new Pilot("Michael Schumacher",
											// 0); OR new Pilot(null, 100);
		ObjectSet<Object> result = db.queryByExample(proto);
		System.out.println(result.size());
		while (result.hasNext()) {
			System.out.println(result.next());
		}
	}

	private static void insert(ObjectContainer db) {
		Pilot pilot1 = new Pilot("Michael Schumacher", 100);
		db.store(pilot1);
		System.out.println("Stored " + pilot1);
		Pilot pilot2 = new Pilot("Rubens Barrichello", 99);
		db.store(pilot2);
		System.out.println("Stored " + pilot2);
	}
}

class Pilot {
	private String name;
	private int points;

	public Pilot(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name + "/" + points;
	}
}
//Ref- http://www.odbms.org/wp-content/uploads/2013/11/db4o-7.10-tutorial-java.pdf- more to be included
//using db4o-8.0...all-java5.jar reference library