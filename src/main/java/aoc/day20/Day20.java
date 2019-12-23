package aoc.day20;

import aoc.Day;
import lombok.Data;

import java.util.List;

public class Day20 implements Day {

    @Data
    private class Point{
        int xPoint;
        int yPoint;
        String type;
        String portalLabel;

        public Point(int x, int y, String label) {
            xPoint = x;
            yPoint = y;
            portalLabel = label;

        }

        public boolean isWall() {
            return type.equals("#");
        }

        public boolean isTile() {
            return type.equals(".");
        }

        public boolean isEmptyPoint() {

            return type.equals(" ");
        }

        public boolean isPortal() {
            boolean result = false;

            if (xPoint == 0 || yPoint == 0 || xPoint == xMax - 1 || yPoint == yMax - 1) {
                return isTile();
            } else {
                return ((
                        maze[xPoint - 1][yPoint].isEmptyPoint() ||
                        maze[xPoint + 1][yPoint].isEmptyPoint() ||
                        maze[xPoint][yPoint - 1].isEmptyPoint() ||
                        maze[xPoint][yPoint + 1].isEmptyPoint()) &&
                        maze[xPoint][yPoint].isTile());

            }
        }



    }

    Point[][] maze;
    int xMax;
    int yMax;

    private void printMaze(Point[][] input, int width, int heigth) {

        for (int b = 0; b < heigth; b++) {
            for (int a = 0; a < width; a++) {
                {
                    if (a == 0) System.out.println();
                    if (input[a][b].isPortal()) {
                        System.out.print("o");
                    } else {
                    System.out.print(input[a][b].getType());
                    }
                }
            }
        }
        System.out.println("\n\n");
    }


    @Override
    public String part1(List<String> input) {

        xMax = input.get(0).length() - 4;
        yMax = input.size() - 4;
        maze = new Point[xMax][yMax];

        for (int y = 2; y<yMax+2; y++  ){
            for (int x = 2; x<xMax+2; x++) {
                maze[x-2][y-2] = new Point(x-2 , y-2 , "");
                maze[x-2][y-2].setType(String.valueOf(input.get(y).charAt(x)));
            }
        }


        printMaze(maze, xMax, yMax);

        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
