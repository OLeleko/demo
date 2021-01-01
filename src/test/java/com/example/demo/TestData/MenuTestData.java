package com.example.demo.TestData;

import com.example.demo.TestMatcher;
import com.example.demo.model.Menu;

import java.time.LocalDate;

import static com.example.demo.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {

    public static final TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Menu.class, "restaurant", "meals", "restaurant_name");
    public static final int MENU_ID = START_SEQ + 8;
    public static LocalDate date = LocalDate.parse("2020-11-13");
    public static LocalDate date2 = LocalDate.parse("2020-11-16");
    public static final Menu menu1 = new Menu(MENU_ID, date2);

    public static Menu getNew() {
        return new Menu(null, date);
    }
}
