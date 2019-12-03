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
    private int z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

@Data
class Line {
    private List<Coordinate> coordinates;

    public Line(){
        coordinates = new ArrayList<>();
    }

    public void addCoordinate(int x, int y, int z) {
        Coordinate coordinate = new Coordinate(x,y,z);
        coordinates.add(coordinate);
    }

    public int hasCoordinate(int x, int y){
        int result = -1;
        for (Coordinate c : coordinates) {
            if ((c.getX() == x ) && (c.getY() == y)) result = c.getZ();
            if (result > -1) break;
        }
        return result;
    }

}

public class Day03 implements Day {

    private Line processLine(String input){
        Line line = new Line();
        int x = 1;
        int y = 1;
        int z = 1;


        String[] xy = input.split(",");

        for(String s : xy) {
            char direction = s.charAt(0);
            int amount = Integer.parseInt(s.substring(1));

            for (int a = 0; a < amount; a++) {
                switch (direction) {
                    case 'R': x++; break;
                    case 'L': x--; break;
                    case 'U': y++; break;
                    case 'D': y--; break;
                }
                if ( line.hasCoordinate(x,y) == -1) line.addCoordinate(x, y, z);
                z++;
            }
        }
        return line;
    }


    @Override
    public String part1(List<String> input) {
        Line line1;
        Line line2;

        line1 = processLine(input.get(0));
        line2 = processLine(input.get(1));

        int foundDistance = Integer.MAX_VALUE;

        for(Coordinate coordinateLine1 : line1.getCoordinates()){
            int line2Distance = line2.hasCoordinate(coordinateLine1.getX(),coordinateLine1.getY());
            if (line2Distance > -1) {
                int distance =  ManhattanDistanceHelper.getManhattanDistance(1,1,coordinateLine1.getX(), coordinateLine1.getY());
                if (distance < foundDistance) foundDistance = distance;
                }
        }
        return input.isEmpty() ? "" : String.valueOf(foundDistance);
    }

    @Override
    public String part2(List<String> input) {
        Line line1;
        Line line2;

        line1 = processLine(input.get(0));
        line2 = processLine(input.get(1));

        int foundDistance = Integer.MAX_VALUE;

        for(Coordinate coordinateLine1 : line1.getCoordinates()){
            int line2Distance = line2.hasCoordinate(coordinateLine1.getX(),coordinateLine1.getY());
            if (line2Distance > -1) {
                int distance = coordinateLine1.getZ() + line2Distance;
                if (distance < foundDistance) foundDistance = distance;
            }
        }
        return input.isEmpty() ? "" : String.valueOf(foundDistance);
    }
}
