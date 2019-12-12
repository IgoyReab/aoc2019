package aoc.day12;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day12Test {

    @Test
    public void testPart1_1(){
        List<String> input;
        String result;

        Day12 day12 = new Day12();

        input = Arrays.asList("<x=-1, y=0, z=2>","<x=2, y=-10, z=-7>","<x=4, y=-8, z=8>","<x=3, y=5, z=-1>");

        day12.iterations = 10;
        result = day12.part1(input);
        assertEquals("179", result);
    }

    @Test
    public void testPart1_2(){
        List<String> input;
        String result;

        Day12 day12 = new Day12();

        input = Arrays.asList("<x=-8, y=-10, z=0>","<x=5, y=5, z=10>","<x=2, y=-7, z=3>","<x=9, y=-8, z=-3>");

        day12.iterations = 100;
        result = day12.part1(input);
        assertEquals("1940", result);
    }


    @Test
    public void testPart2_1(){
        List<String> input;
        String result;

        Day12 day12 = new Day12();

        input = Arrays.asList("<x=-1, y=0, z=2>","<x=2, y=-10, z=-7>","<x=4, y=-8, z=8>","<x=3, y=5, z=-1>");

        result = day12.part2(input);
        assertEquals("2772", result);

    }

    @Test
    public void testPart2_2(){
        List<String> input;
        String result;

        Day12 day12 = new Day12();

        input = Arrays.asList("<x=-8, y=-10, z=0>","<x=5, y=5, z=10>","<x=2, y=-7, z=3>","<x=9, y=-8, z=-3>");

        result = day12.part2(input);
        assertEquals("4686774924", result);

    }
}
