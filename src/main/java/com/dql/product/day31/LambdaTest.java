package com.dql.product.day31;

import java.util.function.Function;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class LambdaTest {

    @Test
    public  void kk() {
        Function<String,String > uppCase =String::toUpperCase;
        Function<String,String > duplicate= s -> s.concat(s);
        assertThat(uppCase.andThen(duplicate).apply("test"),is("TESTTEST"));
    }
}
