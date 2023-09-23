package com.example.demo.services;

import com.example.demo.InvalidValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InvalidClassException;
import java.util.*;


public class IndexServiceImplTest {
    @Test
    void calculate() {
        IndexService indexService = new IndexServiceImpl();
        List calculated = indexService.calculate("aaaBbBbcCccd");
        Assertions.assertEquals(calculated.get(0).toString(), "b=4" );
        Assertions.assertEquals(calculated.get(1).toString(), "c=4" );
        Assertions.assertEquals(calculated.get(2).toString(), "a=3" );
        Assertions.assertEquals(calculated.get(3).toString(), "d=1" );
    }

    @Test
    void calculate_invalid_integer() {

        InvalidValueException ex = Assertions.assertThrows(InvalidValueException.class, () -> {
            IndexService indexService = new IndexServiceImpl();
            indexService.calculate("dsd123");
        });
        Assertions.assertEquals(ex.getMessage(), "input value can't contain integer");

    }

    @Test
    void calculate_invalid_max_chars() {

        InvalidValueException ex = Assertions.assertThrows(InvalidValueException.class, () -> {
            IndexService indexService = new IndexServiceImpl();
            indexService.calculate("https://www.youtube.com/watch?v=dQw4w9WgXcQ https://www.youtube.com/watch?v=dQw4w9WgXcQ https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        });
        Assertions.assertEquals(ex.getMessage(), "input value can't be longer than 100 chars");

    }

    @Test
    void calculate_invalid_min_chars() {

        InvalidValueException ex = Assertions.assertThrows(InvalidValueException.class, () -> {
            IndexService indexService = new IndexServiceImpl();
            indexService.calculate("");
        });
        Assertions.assertEquals(ex.getMessage(), "input value must be not empty");

    }
}
