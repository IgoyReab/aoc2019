package aoc.day03;

import aoc.Day;
import aoc.helper.ManhattanDistanceHelper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

@Data
class Line {
    private List<Coordinate> coordinates;

    public Line(){
        coordinates = new ArrayList<>();
    }

    public void addCoordinate(int x, int y) {
        Coordinate coordinate = new Coordinate(x,y);
        coordinates.add(coordinate);
    }
}

public class Day03 implements Day {

    private Line processLine(String input){
        Line line = new Line();
        int x = 1;
        int y = 1;

        String[] xy = input.split(",");

        for(String s : xy) {
            char direction = s.charAt(0);
            int amount = Integer.parseInt(s.substring(1));

            for (int a = 0; a < amount; a++) {
                switch (direction) {
                    case 'U':
                        y++;
                        break;
                    case 'R':
                        x++;
                        break;
                    case 'L':
                        x--;
                        break;
                    case 'D':
                        y--;
                        break;

                    default:
                        System.out.println("Unknown direction");
                }
                line.addCoordinate(x,y);
            }
        }
        return line;
    }


    @Override
    public String part1(List<String> input) {
        Line line1;
        Line line2;

        List<Coordinate> crossings = new ArrayList<>();
        line1 = processLine(input.get(0));
        line2 = processLine(input.get(1));

        for(Coordinate coordinateLine1 : line1.getCoordinates()){
            for (Coordinate coordinateLine2 : line2.getCoordinates()){
                if ( (coordinateLine1.getX() == coordinateLine2.getX()) &&
                        (coordinateLine1.getY() == coordinateLine2.getY())) {
                    Coordinate foundCoordinate = new Coordinate(coordinateLine1.getX(), coordinateLine2.getY());
                    crossings.add(foundCoordinate);
                }
            }
        }

        int foundDistance = Integer.MAX_VALUE;
        for(Coordinate coordinate: crossings) {
            int distance =  ManhattanDistanceHelper.getManhattanDistance(1,1,coordinate.getX(), coordinate.getY());
            if (distance < foundDistance) foundDistance = distance;
        }
        return input.isEmpty() ? "" : String.valueOf(foundDistance);
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
