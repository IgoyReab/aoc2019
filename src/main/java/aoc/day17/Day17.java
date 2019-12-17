package aoc.day17;

import aoc.Day;
import aoc.helper.IntegerComputerV7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 implements Day {

    private class Cameras {
        IntegerComputerV7 computer;


        public Cameras(List<Long> inputProgram) {
            computer = new IntegerComputerV7(inputProgram);
        }


    }

    private void printScaffolds(String[][] input, int width, int heigth) {

        for (int b = 0; b < heigth; b++) {
            for (int a = 0; a < width; a++) {
                {
                    if (a == 0) System.out.println();
                    System.out.print(input[a][b]);
                }
            }
        }
        System.out.println("\n\n");
    }

    @Override
    public String part1(List<String> input) {
        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        Cameras cameras = new Cameras(longInput);
        cameras.computer.runComputer();


        List<Integer> outputList = new ArrayList(cameras.computer.getOutputs());

        int count = 1;
        for (Integer i : outputList) {
            if (i==10) break;
            count++;
        };

        int xMax = count;
        int yMax = outputList.size() / count;

        String[][] scaffolds = new String[xMax][yMax];

        for (int y = 0; y < yMax; y++) {
            for (int x=0;x<xMax;x++) {
                if (outputList.get(x + (xMax*y)) != 10) {
                    scaffolds[x][y] = String.valueOf(Character.toChars(outputList.get(x + (count*y))));
                } else {
                    scaffolds[x][y] = "|";
                }
            }
        }

        printScaffolds(scaffolds, xMax, yMax);
        int sum=0;
        for (int y = 1; y < yMax-1; y++) {
            for (int x=1;x<xMax-1;x++) {
                if (scaffolds[x][y-1].equals("#") &&
                    scaffolds[x][y+1].equals("#") &&
                    scaffolds[x-1][y].equals("#") &&
                    scaffolds[x+1][y].equals("#") &&
                    scaffolds[x][y].equals("#")) {

                    sum = sum + (x*y);
                }
            }
        }

        System.out.println(sum);

        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        longInput.set(0,2L);
        Cameras cameras = new Cameras(longInput);

        List<Integer> mainRoutine = Arrays.asList(65,44,66,44,66,44,65,44,67,44,65,44,67,44,65,44,67,44,66,10);
        List<Integer> functionA = Arrays.asList(76,44,54,44,82,44,49,50,44,82,44,56,10);
        List<Integer> functionB = Arrays.asList(82,44,56,44,82,44,49,50,44,76,44,49,50,10);
        List<Integer> functionC = Arrays.asList(82,44,49,50,44,76,44,49,50,44,76,44,52,44,76,44,52,10);

        for (Integer i : mainRoutine) {
            cameras.computer.addInput(i);
        }
        for (Integer i : functionA) {
            cameras.computer.addInput(i);
        }
        for (Integer i : functionB) {
            cameras.computer.addInput(i);
        }
        for (Integer i : functionC) {
            cameras.computer.addInput(i);
        }
        cameras.computer.addInput(78);
        cameras.computer.addInput(10);

        cameras.computer.runComputer();

        System.out.println(cameras.computer.getOutputs());


        return input.isEmpty() ? "" : String.valueOf(cameras.computer.getOutputs());
    }
}
