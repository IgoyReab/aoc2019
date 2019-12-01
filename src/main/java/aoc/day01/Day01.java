package aoc.day01;

import aoc.Day;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;

public class Day01 implements Day {

    private int calculatePart1(String s) {
        long i = Long.parseLong(s);
        return (((int) (i / 3 )) - 2);
    }

    private int calculatePart2(String s){
        long i = Long.parseLong(s);

        int fuel = (((int) (Long.parseLong(s) / 3 )) - 2);
        int result = 0;

        while (fuel > 0) {
            result = result + fuel;
            fuel = (((int) ((long) fuel / 3 )) - 2);
        }

        return result;
    }

    @Override
    public String part1(List<String> input) {
        LongAdder sum = new LongAdder();
        input.parallelStream().map(this::calculatePart1).forEach(sum::add);
        return input.isEmpty() ? "" : sum.toString();
    }

    @Override
    public String part2(List<String> input) {
        LongAdder sum = new LongAdder();
        input.parallelStream().map(this::calculatePart2).forEach(sum::add);
        return input.isEmpty() ? "" : sum.toString();
    }
}
