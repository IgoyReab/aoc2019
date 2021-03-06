package aoc.day02;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("1","9","10","3","2","3","11","0","99","30","40","50");
        result = new Day02().part1(input);
        assertEquals("3500", result);

        input = Arrays.asList("1","0","0","0","99");
        result = new Day02().part1(input);
        assertEquals("2", result);

        input = Arrays.asList("2","3","0","3","99");
        result = new Day02().part1(input);
        assertEquals("2", result);

        input = Arrays.asList("2","4","4","5","99","0");
        result = new Day02().part1(input);
        assertEquals("2", result);

        input = Arrays.asList("1","1","1","4","99","5","6","0","99");
        result = new Day02().part1(input);
        assertEquals("30", result);

        input = Arrays.asList("1","12","2","3","1","1","2","3","1","3","4","3","1","5","0","3","2","13","1","19","1",
                "10","19","23","1","6","23","27","1","5","27","31","1","10","31","35","2","10","35","39","1","39","5",
                "43","2","43","6","47","2","9","47","51","1","51","5","55","1","5","55","59","2","10","59","63","1",
                "5","63","67","1","67","10","71","2","6","71","75","2","6","75","79","1","5","79","83","2","6","83",
                "87","2","13","87","91","1","91","6","95","2","13","95","99","1","99","5","103","2","103","10","107",
                "1","9","107","111","1","111","6","115","1","115","2","119","1","119","10","0","99","2","14","0","0");

        result = new Day02().part1(input);
        assertEquals("5482655", result);



    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","12","2","3","1","1","2","3","1","3","4","3","1","5","0","3","2","13","1","19","1",
                "10","19","23","1","6","23","27","1","5","27","31","1","10","31","35","2","10","35","39","1","39","5",
                "43","2","43","6","47","2","9","47","51","1","51","5","55","1","5","55","59","2","10","59","63","1",
                "5","63","67","1","67","10","71","2","6","71","75","2","6","75","79","1","5","79","83","2","6","83",
                "87","2","13","87","91","1","91","6","95","2","13","95","99","1","99","5","103","2","103","10","107",
                "1","9","107","111","1","111","6","115","1","115","2","119","1","119","10","0","99","2","14","0","0");

        result = new Day02().part2(input);
        assertEquals("4967", result);

    }
}
