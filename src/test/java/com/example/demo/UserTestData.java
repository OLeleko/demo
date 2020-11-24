package com.example.demo;

import com.example.demo.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final String sDate1 = "2020-05-02";
    public static  Date date = new Date();
    static {
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static final User user1 = new User(USER_ID, "user1", "12345", date, true, false);
    public static User getNew() {
        return new User(null, "New1", "12345", new Date(), true, false);
    }

}
