/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc;

import aoc.day01.Day01;
import aoc.day02.Day02;
import aoc.day03.Day03;
import aoc.day04.Day04;
import aoc.day05.Day05;
import aoc.day06.Day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class App {

    private static final Map<Integer, Day> DAYS;

    static {
        DAYS = new HashMap<>();
        DAYS.put(1, new Day01());
        DAYS.put(2, new Day02());
        DAYS.put(3, new Day03());
        DAYS.put(4, new Day04());
        DAYS.put(5, new Day05());
        DAYS.put(6, new Day06());
    }

    private static List<String> loadInput(int day){
        String paddedDay = String.valueOf(day);
        if(day < 10) {
            paddedDay = "0" + day;
        }
        String fileName = "day" + paddedDay + ".txt";

        try(BufferedReader r = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(fileName))))){
            return r.lines().collect(toList());
        } catch(IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public static void main(String[] args) {
        LocalTime start = LocalTime.now();

        int day = 1;
        if(args.length != 0){
            day = Integer.parseInt(args[0]);
        }

        int part = 1;
        if(args.length > 1){
            part = Integer.parseInt(args[1]);
        }

        List<String> input = loadInput(day);

        String result;
        if(part == 1) {
            result = DAYS.get(day).part1(input);
        } else {
            result = DAYS.get(day).part2(input);
        }

        LocalTime finish = LocalTime.now();

        System.out.println("Result day " + day + " part " + part + " = " + result);
        System.out.println("Duration (ms): " + Duration.between(start, finish).toMillis());
    }
}
