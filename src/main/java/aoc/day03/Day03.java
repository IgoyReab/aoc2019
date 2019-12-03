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
    private int distanceFromCentralPort;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.distanceFromCentralPort = z;
    }
}

@Data
class Wire {
    private List<Coordinate> coordinates;

    public Wire(){
        coordinates = new ArrayList<>();
    }

    public void addCoordinate(int x, int y, int z) {
        Coordinate coordinate = new Coordinate(x,y,z);
        coordinates.add(coordinate);
    }

    public int hasCoordinate(int x, int y){
        int result = -1;
        for (Coordinate c : coordinates) {
            if ((c.getX() == x ) && (c.getY() == y)) result = c.getDistanceFromCentralPort();
            if (result > -1) break;
        }
        return result;
    }

}

public class Day03 implements Day {

    private Wire processLine(String input){
        Wire wire = new Wire();
        int x = 1;
        int y = 1;
        int count = 1;

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
                if ( wire.hasCoordinate(x,y) == -1) wire.addCoordinate(x, y, count);
                count++;
            }
        }
        return wire;
    }

    private String findDistance(List<String> input, int part) {
        Wire wire1;
        Wire wire2;

        wire1 = processLine(input.get(0));
        wire2 = processLine(input.get(1));

        int foundDistance = Integer.MAX_VALUE;

        for(Coordinate coordinateLine1 : wire1.getCoordinates()){
            int wireDistance = wire2.hasCoordinate(coordinateLine1.getX(),coordinateLine1.getY());
            if (wireDistance > -1) {
                int distance = 0;
                if (part == 1) distance = ManhattanDistanceHelper.getManhattanDistance(1, 1, coordinateLine1.getX(), coordinateLine1.getY());
                if (part == 2) distance = coordinateLine1.getDistanceFromCentralPort() + wireDistance;
                if (distance < foundDistance) foundDistance = distance;
            }
        }
        return input.isEmpty() ? "" : String.valueOf(foundDistance);
    }


    @Override
    public String part1(List<String> input) {
        return input.isEmpty() ? "" : findDistance(input, 1);
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : findDistance(input, 2);
    }
}
