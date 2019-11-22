package aoc.day02;

import aoc.Day;
import com.google.common.base.CharMatcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day02 implements Day {

    static int countChars(String testString, char testChar) {
        return CharMatcher.is(testChar).countIn(testString);
    }

    @Override
    public String part1(List<String> input) {
        List<String> foundDoubles = input.stream().
                filter(inputLine -> IntStream.range(0, inputLine.length())
                        .anyMatch(i -> (countChars(inputLine, inputLine.charAt(i))) == 2))
                .collect(Collectors.toList());

        List<String> foundTriples = input.stream().
                filter(inputLine -> IntStream.range(0, inputLine.length())
                        .anyMatch(i -> (countChars(inputLine, inputLine.charAt(i))) == 3))
                .collect(Collectors.toList());

        return input.isEmpty() ? "" : String.valueOf(foundDoubles.size() * foundTriples.size());
    }

    @Override
    public String part2(List<String> input) {
        boolean foundIt = false;
        char resultChar = ' ';
        String foundString = "";

        for (String inputFirstLoop : input) {                                   // loop through the array
            for (String inputSecondLoop : input) {                              // loop through the array a second time, now I have to elements of the array at the same time
                LinkedList<Character> foundDifference = new LinkedList<>();    // initialize a list to count different characters
                if (Objects.equals(inputFirstLoop, inputSecondLoop))
                    continue;                                                   // Do not have to check the element against itself
                for (int i = 0; i < inputFirstLoop.length(); i++) {             // Loop through both the elements to determine which characters are the same
                    if (inputFirstLoop.charAt(i) != inputSecondLoop.charAt(i)) {// if they are not the same add the character to the list
                        resultChar = inputFirstLoop.charAt(i);                  // save the Char that is not the same for later use
                        foundDifference.add(resultChar);
                    }
                }
                foundIt = (foundDifference.size() == 1);                        // finaly if there was only one element in the list it is found, set foundIt so I can terminate
                if (foundIt)
                    break;                                                      // terminate the iteration now and I the higher loop.....
            }
            // save the found value , subtract the different character in a variable
            foundString = (inputFirstLoop.replace(String.valueOf(resultChar), ""));
            if (foundIt) break;
        }

        return input.isEmpty() ? "" : foundString;
    }
}
