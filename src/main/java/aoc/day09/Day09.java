package aoc.day09;

import aoc.Day;
import aoc.helper.IntegerComputer;

import java.util.List;
import java.util.stream.Collectors;

public class Day09 implements Day {
    @Override
    public String part1(List<String> input) {
        List<Long> longInput = input.stream().
                map(Long::parseLong).collect(Collectors.toList());

        IntegerComputer ic = new IntegerComputer(longInput);
        ic.setInputParameter(1);
        List<Long> result = ic.runIntegerComputer();

        System.out.println(result);

        return input.isEmpty() ? "" : String.valueOf(result);
    }

    @Override
    public String part2(List<String> input) {List<Long> longInput = input.stream().
            map(Long::parseLong).collect(Collectors.toList());


        IntegerComputer ic = new IntegerComputer(longInput);
        ic.setInputParameter(2);
        List<Long> result = ic.runIntegerComputer();

        System.out.println(result);

        return input.isEmpty() ? "" : String.valueOf(result);
    }
}
