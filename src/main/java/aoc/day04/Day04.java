package aoc.day04;

import aoc.Day;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements Day {

    private boolean hasSameAdjacent(String inputString) {
        boolean result = false;

        for (int i=1; i < inputString.length(); i++ ) {
            if (Integer.parseInt(String.valueOf(inputString.charAt(i))) == Integer.parseInt(String.valueOf(inputString.charAt(i-1))) ) {
                result = true;
                break;
            }
        }

        return result;
    }
private boolean hasSameAdjacentOnlyTwice(String inputString) {
        boolean result = false;
        int previousDigit = -1;
        int maxIndex = inputString.length() - 1;

        for (int i=1; i < inputString.length(); i++ ) {
            int d1 = Integer.parseInt(String.valueOf(inputString.charAt(i)));
            int d2 = Integer.parseInt(String.valueOf(inputString.charAt(i-1)));

            if (i == maxIndex) {
                result = ((d1 == d2) && (d2 != previousDigit));
                break;
            }

            int d3 = Integer.parseInt(String.valueOf(inputString.charAt(i+1)));

            if ((d1 == d2) && (d1 != d3) && (d2 != previousDigit)) {
                result = true;
                break;
            }

            previousDigit = d2;
        }

        return result;
    }

    private boolean isNeverDecreasing(String inputString) {
        boolean result = true;

        for (int i=1; i < inputString.length(); i++ ) {
            if (Integer.parseInt(String.valueOf(inputString.charAt(i))) < Integer.parseInt(String.valueOf(inputString.charAt(i-1))) ) {
                result = false;
                break;
            }
        }

        return result;
    }

    @Override
    public String part1(List<String> input) {
        String[] xy = input.get(0).split("-");

        List<Integer> possiblePasswords = new ArrayList<>();

        for(int x = Integer.parseInt(xy[0]); x <= Integer.parseInt(xy[1]); x++) {
            if ((isNeverDecreasing(String.valueOf(x))) && (hasSameAdjacent(String.valueOf(x)))) possiblePasswords .add(x);
        }
        return input.isEmpty() ? "" : String.valueOf(possiblePasswords.size());
    }

    @Override
    public String part2(List<String> input) {String[] xy = input.get(0).split("-");

        List<Integer> possiblePasswords = new ArrayList<>();

        for(int x = Integer.parseInt(xy[0]); x <= Integer.parseInt(xy[1]); x++) {
            if ((isNeverDecreasing(String.valueOf(x))) && (hasSameAdjacentOnlyTwice(String.valueOf(x)))) possiblePasswords .add(x);
        }
        return input.isEmpty() ? "" : String.valueOf(possiblePasswords.size());
    }
}