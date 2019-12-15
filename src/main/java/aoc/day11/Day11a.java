package aoc.day11;

import aoc.Day;
import aoc.helper.IntegerComputerV6;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day11a implements Day {

    @Data
    private class HullRobot {
        private IntegerComputerV6 computer;
        private HashMap<Cord, PaintColor> paintBoard;
        private Cord currentLocation = new Cord(0,0);
        private Direction facing = Direction.UP;

        HullRobot(List<Long> instructionSet, boolean isPart1) {
            computer = new IntegerComputerV6(instructionSet);
            paintBoard = new HashMap<>();
            if (!isPart1) {
                paintBoard.put(currentLocation, PaintColor.WHITE);
            }

        }
        void paintAway() {
            boolean done = false;
            while (!done) {
                PaintColor standingOn = paintBoard.getOrDefault(currentLocation, PaintColor.BLACK);
                Long input = standingOn == PaintColor.BLACK ? 0L : 1L;
                computer.addInput(input);

                String exitCode = computer.runComputer();

                if(exitCode.equals("EXITED")) {
                    done = true;
                }

                Long colorToPaint = computer.getOutputs().poll();
                Long directionTurn = computer.getOutputs().poll();

                if(colorToPaint == 0) {
                    paintBoard.put(currentLocation, PaintColor.BLACK);
                } else {
                    paintBoard.put(currentLocation, PaintColor.WHITE);
                }

                if(directionTurn == 0) {
                    turnLeft();
                }
                else {
                    turnRight();
                }

                moveForward();
            }
        }

        private void turnLeft() {
            switch (facing) {
                case UP:
                    facing = Direction.LEFT;
                    break;
                case DOWN:
                    facing = Direction.RIGHT;
                    break;
                case LEFT:
                    facing = Direction.DOWN;
                    break;
                case RIGHT:
                    facing = Direction.UP;
                    break;
            }
        }

        private void turnRight() {
            switch (facing) {
                case UP:
                    facing = Direction.RIGHT;
                    break;
                case DOWN:
                    facing = Direction.LEFT;
                    break;
                case LEFT:
                    facing = Direction.UP;
                    break;
                case RIGHT:
                    facing = Direction.DOWN;
                    break;
            }
        }

        private void moveForward() {
            switch (facing) {
                case UP:
                    currentLocation = new Cord(currentLocation.x, currentLocation.y + 1);
                    break;
                case DOWN:
                    currentLocation = new Cord(currentLocation.x, currentLocation.y - 1);
                    break;
                case RIGHT:
                    currentLocation = new Cord(currentLocation.x + 1, currentLocation.y);
                    break;
                case LEFT:
                    currentLocation = new Cord(currentLocation.x - 1, currentLocation.y);
                    break;

            }
        }

        private void printRobotsPaintBoard() {
            int minX = paintBoard.keySet().stream().map(Cord::getX).min(Integer::compareTo).get();
            int maxX = paintBoard.keySet().stream().map(Cord::getX).max(Integer::compareTo).get();
            int minY = paintBoard.keySet().stream().map(Cord::getY).min(Integer::compareTo).get();
            int maxY = paintBoard.keySet().stream().map(Cord::getY).max(Integer::compareTo).get();

            for(int i = maxY; i >= minY; i--) { // Y is upside down because max x is lowest value so have to flip it
                StringBuilder stringBuilder = new StringBuilder();
                for(int j = minX; j <= maxX; j++) {
                    if (PaintColor.WHITE == paintBoard.get(new Cord(j, i))) {
                        stringBuilder.append("#");
                    }
                    else {
                        stringBuilder.append(" ");
                    }
                }
                System.out.println(stringBuilder.toString());
            }
        }
    }

    public enum Direction{
        UP,
        LEFT,
        DOWN,
        RIGHT
    }

    public enum PaintColor{
        BLACK,
        WHITE
    }

    public static class Cord{
        private int x;
        private int y;

        Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            Cord other = (Cord)obj;
            return this.x == other.x && this.y == other.y;
        }
        @Override
        public int hashCode() {
            return  x + y;
        }
    }



    @Override
    public String part1(List<String> input) {

        List<Long> longInput =  Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        HullRobot robot = new HullRobot(longInput,  true);
        robot.paintAway();

        System.out.println("Part 1: painty boy painted :" + robot.paintBoard.keySet().size() + " squares");
        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {

        List<Long> longInput =  Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        HullRobot robot2 = new HullRobot(longInput, false);
        robot2.paintAway();

        System.out.println("Part 2 starting to print");
        robot2.printRobotsPaintBoard();

        return input.isEmpty() ? "" : "";
    }
}
