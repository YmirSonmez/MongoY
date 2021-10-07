package me.ymir.mongoy;

public class Test {

    public static void main(String[] args) {
        MongoY mongoY = new MongoY("localhost",27017,"test");
        //mongoY.set("t","kEy", 5);
        mongoY.set("t",new CaseInsensitiveString("KEy"),7);

        //mongoY.removeData("t",new CaseInsensitiveString("key"));
        System.out.println(mongoY.getDocument("t","key",new CaseInsensitiveString("key")));

    }
}
