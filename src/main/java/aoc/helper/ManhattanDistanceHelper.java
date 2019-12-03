package aoc.helper;

public class ManhattanDistanceHelper
{
    public static int getManhattanDistance(int x, int y, int a, int b ) {
        return (Math.abs(x - a) + Math.abs(y - b) );
    }
}
