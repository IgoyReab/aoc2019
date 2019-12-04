package aoc.day04;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day04Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("111110-111111");

        result = new Day04().part1(input);
        assertEquals("1", result);

        input = Arrays.asList("223450-223451");

        result = new Day04().part1(input);
        assertEquals("0", result);

        input = Arrays.asList("123789-123790");

        result = new Day04().part1(input);
        assertEquals("0", result);

//        input = Arrays.asList("256310-732736");
//
//        result = new Day04().part1(input);
//        assertEquals("", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("256310-732736");

        result = new Day04().part2(input);
        assertEquals("", result);

    }
}
