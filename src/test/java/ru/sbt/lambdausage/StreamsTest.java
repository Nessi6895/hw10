package ru.sbt.lambdausage;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Артем on 22.08.2016.
 */
public class StreamsTest {
    @Test
    public void testFilter() {
        List<String> list = Arrays.asList("a1", "a1", "b3", "c4");
        Streams<String> expected = Streams.of(Arrays.asList("a1", "a1"));
        assertTrue(expected.equals(Streams.of(list).filter(p -> p.contains("a"))));
    }

    @Test
    public void testTransform() {
        List<String> list = Arrays.asList("1", "2", "3");
        Streams<Integer> expected = Streams.of(Arrays.asList(4, 5, 6));
        assertTrue(expected.equals(Streams.of(list).transform(p -> Integer.parseInt(p) + 3)));
    }

    @Test
    public void testToMap() {
        List<String> list = Arrays.asList("a1", "b2", "c4");
        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 1);
        expected.put("b", 2);
        expected.put("c", 4);
        assertTrue(expected.equals(Streams.of(list).toMap(p -> p.substring(0, 1), p -> Integer.parseInt(p.substring(1)))));
    }
}
