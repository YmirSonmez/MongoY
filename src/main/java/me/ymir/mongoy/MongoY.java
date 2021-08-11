package me.ymir.mongoy;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class MongoY {
    private final MongoClient mongo;
    private final MongoDatabase database;

    public MongoY(String host,int port,String dbName) {
        this.mongo = new MongoClient(host, port);
        this.database = this.mongo.getDatabase(dbName);
    }

    //--------------------------------------

    public MongoCollection<Document> getCollection(String collection) {
        return this.database.getCollection(collection);
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public MongoClient getMongoClient(){
        return this.mongo;
    }

    //--------------------------------------

    public void set(String collection, YObject object) {
        set(collection,object.getKey(),object);
    }

    public void set(String collection, String key, Object object) {
        Document document;
        if (object instanceof Integer || object instanceof String || object instanceof Double || object instanceof Float || object instanceof Boolean || object instanceof List || object instanceof HashMap) {
            document = new Document().append("key", key).append("value", object);
        } else {
            document = Document.parse(new Gson().toJson(object));
        }
        set(collection, key, document);
    }

    public void set(String collection, String key, Document document) {
        if (exists(collection, "key", key)) {
            DBObject dbObject = new BasicDBObject().append("key", key);
            this.database.getCollection(collection).deleteOne((Bson) dbObject);
        }
        this.database.getCollection(collection).insertOne(document);
    }

    //--------------------------------------

    public void removeData(String collection, String key){
        removeData(collection,"key",key);
    }
    public void removeData(String collection,String keyName, String key) {
        DBObject dbObject = new BasicDBObject().append(keyName, key);
        this.database.getCollection(collection).deleteOne((Bson) dbObject);
    }

    //--------------------------------------

    public boolean exists(String collection, Object key) {
        return exists(collection, "key", key);
    }

    public boolean exists(String collection, String keyName, Object key) {
        DBObject dbObject = new BasicDBObject().append(keyName, key);
        return exists(collection, dbObject);
    }

    public boolean exists(String collection, DBObject object) {
        Document doc = this.database.getCollection(collection).find((Bson) object).first();
        return doc != null;
    }

    //--------------------------------------

    public Integer getInt(String collection, String key, Integer defaultValue) {
        return getInt(collection, "key", key, "value", defaultValue);
    }

    public String getString(String collection, String key, String defaultValue) {
        return getString(collection, "key", key, "value", defaultValue);
    }

    public Boolean getBoolean(String collection, String key, Boolean defaultValue) {
        return getBoolean(collection, "key", key, "value", defaultValue);
    }

    public Double getDouble(String collection, String key, Double defaultValue) {
        return getDouble(collection, "key", key, "value", defaultValue);
    }

    public Float getFloat(String collection, String key, Float defaultValue) {
        return getFloat(collection, "key", key, "value",defaultValue);
    }

    //--------------------------------------

    public Integer getInt(String collection, String keyName,String key, Integer defaultValue) {
        return getInt(collection, keyName, key, "value", defaultValue);
    }

    public String getString(String collection, String keyName, String key, String defaultValue) {
        return getString(collection, keyName, key, "value", defaultValue);
    }

    public Boolean getBoolean(String collection, String keyName, String key, Boolean defaultValue) {
        return getBoolean(collection, keyName, key, "value", defaultValue);
    }

    public Double getDouble(String collection, String keyName, String key, Double defaultValue) {
        return getDouble(collection, keyName, key, "value", defaultValue);
    }

    public Float getFloat(String collection, String keyName, String key, Float defaultValue) {
        return getFloat(collection, keyName, key, "value",defaultValue);
    }

    //--------------------------------------


    public Integer getInt(String collection, String keyName, String key, String value,Integer defaultValue) {
        Document doc = getDocument(collection, keyName, key);
        if (doc != null && doc.get(value) instanceof Integer) {
            return (Integer) doc.get(value);
        }
        return defaultValue;
    }

    public String getString(String collection, String keyName, String key, String value, String defaultValue) {
        Document doc = getDocument(collection, keyName, key);
        if (doc != null && doc.get(value) instanceof String) {
            return (String) doc.get(value);
        }
        return defaultValue;
    }

    public Double getDouble(String collection, String keyName, String key, String value, Double defaultValue) {
        Document doc = getDocument(collection, keyName, key);
        if (doc != null && doc.get(value) instanceof Double) {
            return (Double) doc.get(value);
        }
        return defaultValue;
    }

    public Float getFloat(String collection, String keyName, String key, String value, Float defaultValue) {
        Document doc = getDocument(collection, keyName, key);
        if (doc != null && doc.get(value) instanceof Float) {
            return (Float) doc.get(value);
        }
        return defaultValue;
    }

    public Boolean getBoolean(String collection, String keyName, String key, String value, Boolean defaultValue) {
        Document doc = getDocument(collection, keyName, key);
        if (doc != null && doc.get(value) instanceof Boolean) {
            return (Boolean) doc.get(value);
        }
        return defaultValue;
    }

    //--------------------------------------

    public Object getObject(String collection, String keyName, Object key) {
        if (exists(collection, keyName, key)) {
            DBObject dbObject = new BasicDBObject().append(keyName, key);
            return this.database.getCollection(collection).find((Bson) dbObject).first();
        } else {
            return "";
        }
    }

    public ArrayList<Document> getObjects(String collection, String keyName, Object key){
        if (exists(collection, keyName, key)) {
            DBObject dbObject = new BasicDBObject().append(keyName, key);
            ArrayList<Document> objects = new ArrayList<>();
            for(Document doc : this.database.getCollection(collection).find((Bson) dbObject)){
                objects.add(doc);
            }
            return objects;
        } else {
            return new ArrayList<>();
        }
    }

    public <T> T[] getObjects(String collection, Class<T> classOff,String keyName,Object key){
        ArrayList<Document> objects = getObjects(collection,keyName,key);
        @SuppressWarnings("unchecked")
        T[] objetsClass = (T[]) new Object[objects.size()];
        for (int i =0;i<objects.size();i++){
            objetsClass[i] = new Gson().fromJson(objects.get(i).toJson(),classOff);
        }
        return objetsClass;
    }

    public <T> T getObject(String collection,String key,Class<T> classOff){
        return getObject(collection,"key",key,classOff);
    }

    public <T> T getObject(String collection,String keyName,Object key,Class<T> classOff){
        return new Gson().fromJson(getObjectJson(collection,keyName,key),classOff);
    }

    public Object getObject(String collection, Object key) {
        return getObject(collection, "key", key);
    }

    public String getObjectJson(String collection, Object key) {
        return getObjectJson(collection, "key", key);
    }

    public String getObjectJson(String collection, String keyName, Object key) {
        if (exists(collection, keyName, key)) {
            return getDocument(collection, keyName, key).toJson();
        }
        return "";
    }

    public Document getDocument(String collection, String keyName, Object key) {
        DBObject dbObject = new BasicDBObject().append(keyName, key);
        return this.database.getCollection(collection).find((Bson) dbObject).first();
    }

    //--------------------------------------

    @Deprecated
    public List<String> getStringList(String collection,String keyName,String key,String value){
        return getStringList(collection,keyName,key,value,null);
    }

    @Deprecated
    public List<String> getStringList(String collection, String keyName, String key) {
        return getStringList(collection, keyName, key, "value",null);
    }

    @Deprecated
    public List<String> getStringList(String collection, String key, List<String> defaultList) {
        return getStringList(collection, "key", key, "value",defaultList);
    }

    @Deprecated
    public List<String> getStringList(String collection, String key) {
        return getStringList(collection, "key", key,"value", null);
    }

    @Deprecated
    public List<String> getStringList(String collection, String keyName, String key,String value, List<String> defaultList) {
        if (exists(collection, keyName, key)) {
            Document doc = getDocument(collection, keyName, key);
            return new ArrayList<>(doc.getList(value, String.class));
        } else {
            if (defaultList == null) {
                return new ArrayList<>();
            }
            return defaultList;
        }
    }

    //--------------------------------------

    public <T> List<T> getList(String collection, String keyName, String key,Class<T> classOff) {
        return getList(collection, keyName, key, "value",classOff);
    }

    public <T> List<T> getList(String collection, String key,Class<T> classOff) {
        return getList(collection, "key", key,"value", classOff);
    }

    public <T> List<T> getList(String collection, String keyName, String key,String value,Class<T> classOff) {
        if (exists(collection, keyName, key)) {
            Document doc = getDocument(collection, keyName, key);
            return doc.getList(value, classOff);
        } else {
            return null;
        }
    }
}
