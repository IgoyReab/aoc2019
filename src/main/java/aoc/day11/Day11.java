package aoc.day11;

import aoc.Day;
import aoc.helper.IntegerComputerV2;
import aoc.helper.LoopHelper;
import lombok.Data;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11 implements Day {
    public Integer width = 8;
    public Integer heigth = 8;

    @Data
    public class HullRobot {
        private Character direction;
        private Integer x;
        private Integer y;
        private Integer[][] hull;
        private Integer panelsDone;
        private IntegerComputerV2 computer;

        public HullRobot(List<Long> inputProgram){
            this.direction = 'U';
            this.x = (width / 2) - 1;
            this.y = (heigth / 2) - 1;

            this.panelsDone = 0;
            this.computer = new IntegerComputerV2(inputProgram);
            this.hull = new Integer[width][heigth];
            LoopHelper.nestedLoop(hull, (a , b) -> hull[a][b] = 0);
        }

        private void printHull() {
            System.out.println("Image : \n");
            System.out.println("Count : " + computer.getCount());
            System.out.println("Input : " + computer.getInputParameter());


            LoopHelper.nestedLoop(hull, (b, a) -> {
                if (a==0) System.out.println();
                if ((this.x == a) && (this.y == b)) {
                    System.out.print(this.direction);
                } else {
                    System.out.print((hull[a][b] == 0) ? "." : "#");
                }
            });
            System.out.println("\n\n");
        }

        private  void iterateRobot() {

            computer.runIntegerComputer();

            boolean paintWhite = (computer.getResult().get(computer.getResult().size() - 1) == 1);
            System.out.println("Paint parameter : " + computer.getResult().get(computer.getResult().size() - 1));

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

            computer.runIntegerComputer();
            System.out.println( "Direction parameter : " + computer.getResult().get(computer.getResult().size() - 1));
            switch (direction) {
                case 'U': {
                    if (computer.getResult().get(computer.getResult().size()  - 1) == 0) {
                        direction = 'L';
                        x--;
                    } else {
                        direction = 'R';
                        x++;
                    }
                    break;
                }
                case 'D': {
                    if (computer.getResult().get(computer.getResult().size()  - 1) == 0) {
                        direction = 'R';
                        x++;
                    } else {
                        direction = 'L';
                        x--;
                    }
                    break;
                }
                case 'L': {
                    if (computer.getResult().get(computer.getResult().size()  - 1) == 0) {
                        direction = 'D';
                        y++;
                    } else {
                        direction = 'U';
                        y--;
                    }
                    break;
                }
                case 'R': {
                    if (computer.getResult().get(computer.getResult().size()  - 1) == 0) {
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
                computer.setInputParameter(1);
            } else {
                computer.setInputParameter(0);
            }
        }

        private int getInput(int putRegister) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your input to put at " + (putRegister + 1) + " : ");
            return scanner.nextInt();
        }

        public List<Long> runRobot() {

            while (!(computer.isHalted())) {
                printHull();
                iterateRobot();
                System.out.println(computer.getResult());
//                int stopEven = 0;
//                getInput(stopEven);
            }
            return computer.getResult();
        }
    }


    @Override
    public String part1(List<String> input) {

        List<Long> longInput = input.stream().
                map(Long::parseLong).collect(Collectors.toList());

        HullRobot robot = new HullRobot(longInput);

        System.out.println(robot.runRobot());

        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
