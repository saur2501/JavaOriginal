package dbms.nosql;

//NoSQL- Document based store, ensures CP of CAP (May compromise A)
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.gte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDB {
	static MongoClient mongoClient;

	public static void main(String args[]) {
		MongoCollection<Document> collection = getCollection();
		insertDocuments(collection);
		selectDocuments(collection);
		updateDocuments(collection);
		deleteDocuments(collection);
		createIndex(collection);
		mongoClient.close();
	}

	private static void createIndex(MongoCollection<Document> collection) {
		collection.createIndex(new Document("i", 1));
	}

	private static void deleteDocuments(MongoCollection<Document> collection) {
		collection.deleteOne(eq("i", 110));

		DeleteResult deleteResult = collection.deleteMany(gte("i", 100));
		System.out.println(deleteResult.getDeletedCount());
	}

	private static void updateDocuments(MongoCollection<Document> collection) {
		collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)));

		UpdateResult updateResult = collection.updateMany(lt("i", 100), new Document("$set", new Document("i", 110)));
		System.out.println(updateResult.getModifiedCount());
	}

	private static void selectDocuments(MongoCollection<Document> collection) {
		Document document = collection.find().first();
		System.out.println(document.toJson());

		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
		cursor.close();

		document = collection.find(eq("i", 71)).first();
		System.out.println(document.toJson());

		Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document.toJson());
			}
		};

		// importing static filters to use gt, eq, etc
		collection.find(gt("i", 50)).forEach(printBlock);

		collection.find(and(gt("i", 50), lte("i", 100))).forEach(printBlock);
	}

	private static void insertDocuments(MongoCollection<Document> collection) {
		Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1)
				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
				.append("info", new Document("x", 203).append("y", 102));

		collection.insertOne(doc);
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < 100; i++) {
			documents.add(new Document("i", i));
		}
		collection.insertMany(documents);
		System.out.println(collection.count() + " documents inserted");
	}

	private static MongoCollection<Document> getCollection() {
		// To connect to mongodb server
		mongoClient = new MongoClient("localhost", 27017);

		// Now connect to your databases
		MongoDatabase db = mongoClient.getDatabase("test");

		//db.createCollection("testedd");
		MongoCollection<Document> collection = db.getCollection("testedd");
		return collection;
	}
}
//Launch MongoDB service b4 executing this- mongod.exe --dbpath "(data_path)"
//verify using mongo.exe client shell