package aoc.day15;

import aoc.Day;
import aoc.helper.DijkstraHelper;
import aoc.helper.IntegerComputerV6;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Day15 implements Day {

    private static final int N = 60;
    private static final int M = 60;

    @Data
    public class SearchDroid {
        private int width;
        private int heigth;
        private Integer x;
        private Integer y;
        private Integer startX;
        private Integer startY;
        private Integer oxygenX;
        private Integer oxygenY;
        private String[][] area;
        private IntegerComputerV6 computer;
        boolean printArea = false;

        public SearchDroid(List<Long> inputProgram, int x, int y, int x2, int y2) {
            this.width = x;
            this.heigth = y;
            this.x = x2;
            this.y = y2;
            this.startX = x2;
            this.startY = y2;
            this.oxygenX = 0;
            this.oxygenY = 0;

            this.computer = new IntegerComputerV6(inputProgram);
            this.area = new String[width][heigth];
            for (int a = 0; a < width; a++) {
                for (int b = 0; b < heigth; b++) {
                    if ((a == 0 || b == 0) || (a == width - 1 || b == width - 1)) {
                        area[a][b] = "#";
                    } else {
                        area[a][b] = "?";
                    }
                }
            }
            area[x2][y2] = ".";
        }

        private void printArea() {
            if (printArea) {

                System.out.println("Count  : " + computer.getCount());
                System.out.println("Input  : " + computer.getInputs());
                System.out.println("Output : " + computer.getOutputs());

                for (int b = 0; b < heigth; b++) {
                    for (int a = 0; a < width; a++) {
                        {
                            if (a == 0) System.out.println();
                            if ((this.x == a) && (this.y == b)) {
                                System.out.print("R");
                            } else {
                                if ((a == startX) && (b == startY)) {
                                    System.out.print("S");

                                } else {
                                    System.out.print(area[a][b]);
                                }
                            }
                        }
                    }
                }
                System.out.println("\n\n");
            }
        }

        private String iterateRobot() {
            Integer input = 0;
            int a = 0;
            int b = 0;
            String result;
            Random rand = new Random();


            boolean ok = false;

            boolean one = false;
            boolean two = false;
            boolean three = false;
            boolean four = false;

            while (!ok) {
                input = rand.nextInt((4 - 1) + 1) + 1;
                switch (input) {
                    case 4: {
                        if (four) continue;
                        if (area[x + 1][y].equals("?")) {

                            a = x + 1;
                            b = y;
                            ok = true;
                        }
                        break;
                    }
                    case 3: {
                        if (three) continue;
                        if (area[x - 1][y].equals("?")) {
                            a = x - 1;
                            b = y;
                            ok = true;
                        }
                        break;
                    }
                    case 2: {
                        if (two) continue;
                        if (area[x][y - 1].equals("?")) {
                            a = x;
                            b = y - 1;
                            ok = true;
                        }
                        break;
                    }
                    case 1: {
                        if (one) continue;
                        if (area[x][y + 1].equals("?")) {
                            a = x;
                            b = y + 1;
                            ok = true;
                        }
                        break;
                    }
                }

                one = (input == 1);
                two = (input == 2);
                three = (input == 3);
                four = (input == 4);

                if (ok) continue;
                switch (input) {
                    case 4: {
                        if (area[x + 1][y].equals(".")) {
                            a = x + 1;
                            b = y;
                            ok = true;
                        }
                        break;
                    }
                    case 3: {
                        if (area[x - 1][y].equals(".")) {
                            a = x - 1;
                            b = y;
                            ok = true;
                        }
                        break;
                    }
                    case 2: {
                        if (area[x][y - 1].equals(".")) {
                            a = x;
                            b = y - 1;
                            ok = true;
                        }
                        break;
                    }
                    case 1: {
                        if (area[x][y + 1].equals(".")) {
                            a = x;
                            b = y + 1;
                            ok = true;
                        }
                        break;
                    }
                }

                if (!ok && one && two && three && four) {
                    result = "EXITED";
                    return result;
                }
            }

            computer.addInput((long) input);

            result = computer.runComputer();

            if (result.equals("EXITED")) {
                return result;
            }

            Long computerResponse = computer.getOutputs().poll();

            if (computerResponse == 0) {
                area[a][b] = "#";
            } else {
                if (computerResponse == 1) {
                    area[a][b] = ".";
                    x = a;
                    y = b;
                } else {
                    area[a][b] = "O";
                    oxygenX = a;
                    oxygenY = b;
                    return "EXITED";
                }
            }
            return result;
        }

        public String runRobot() {
            while (true) {
                printArea();
                String result = iterateRobot();
                if (result.equals("EXITED")) return result;
            }
        }
    }


    @Override
    public String part1(List<String> input) {

        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        SearchDroid robot = new SearchDroid(longInput, M , N, N/2, M/2);

        robot.runRobot();


        robot.setPrintArea(true);
        robot.printArea();

        int[][] result = new int[M][N];
        DijkstraHelper.Util.updateDistance(robot.getArea(), result);

        for (int i = 0; i < M; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        return input.isEmpty() ? "" : String.valueOf(result[N/2][M/2]);
    }

    @Override
    public String part2(List<String> input) {

        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        SearchDroid robot = new SearchDroid(longInput, N , M, N/2, M/2);

        robot.runRobot();


        robot.setPrintArea(true);
        robot.printArea();

        int[][] result = new int[M][N];
        DijkstraHelper.Util.updateDistance(robot.getArea(), result);


        for (int i = 0; i < M; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

        int result2 = 0;
        for (int o=0; o<N; o++){
            for (int p=0; p<M; p++) {
                if (result[o][p] > result2) result2 = result[o][p];
            }
        }

        return input.isEmpty() ? "" : String.valueOf(result2);
    }
}
