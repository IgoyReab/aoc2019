package aoc.day03;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day03Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83");

        result = new Day03().part1(input);
        assertEquals("159", result);

        input = Arrays.asList("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

        result = new Day03().part1(input);
        assertEquals("135", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day03().part2(input);
        assertEquals("", result);

    }
}
