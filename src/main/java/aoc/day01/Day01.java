package aoc.day01;

import aoc.Day;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class Day01 implements Day {

    @Override
    public String part1(List<String> input) {
        LongAdder sum = new LongAdder();
        input.parallelStream().map(Integer::valueOf).forEach(sum::add);
        return input.isEmpty() ? "" : sum.toString();
    }

    @Override
    public String part2(List<String> input) {
        Set<Long> foundFrequencies = new HashSet<>();         // set of Longs, if I add an element that already exists it returns false
        LongAdder sum = new LongAdder();
        boolean found = false;

        int[] freqShifts = input.stream().mapToInt(Integer::parseInt).toArray();

        do {
            for (int freqShift : freqShifts) {                       // loop through the array
                found = !(foundFrequencies.add(sum.longValue()));    // found becomes true if  an element that already exist is added, if that happens the r
                if (found) break;
                sum.add(freqShift);
            }
        } while (!found);                                            // try again if not found

        return input.isEmpty() ? "" : sum.toString();
    }
}
