package aoc.day05;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day05 implements Day {
    private int getInput(int putRegister) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your input to put at " + (putRegister + 1) + " : ");
        return scanner.nextInt();
    }

    private Integer[] runProgram(Integer[] inputProgram){
        int count = 0;
        int oppCode = 0;
        int parameter1;
        int parameter2;
        int parameter3;

        List<Integer> output = new ArrayList<>();

        while (oppCode != 99) {
            oppCode = inputProgram[count] % 10;
            oppCode = (oppCode == 9) ? 99 : oppCode;
            boolean positionMode1 = (0 == ( inputProgram[count] / 100) % 10);
            boolean positionMode2 = (0 == ( inputProgram[count] / 1000) % 10);

            switch (oppCode){
                case 1 :
                case 2 : {
                    parameter1 = (positionMode1) ?  inputProgram[inputProgram[count + 1]] : inputProgram[count + 1];
                    parameter2 = (positionMode2) ?  inputProgram[inputProgram[count + 2]] : inputProgram[count + 2];
                    parameter3 = inputProgram[count + 3];
                    inputProgram[parameter3] = (oppCode == 1) ? parameter1 + parameter2  : parameter1 * parameter2;
                    count += 4;
                    break;
                }
                case 3: {
                    parameter1 = inputProgram[count + 1];
                    inputProgram[parameter1] = getInput(parameter1);
                    count += 2;
                    break;
                }
                case 4: {
                    parameter1 = (positionMode1) ? inputProgram[inputProgram[count + 1]] : inputProgram[count + 1];
                    output.add(parameter1);
                    count += 2;
                    break;
                }
                case 5:
                case 6: {
                    parameter1 = (positionMode1) ? inputProgram[inputProgram[count + 1]] : inputProgram[count + 1];
                    parameter2 = (positionMode2) ? inputProgram[inputProgram[count + 2]] : inputProgram[count + 2];
                    count = (((oppCode == 5) && (parameter1 != 0)) || ((oppCode == 6 ) && (parameter1 == 0))) ? parameter2 : count + 3;
                    break;
                }
                case 7:
                case 8: {
                    parameter1 = (positionMode1) ? inputProgram[inputProgram[count + 1]] : inputProgram[count + 1];
                    parameter2 = (positionMode2) ? inputProgram[inputProgram[count + 2]] : inputProgram[count + 2];
                    parameter3 =  inputProgram[count + 3];
                    boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                    inputProgram[parameter3] = (conditionMet) ? 1 : 0;
                    count += 4;
                    break;
                }
            }
        }
        return output.toArray(new Integer[0]);
    }


    @Override
    public String part1(List<String> input) {
        return input.isEmpty() ? "" : Arrays.toString(runProgram(input.stream().
                map(Integer::parseInt).toArray(Integer[]::new)));
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : Arrays.toString(runProgram(input.stream().
                map(Integer::parseInt).toArray(Integer[]::new)));
    }
}
