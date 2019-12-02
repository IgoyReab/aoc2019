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

        input = Arrays.asList("12","14","1969","100756");
        result = new Day01().part1(input);
        assertEquals("34241", result);


    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("14","1969","100756");
        result = new Day01().part2(input);
        assertEquals("51314", result);
    }
}
