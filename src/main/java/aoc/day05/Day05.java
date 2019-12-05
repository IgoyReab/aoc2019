package aoc.day05;

import aoc.Day;

import java.util.List;
import java.util.Scanner;

public class Day05 implements Day {
    private int getInput(int putRegister) {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter your input to put at " + (putRegister + 1) + " : ");

      return scanner.nextInt();
    }

    private void printOutput(int outputValue, int fromRegister){
        if (fromRegister == 0){
            System.out.println("The immediate value is : " + (outputValue));
        } else {
            System.out.println("The value at register " + (fromRegister + 1) + " is : " + (outputValue));
        }
    }

    private String[] runProgram(String[] inputProgram){
        int commandoCount = 1;
        int count = 0;
        int oppCode = 0;
        int parameter1;
        int parameter2;
        int parameter3;
        boolean positionMode1 = true;
        boolean positionMode2 = true;
//        boolean positionMode3 = true;

        while (oppCode != 99) {
            System.out.println("Command : " + commandoCount);
            switch (inputProgram[count].length()) {
                case 1:
                case 2:    {
                    oppCode = Integer.parseInt(inputProgram[count]);
//                    positionMode3 = true;
                    positionMode2 = true;
                    positionMode1 = true;
                    break;
                }
                case 3 : {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(2)));
//                    positionMode3 = true;
                    positionMode2 = true;
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    break;
                }
                case 4: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(3)));
//                    positionMode3 = true;
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    break;
                }
                case '5': {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(4)));
//                    positionMode3 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(2))));
                    break;
                }
            }

            if (oppCode == 9) oppCode = 99;

            switch (oppCode){
                case 1 :
                {
                    parameter1 =  Integer.parseInt(inputProgram[count + 1]);
                    parameter2 =  Integer.parseInt(inputProgram[count + 2]);
                    parameter3 =  Integer.parseInt(inputProgram[count + 3]);
                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);
                    inputProgram[parameter3] = String.valueOf(parameter1 + parameter2);
                    count += 4;
                    break;
                }
                case 2: {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    parameter2 = Integer.parseInt(inputProgram[count + 2]);
                    parameter3 = Integer.parseInt(inputProgram[count + 3]);
                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);
                    inputProgram[parameter3] = String.valueOf(parameter1 * parameter2);
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
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    int register = 0;
                    if (positionMode1) {
                        register = parameter1;
                        parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    }
                    printOutput(parameter1, register);
                    count += 2;

                }
            }
            commandoCount += 1;
        }
        return inputProgram;
    }


    @Override
    public String part1(List<String> input) {
        String[] strings = runProgram(input.toArray(new String[0]));
        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
