package me.ymir.mongoy;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        MongoY mongoY = new MongoY("localhost",27017,"test");
        mongoY.set("t","key", Arrays.asList("Deniz","Ali"));

        System.out.println(mongoY.getDocument("t","value",new CaseInsensitiveString("ali")));

    }
}
