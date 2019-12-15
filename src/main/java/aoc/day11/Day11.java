package aoc.day11;

import aoc.Day;
import aoc.helper.IntegerComputerV6;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 implements Day {

        @Data
        public class HullRobot {
            private int width;
            private int heigth;
            private Character direction;
            private Integer x;
            private Integer y;
            private Integer[][] hull;
            private Integer panelsDone;
            private IntegerComputerV6 computer;
            boolean printHull = false;

            public HullRobot(List<Long> inputProgram, int x, int y, int x2, int y2) {
                this.width = x;
                this.heigth = y;
                this.direction = 'U';
                this.x = x2;
                this.y = y2;

                this.panelsDone = 0;
                this.computer = new IntegerComputerV6(inputProgram);
                this.hull = new Integer[width][heigth];
                for (int a=0; a<width; a++) {
                    for (int b=0; b<heigth; b++){
                        hull[a][b] = 0;
                    }
                }
            }

            public void paintPanel(int x, int y, int color) {
                hull[x][y] = (color == 1) ? 1 : 2;
            }

            private void printHull() {
                if (printHull) {

                    System.out.println("Count  : " + computer.getCount());
                    System.out.println("Input  : " + computer.getInputs());
                    System.out.println("Output : " + computer.getOutputs());
                    System.out.println("Panels : " + panelsDone);

                    for (int b=0; b<heigth; b++) {
                        for (int a=0; a<width; a++) {
                            {
                                if (a == 0) System.out.println();
                                if ((this.x == a) && (this.y == b)) {
                                    System.out.print(this.direction);
                                } else {
                                    System.out.print((hull[a][b] == 1) ? "#" : ".");
                                }
                            }
                        }
                    }
                    System.out.println("\n\n");
                }
            }

            private String iterateRobot() {

                String result = computer.runComputer();

                if (result.equals("EXITED")){
                    return result;
                }

                Long colorToPaint = computer.getOutputs().poll();

                boolean paintWhite = (colorToPaint == 1);

                if (hull[x][y] == 0) {
                    if (paintWhite) {
                        hull[x][y] = 1;
                        panelsDone++;
                    } else {
                        hull[x][y] = 2;
                    }
                } else {
                    if (paintWhite) {
                        hull[x][y] = 1;
                    } else {
                        hull[x][y] = 2;
                    }
                }

                Long directionTurn = computer.getOutputs().poll();
                switch (direction) {
                    case 'U': {
                        if (directionTurn == 0) {
                            direction = 'L';
                            x--;
                        } else {
                            direction = 'R';
                            x++;
                        }
                        break;
                    }
                    case 'D': {
                        if (directionTurn == 0) {
                            direction = 'R';
                            x++;
                        } else {
                            direction = 'L';
                            x--;
                        }
                        break;
                    }
                    case 'L': {
                        if (directionTurn == 0) {
                            direction = 'D';
                            y++;
                        } else {
                            direction = 'U';
                            y--;
                        }
                        break;
                    }
                    case 'R': {
                        if (directionTurn == 0) {
                            direction = 'U';
                            y--;
                        } else {
                            direction = 'D';
                            y++;
                        }
                        break;
                    }
                }

                if (hull[x][y] == 1) {
                    computer.addInput((long) 1);
                } else {
                    computer.addInput((long) 0);
                }

                return result;
            }

            public String runRobot() {
                while (true) {
                    printHull();
                    String result = iterateRobot();
                    if (result.equals("EXITED")) return result;
                }
            }
        }


        @Override
        public String part1(List<String> input) {

            List<Long> longInput =  Arrays.stream(input.get(0).split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            HullRobot robot = new HullRobot(longInput, 100, 100, 50, 50);
            robot.computer.addInput((long) 0);

            robot.runRobot();

            robot.setPrintHull(true);
            robot.printHull();

            System.out.println(robot.getPanelsDone());

            return input.isEmpty() ? "" : String.valueOf(robot.getPanelsDone());
        }

    @Override
    public String part2(List<String> input) {

        List<Long> longInput =  Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());


        HullRobot robot2 = new HullRobot(longInput, 43, 6, 0 , 0);


        robot2.computer.addInput((long) 1);
        robot2.paintPanel(robot2.getX(), robot2.getY(), 1);

        robot2.runRobot();

        robot2.setPrintHull(true);
        robot2.printHull();

        System.out.println(robot2.getPanelsDone());

        return input.isEmpty() ? "" : String.valueOf(robot2.getPanelsDone());
    }
}
