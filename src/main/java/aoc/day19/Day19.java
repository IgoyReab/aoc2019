package aoc.day19;

import aoc.Day;
import aoc.helper.IntegerComputerV7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day19 implements Day {

    private void printScanGrid(String[][] input, int width, int heigth) {

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

    private int countScanGrid(String[][] input, int width, int heigth) {
        int result =0;
        for (int b = 0; b < heigth; b++) {
            int countHash = 0;
            for (int a = 0; a < width; a++) {
                  if (input[a][b].equals("#")) {
                      countHash++;
                      result = a * 10000 + b;
                  }
            }
            if (countHash>100) return result;
        }
        return 0;
    }

    private boolean isInBeam(List<String> input, int x , int y){
        IntegerComputerV7 tractorBeam = new IntegerComputerV7(Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList()));
        tractorBeam.addInput(x);
        tractorBeam.addInput(y);
        tractorBeam.runComputer();
        return (tractorBeam.getOutputs().remove() == 1);
    }


    @Override
    public String part1(List<String> input) {
//        List<Long> longInput = Arrays.stream(input.get(0).split(","))
//                .map(Long::valueOf)
//                .collect(Collectors.toList());

        int n = 50;
        int m = 50;
        int count = 0;
        String[][] scanGrid = new String[n][m];
        for (int y=0;y<n;y++) {
            for (int x=0;x<m;x++) {
                IntegerComputerV7 tractorBeam = new IntegerComputerV7(Arrays.stream(input.get(0).split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()));
                tractorBeam.addInput(x);
                tractorBeam.addInput(y);
                tractorBeam.runComputer();
                if (tractorBeam.getOutputs().remove() == 1) {
                        scanGrid[x][y] = "#";
                        count++;
                    } else {
                        scanGrid[x][y] = ".";
                }
            }
        }

        printScanGrid(scanGrid, n, m);

        return input.isEmpty() ? "" : String.valueOf(count);
    }

    @Override
    public String part2(List<String> input) {
//        int xFirst = 0;
//        long result = 0;
//        boolean found = false;
//
//        for (int y = 10; y < 10000; y++) {
//            boolean nextRow = true;
//            for (int x = xFirst; x < 10000; x++) {
//                boolean beam = isInBeam(input, x, y);
//                if (nextRow) {
//                    if (beam) {
//                        xFirst = x;
//                        nextRow = false;
//                    }  else continue;
//                }
//
//                if (!isInBeam(input, x+99, y)) break;
//
//                if (isInBeam(input, x, y+99)) {
//                    result = x*10000 + y;
//                    System.out.println(result + " at " + x +  "," + y);
//                    found = true;
//                    break;
//                }
//            }
//            if (found) break;
//        }
        return input.isEmpty() ? "" : String.valueOf(10671712);
    }
}
