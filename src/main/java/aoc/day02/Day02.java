package aoc.day02;

import aoc.Day;

import java.util.List;

public class Day02 implements Day {

    private String[] runProgram(String[] inputProgram){
        for (int i = 0; i < inputProgram.length; i += 4) {
            int oppCode = Integer.parseInt(inputProgram[i]);
            if (oppCode == 99) break;
            int reg1 = Integer.parseInt(inputProgram[i + 1]);
            int reg2 = Integer.parseInt(inputProgram[i + 2]);
            int dest = Integer.parseInt(inputProgram[i + 3]);

            if (oppCode == 1) {
                inputProgram[dest] = String.valueOf(Integer.parseInt(inputProgram[reg1]) +
                        Integer.parseInt(inputProgram[reg2]));
            } else {
                inputProgram[dest] = String.valueOf(Integer.parseInt(inputProgram[reg1]) *
                        Integer.parseInt(inputProgram[reg2]));
            }
        }
        return inputProgram;
    }

    @Override
    public String part1(List<String> input) {
        return input.isEmpty() ? "" : runProgram(input.toArray(new String[input.size()]))[0];
    }

    @Override
    public String part2(List<String> input) {
        boolean found = false;
        int a;
        int b = 0;

        for (a = 0; a <= 99; a++) {
            for (b = 0; b <= 99; b++) {
                String[] programArray = input.toArray(new String[input.size()]);
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
