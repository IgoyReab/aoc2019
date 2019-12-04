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

    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("111122-111123");
        result = new Day04().part2(input);
        assertEquals("1", result);

        input = Arrays.asList("111123-111124");
        result = new Day04().part2(input);
        assertEquals("0", result);

        input = Arrays.asList("112233-112234");
        result = new Day04().part2(input);
        assertEquals("2", result);

        input = Arrays.asList("111110-111111");
        result = new Day04().part2(input);
        assertEquals("0", result);

        input = Arrays.asList("223450-223451");
        result = new Day04().part2(input);
        assertEquals("0", result);

        input = Arrays.asList("123789-123790");
        result = new Day04().part2(input);
        assertEquals("0", result);

    }
}
