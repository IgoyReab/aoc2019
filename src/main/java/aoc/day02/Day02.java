package aoc.day02;

import aoc.Day;

import java.util.List;

public class Day02 implements Day {

    private Integer[] runProgram(Integer[] inputProgram){
        for (int i = 0; i < inputProgram.length; i += 4) {
            if (inputProgram[i] == 99) break;

            inputProgram[inputProgram[i + 3]] = (inputProgram[i] == 1) ?
                inputProgram[inputProgram[i + 1]] + inputProgram[inputProgram[i + 2]] :
                inputProgram[inputProgram[i + 1]] * inputProgram[inputProgram[i + 2]];

        }
        return inputProgram;
    }

    @Override
    public String part1(List<String> input) {
        return input.isEmpty() ? "" : String.valueOf(runProgram(input.stream().map(Integer::parseInt).toArray(Integer[]::new))[0]);
    }

    @Override
    public String part2(List<String> input) {
        int a;
        int b = 0;
        boolean found = false;

        for (a = 0; a <= 99; a++) {
            for (b = 0; b <= 99; b++) {
                Integer[] programArray = input.stream().map(Integer::parseInt).toArray(Integer[]::new);
                programArray[1] = a;
                programArray[2] = b;
                found = (runProgram(programArray)[0] == 19690720);
                if (found) break;
            }
            if (found) break;
        }
        return input.isEmpty() ? "" : (String.valueOf(100 * a + b));
    }
}
