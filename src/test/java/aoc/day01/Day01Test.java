package aoc.day01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("-1","2","8","-3");
        result = new Day01().part1(input);
        assertEquals("6", result);

        input = Arrays.asList("+1","-2","+3","+1");
        result = new Day01().part1(input);
        assertEquals("3", result);

        input = Arrays.asList("+1","+1","+1");
        result = new Day01().part1(input);
        assertEquals("3", result);

        input = Arrays.asList("+1","+1","-2");
        result = new Day01().part1(input);
        assertEquals("0", result);

        input = Arrays.asList("-1","-2","-3");
        result = new Day01().part1(input);
        assertEquals("-6", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

//                +1, -1 first reaches 0 twice.
//                +3, +3, +4, -2, -4 first reaches 10 twice.
//                -6, +3, +8, +5, -6 first reaches 5 twice.
//                +7, +7, -2, -7, -4 first reaches 14 twice.

        input = Arrays.asList("+1","-2","+3","+1");
        result = new Day01().part2(input);
        assertEquals("2", result);

        input = Arrays.asList("+1","-1");
        result = new Day01().part2(input);
        assertEquals("0", result);

        input = Arrays.asList("+3","+3","+4","-2","-4");
        result = new Day01().part2(input);
        assertEquals("10", result);

        input = Arrays.asList("-6","+3","+8","+5","-6");
        result = new Day01().part2(input);
        assertEquals("5", result);

        input = Arrays.asList("+7","+7","-2","-7","-4");
        result = new Day01().part2(input);
        assertEquals("14", result);

    }
}
