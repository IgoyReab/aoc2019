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

//        A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0,
//        which would call for a negative fuel), so the total fuel required is still just 2.
//        At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216
//        then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel.
//        So, the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
//        The fuel required by a module of mass 100756 and its fuel is:
//        33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.

        input = Arrays.asList("14");
        result = new Day01().part2(input);
        assertEquals("2", result);

        input = Arrays.asList("1969");
        result = new Day01().part2(input);
        assertEquals("966", result);

        input = Arrays.asList("100756");
        result = new Day01().part2(input);
        assertEquals("50346", result);
    }
}
