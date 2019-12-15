package aoc.day14;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("10 ORE => 10 A",
        "1 ORE => 1 B",
        "7 A, 1 B => 1 C",
        "7 A, 1 C => 1 D",
        "7 A, 1 D => 1 E",
        "7 A, 1 E => 1 FUEL");

        result = new Day14().part1(input);
        assertEquals("31", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day14().part2(input);
        assertEquals("", result);

    }
}
