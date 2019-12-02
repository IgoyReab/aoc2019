package aoc.day02;

import aoc.Day;

import java.util.List;

public class Day02 implements Day {

    private String[] runProgram(String[] inputProgram){
        for (int i = 0; i < inputProgram.length; i += 4) {
            if (Integer.parseInt(inputProgram[i]) == 99) break;

            if (Integer.parseInt(inputProgram[i]) == 1) {
                inputProgram[Integer.parseInt(inputProgram[i + 3])] = String.valueOf(
                        Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[i + 1])]) +
                                Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[i + 2])]));
            } else {
                inputProgram[Integer.parseInt(inputProgram[i + 3])] = String.valueOf(
                        Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[i + 1])]) *
                                Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[i + 2])]));
            }
        }
        return inputProgram;
    }

    @Override
    public String part1(List<String> input) {
        return input.isEmpty() ? "" : runProgram(input.toArray(new String[0]))[0];
    }

    @Override
    public String part2(List<String> input) {
        int a;
        int b = 0;
        boolean found = false;

        for (a = 0; a <= 99; a++) {
            for (b = 0; b <= 99; b++) {
                String[] programArray = input.toArray(new String[0]);
                programArray[1] = String.valueOf(a);
                programArray[2] = String.valueOf(b);
                found = (Integer.parseInt(runProgram(programArray)[0]) == 19690720);
                if (found) break;
            }
            if (found) break;
        }
        return input.isEmpty() ? "" : (String.valueOf(100 * a + b));
    }
}
