package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest
public class TestSpringDemo {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test01(){
        String text=applicationContext!=null?"is ok":"Is not okay";
        System.out.println(text);
    }
}
