# MongoY
Simple Mongo API

### Java Docs
https://ymirsonmez.github.io/MongoY/

### Latest Features

- 2.4
```JAVA
//Connects localhost
MongoY mongoy = new MongoY("DB");
mongoy.removeData("moneys", "SwadeDev0");
mongoy.getInt("moneys", "SwadeDev0"); #Integer
mongoy.exists("moneys", "SwadeDev0"); #Boolean
```

### Usage

```JAVA
MongoY mongoy = new MongoY("host", 27017, "DB");

mongoy.set("moneys", "Ymir", 5);
mongoy.removeData("moneys", "Ymir");
mongoy.getInt("moneys", "Ymir"); #Integer
mongoy.exists("moneys", "Ymir"); #Boolean
```
```JAVA
mongoy.set("collection", YObject);
mongo.set("collection", key, Object);
```

### V2 Features
```JAVA
new CaseInsensitiveString("text");

MongoY mongoy = new MongoY("host", 27017, "DB");

mongoy.set("moneys", "Ymir", 5);
mongoy.removeData("moneys", new CaseInsensitiveString("ymir"));
mongoy.getInt("moneys", new CaseInsensitiveString("yMiR")); #Integer
mongoy.exists("moneys", new CaseInsensitiveString("YMIR")); #Boolean
```

### Maven
```XML
<dependency>
    <groupId>me.ymir.mongoy</groupId>
    <artifactId>mongoy</artifactId>
    <version>2.4</version>
</dependency>
```
