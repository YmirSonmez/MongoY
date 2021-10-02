# MongoY
Simple Mongo API

### Java Docs
https://ymirsonmez.github.io/MongoY/

### Usage

```JAVA
MongoY mongoy = new MongoY("host",27017,"DB");

mongoy.set("moneys","Ymir",5);
mongoy.removeData("moneys","Ymir");
mongoy.getInt("moneys","Ymir"); #Integer
mongoy.exists("moneys","Ymir"); #Boolean
```
```JAVA
mongoy.set("collection",YObject);
mongo.set("collection",key,Object);
```

### Maven
```XML
<dependency>
    <groupId>me.ymir.mongoy</groupId>
    <artifactId>mongoy</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
