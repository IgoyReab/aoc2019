package aoc.day04;

import aoc.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> inputList = new ArrayList<>();

        for(int x = Integer.parseInt(xy[0]); x <= Integer.parseInt(xy[1]); x++) {

            inputList.add(String.valueOf(x));
        }

        List<String> foundDoubles = inputList.stream().filter(this::hasSameAdjacent).collect(Collectors.toList());

        List<String> possiblePasswords = foundDoubles.stream().filter(this::isNeverDecreasing).collect(Collectors.toList());

        return input.isEmpty() ? "" : String.valueOf(possiblePasswords.size());
    }

    @Override
    public String part2(List<String> input) {


        return input.isEmpty() ? "" : "";
    }
}