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
            int digit1 = Integer.parseInt(String.valueOf(inputString.charAt(i-1)));
            int digit2 = Integer.parseInt(String.valueOf(inputString.charAt(i)));

            if (i == maxIndex) {
                result = ((digit1 == digit2) && (digit1 != previousDigit));
                break;
            }

            int nextDigit = Integer.parseInt(String.valueOf(inputString.charAt(i+1)));
            if ((digit1 == digit2) && (digit1 != previousDigit) && (digit2 != nextDigit)) {
                result = true;
                break;
            }

            previousDigit = digit1;
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