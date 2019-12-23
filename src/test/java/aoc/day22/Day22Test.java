package aoc.day22;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day22Test {


//    @Test
//    public void testPart1_1(){
//        List<String> input;
//        String result;
//
//        input = Arrays.asList("deal with increment 7",
//                              "deal into new stack",
//                              "deal into new stack");
//
//        result = new Day22().part1(input);
//        assertEquals("[0, 3, 6, 9, 2, 5, 8, 1, 4, 7]", result);
//    }
//
//    @Test
//    public void testPart1_2(){
//        List<String> input;
//        String result;
//
//        input = Arrays.asList("cut 6",
//                              "deal with increment 7",
//                              "deal into new stack");
//
//        result = new Day22().part1(input);
//        assertEquals("[3, 0, 7, 4, 1, 8, 5, 2, 9, 6]", result);
//    }
//
//    @Test
//    public void testPart1_3(){
//        List<String> input;
//        String result;
//
//        input = Arrays.asList(  "deal into new stack",
//                                "cut -2",
//                                "deal with increment 7",
//                                "cut 8",
//                                "cut -4",
//                                "deal with increment 7",
//                                "cut 3",
//                                "deal with increment 9",
//                                "deal with increment 3",
//                                "cut -1");
//
//        result = new Day22().part1(input);
//        assertEquals("[9, 2, 5, 8, 1, 4, 7, 0, 3, 6]", result);
//    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day22().part2(input);
        assertEquals("", result);

    }
}
