package aoc.day06;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day06Test {

    @Test
    public void testPart1() {
        List<String> input;
        String result;
        input = Arrays.asList("B)C", "C)D", "COM)B", "E)F", "B)G", "D)E", "G)H", "D)I", "D)E", "E)J", "J)K", "K)L");

        result = new Day06().part1(input);
        assertEquals("42", result);
    }

    @Test
    public void testPart2() {
        List<String> input;
        String result;

        input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN");

        result = new Day06().part2(input);
        assertEquals("4", result);

    }
}
