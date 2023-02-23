package com.example.hongpark.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChefTest {

    @Autowired
    Chef chef;
    @Test
    void 돈가스_요리하기(){

        String menu = "돈가스";
        String food = chef.cook(menu);
        String expected = "한돈 등심 돈가스";
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기(){


        String menu = "스테이크";
        String food = chef.cook(menu);
        String expected ="한우 꽃등심 스테이크";
        assertEquals(expected, food);
        System.out.println(food);
    }
    @Test
    void 크리스피치킨_요리하기(){

        String menu = "치킨";
        String food = chef.cook(menu);
        String expected ="크리스피 치킨";
        assertEquals(expected, food);
        System.out.println(food);
    }

}