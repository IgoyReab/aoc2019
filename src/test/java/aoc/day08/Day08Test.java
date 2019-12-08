package aoc.day08;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day08Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("123456789012");
        Day08 day08 = new Day08();

        day08.width = 3;
        day08.tall = 2;

        result = day08.part1(input);
        assertEquals("1", result);

        input = Arrays.asList("123126789012");
        day08.width = 3;
        day08.tall = 2;

        result = day08.part1(input);
        assertEquals("4", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;
        Day08 day08 = new Day08();

        input = Arrays.asList("0222112222120000");

        day08.width = 2;
        day08.tall = 2;

        result = day08.part2(input);

        assertEquals("[[0, 1], [1, 0]]", result);

    }
}
