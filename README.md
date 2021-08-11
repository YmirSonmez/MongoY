# MongoY
Projelerimde kolaylık sağlaması için yapmıştım umarım işinize yarar :)

### ----

```
MongoY mongoy = new MongoY("host",27017,"DB");

mongoy.set("moneys","Ymir",5);
mongoy.removeData("moneys","Ymir");
mongoy.getInt("moneys","Ymir"); #Integer
mongoy.exists("moneys","Ymir"); #Boolean
```
```
mongoy.set("collection",YObject);
```
