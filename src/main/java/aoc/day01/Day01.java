package aoc.day01;

import aoc.Day;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 implements Day {

    static long resultingFrequency(Integer[] input, long n, int i) {               //recursive function to add all elements together
        if (i >= input.length) return n;                    // stop if last element has been added
        return input[i] + resultingFrequency(input, n, i + 1);  //recursive call to this function with the next element
    }

    static long firstRecuringFrequency(Integer[] input) {

        Set<Long> foundFrequencies = new HashSet<Long>();         // set of Longs, if I add an element that already exists it returns false

        long sum = 0;
        boolean found = false;

        foundFrequencies.add((long) 0);

        do {
            for (int i = 0; i < input.length; i++) {     // loop through the array
                sum = sum + input[i];
                found = !(foundFrequencies.add(sum));        // found becomes true if  an element that already exist is added, if that happens the recuring Frequency has been found
                if (found) break;
            }
        } while (!found);                                     // try agian if not found

        return sum;                                           // return the found value (the one that was double!
    }

    @Override
    public String part1(List<String> input) {
        Integer[] freqShifts = new Integer[input.size()];
        int count = 0;

        for (String s: input) {
            s =s.replace("+","");
            freqShifts[count] = Integer.parseInt(s);
            count ++;
        }
        return input.isEmpty() ? "" : String.valueOf(resultingFrequency(freqShifts, 0 ,0));
    }

    @Override
    public String part2(List<String> input) {
        Integer[] freqShifts = new Integer[input.size()];
        int count = 0;

        for (String s: input) {
            s =s.replace("+","");
            freqShifts[count] = Integer.parseInt(s);
            count ++;
        }

        return input.isEmpty() ? "" : String.valueOf(firstRecuringFrequency(freqShifts));
    }

}
