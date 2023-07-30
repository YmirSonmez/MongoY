package me.ymir.mongoy;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

@SuppressWarnings("unused")
public class MongoY extends YDataBase {
    private final MongoClient mongo;

    public MongoY(String host, int port, String dbName) {
        this.mongo = new MongoClient(host, port);
        init(mongo.getDatabase(dbName));
    }

    public MongoY(MongoClientURI uri, String dbName) {
        this.mongo = new MongoClient(uri);
        init(mongo.getDatabase(dbName));
    }

    //--------------------------------------

    public MongoCollection<Document> getCollection(String collection) {
        return this.database.getCollection(collection);
    }

    public YDataBase getAnotherDatabase(String dataBase) {
        YDataBase db = new YDataBase();
        db.init(mongo.getDatabase(dataBase));
        return db;
    }

    public MongoClient getMongoClient() {
        return this.mongo;
    }
}
