package com.dql.product.day31;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LambdaTest {

    @Test
    public  void kk() {
        Function<String,String > uppCase =String::toUpperCase;
        Function<String,String > duplicate= s -> s.concat(s);
        assertThat(uppCase.andThen(duplicate).apply("test"),is("TESTTEST"));

        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt();
        System.out.println(random.get());

        BinaryOperator<Integer> add =Integer::sum;
        BinaryOperator<Integer> substract = (a,b) ->b-a;
        assertThat(substract.apply(add.apply(1,2),3),is(0));

        Consumer<String> consumer =System.out::println;
        consumer.andThen(consumer).accept("qwer");

        Predicate<Integer> p = i ->i>0;
        Predicate<Integer> even = i -> i %2==0;
        assertTrue(p.and(even).test(2));
    }



    private static double calc(List<Integer> ints) {
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量，纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        //注意count可能为0的可能
        return count >0 ? total / count : 0;
    }


    @Test
    public  void test() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        double average = calc(ints);
        double streamResult = ints.stream()
                .map(i -> new Point2D.Double((double) i % 3, (double) i / 3))
                .filter(point -> point.getY() > 1)
                .mapToDouble(point -> point.distance(0, 0))
                .average()
                .orElse(0);
        //如何用一行代码来实现，比较一下可读性
        assertThat(average, is(streamResult));


    }

    @Test(expected = IllegalArgumentException.class)
    public void optional() {
        assertThat(Optional.of(1).get(),is(1));
        assertThat(Optional.ofNullable(null).orElse("A"),is("A"));
        assertFalse(OptionalDouble.empty().isPresent());
        assertThat(Optional.of(1).map(Math::incrementExact).get(),is(2));
        assertThat(Optional.of(1).filter(i->i%2==0).orElse(null),is(nullValue()));
        Optional.empty().orElseThrow(IllegalArgumentException::new);
    }


}
