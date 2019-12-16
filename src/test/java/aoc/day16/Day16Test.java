package aoc.day16;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    @Test
    public void testPart1_1(){
        List<String> input;
        String result;

        input = Arrays.asList("12345678");

        result = new Day16().part1(input);
        assertEquals("[2, 3, 8, 4, 5, 6, 7, 8]", result);
    }

    @Test
    public void testPart1_2(){
        List<String> input;
        String result;

        input = Arrays.asList("80871224585914546619083218645595");

        result = new Day16().part1(input);
        assertEquals("[2, 4, 1, 7, 6, 1, 7, 6, 4, 8, 0, 9, 1, 9, 0, 4, 6, 1, 1, 4, 0, 3, 8, 7, 6, 3, 1, 9, 5, 5, 9, 5]", result);
    }

    @Test
    public void testPart1_3(){
        List<String> input;
        String result;

        input = Arrays.asList("19617804207202209144916044189917");

        result = new Day16().part1(input);
        assertEquals("[7, 3, 7, 4, 5, 4, 1, 8, 5, 5, 7, 2, 5, 7, 2, 5, 9, 1, 4, 9, 4, 6, 6, 5, 9, 9, 6, 3, 9, 9, 1, 7]", result);
    }

    @Test
    public void testPart1_4(){
        List<String> input;
        String result;

        input = Arrays.asList("69317163492948606335995924319873");

        result = new Day16().part1(input);
        assertEquals("[5, 2, 4, 3, 2, 1, 3, 3, 2, 9, 2, 9, 9, 8, 6, 0, 6, 8, 8, 0, 4, 9, 5, 9, 7, 4, 8, 6, 9, 8, 7, 3]", result);
    }


    @Test
    public void testPart2_1(){
        List<String> input;
        String result;

        input = Arrays.asList("03036732577212944063491565474664");

        result = new Day16().part2(input);
        assertEquals("84462026", result);

    }

    @Test
    public void testPart2_2(){
        List<String> input;
        String result;

        input = Arrays.asList("02935109699940807407585447034323");

        result = new Day16().part2(input);
 //       assertEquals("78725270", result);
        assertEquals("78725270", result);

    }

    @Test
    public void testPart2_3(){
        List<String> input;
        String result;

        input = Arrays.asList("03081770884921959731165446850517");

        result = new Day16().part2(input);
        assertEquals("53553731", result);
      //  assertEquals("53553731", result);

    }
}
