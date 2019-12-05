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

    private String[] runProgramPart1(String[] inputProgram){
        int count = 0;
        int oppCode = 0;
        int parameter1;
        int parameter2;
        int parameter3;
        boolean positionMode1 = true;
        boolean positionMode2 = true;

        List<String> output = new ArrayList<>();

        while (oppCode != 99) {
            switch (inputProgram[count].length()) {
                case 1:
                case 2:    {
                    oppCode = Integer.parseInt(inputProgram[count]);
                    positionMode2 = true;
                    positionMode1 = true;
                    break;
                }
                case 3 : {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(2)));
                    positionMode2 = true;
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    break;
                }
                case 4: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(3)));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    break;
                }
                case 5: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(4)));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(2))));
                    break;
                }
            }

            if (oppCode == 9) oppCode = 99;

            switch (oppCode){
                case 1 :
                case 2 : {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    parameter2 = Integer.parseInt(inputProgram[count + 2]);
                    parameter3 = Integer.parseInt(inputProgram[count + 3]);
                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);
                    inputProgram[parameter3] = (oppCode == 1) ? String.valueOf(parameter1 + parameter2) : String.valueOf(parameter1 * parameter2);
                    count += 4;
                    break;
                }
                case 3: {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    inputProgram[parameter1] = String.valueOf(getInput(parameter1));
                    count += 2;
                    break;
                }
                case 4: {
                    parameter1 = (positionMode1) ?
                            Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[count + 1])]) :
                            Integer.parseInt(inputProgram[count + 1]);
                    output.add(String.valueOf(parameter1));
                    count += 2;
                    break;
                }
            }
        }
        return output.toArray(new String[0]);
    }

    private String[] runProgramPart2(String[] inputProgram){
        int count = 0;
        int oppCode = 0;
        int parameter1;
        int parameter2;
        int parameter3;
        boolean positionMode1 = true;
        boolean positionMode2 = true;

        List<String> output = new ArrayList<>();

        while (oppCode != 99) {
            switch (inputProgram[count].length()) {
                case 1:
                case 2:    {
                    oppCode = Integer.parseInt(inputProgram[count]);
                    positionMode2 = true;
                    positionMode1 = true;
                    break;
                }
                case 3 : {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(2)));
                    positionMode2 = true;
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    break;
                }
                case 4: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(3)));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    break;
                }
                case 5: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(4)));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(2))));
                    break;
                }
            }

            if (oppCode == 9) oppCode = 99;

            switch (oppCode){
                case 1 :
                case 2 : {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    parameter2 = Integer.parseInt(inputProgram[count + 2]);
                    parameter3 = Integer.parseInt(inputProgram[count + 3]);
                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                    inputProgram[parameter3] = (oppCode == 1) ? String.valueOf(parameter1 + parameter2) : String.valueOf(parameter1 * parameter2);
                    count += 4;
                    break;
                }
                case 3: {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    inputProgram[parameter1] = String.valueOf(getInput(parameter1));
                    count += 2;
                    break;
                }
                case 4: {
                    parameter1 = (positionMode1) ?
                            Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[count + 1])]) :
                            Integer.parseInt(inputProgram[count + 1]);
                    output.add(String.valueOf(parameter1));
                    count += 2;
                    break;
                }
                case 5:
                case 6: {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    parameter2 = Integer.parseInt(inputProgram[count + 2]);

                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                    count = (((oppCode == 5) && (parameter1 != 0)) || ((oppCode == 6 ) && (parameter1 == 0))) ? parameter2 : count + 3;
                    break;
                }
                case 7:
                case 8: {
                    parameter1 =  Integer.parseInt(inputProgram[count + 1]);
                    parameter2 =  Integer.parseInt(inputProgram[count + 2]);
                    parameter3 =  Integer.parseInt(inputProgram[count + 3]);

                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                    boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                    inputProgram[parameter3] = (conditionMet) ? String.valueOf(1) : String.valueOf(0);

                    count += 4;
                    break;
                }
            }
        }
        return output.toArray(new String[0]);
    }


    @Override
    public String part1(List<String> input) {
        String[] output = runProgramPart1(input.toArray(new String[0]));
        return input.isEmpty() ? "" : Arrays.toString(output);
    }

    @Override
    public String part2(List<String> input) {
        String[] output = runProgramPart2(input.toArray(new String[0]));
        return input.isEmpty() ? "" : Arrays.toString(output);
    }
}
