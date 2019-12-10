package aoc.day10;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day10Test {

    @Test
    public void testPart1_1(){
        List<String> input;
        String result;

        input = Arrays.asList(".#..#",".....","#####","....#","...##");

        result = new Day10().part1(input);
        assertEquals("8", result);
    }

    @Test
    public void testPart1_2(){
        List<String> input;
        String result;
        input = Arrays.asList("......#.#.", "#..#.#....", "..#######.", ".#.#.###..", ".#..#.....", "..#....#.#", "#..#....#.", ".##.#..###", "##...#..#.", ".#....####");

        result = new Day10().part1(input);
        assertEquals("33", result);
    }

    @Test
    public void testPart1_3(){
        List<String> input;
        String result;

        input = Arrays.asList(".#..#..###", "####.###.#", "....###.#.", "..###.##.#", "##.##.#.#.", "....###..#", "..#.#..#.#", "#..#.#.###", ".##...##.#", ".....#.#..");

        result = new Day10().part1(input);
        assertEquals("35", result);
    }

    @Test
    public void testPart1_4(){
        List<String> input;
        String result;

        input = Arrays.asList(".#..##.###...#######", "##.############..##.", ".#.######.########.#", ".###.#######.####.#.",
                "#####.##.#.##.###.##", "..#####..#.#########", "####################", "#.####....###.#.#.##",
                "##.#################", "#####.##.###..####..", "..######..##.#######", "####.##.####...##..#",
                ".#####..#.######.###", "##...#.##########...", "#.##########.#######", ".####.#.###.###.#.##", "....##.##.###..#####",
                ".#.#.###########.###", "#.#.#.#####.####.###", "###.##.####.##.#..##");

        result = new Day10().part1(input);
        assertEquals("210", result);
    }
    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day10().part2(input);
        assertEquals("", result);

    }
}
