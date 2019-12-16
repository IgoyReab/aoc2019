package aoc.day16;

import aoc.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Day16 implements Day {


    private List<Integer> expandList (List<Integer> input, int factor) {
        List<Integer> result = new ArrayList<>();

        for (int x=0; x< input.size(); x++) {
            for (int y=0; y<factor; y++) {
                result.add(input.get(x));
            }
        }
        return result;
    }

    private List<Integer> parsePhase (List<Integer> input, List<Integer> parse) {
        List<Integer> result = new ArrayList<>();

        for (int a=0; a<input.size(); a++) {
            List<Integer> thisParse = expandList(parse, a+1);
            int newDigit= 0;
            int y = 0;
            for (int x=0; x<input.size(); x++) {
                y++;
                if (y>=thisParse.size()) y=0;
                newDigit = newDigit + (input.get(x) * thisParse.get(y));
            }
            result.add(abs(newDigit)%10);
        }
        return result;
    }

    @Override
    public String part1(List<String> input) {

        // Create an empty List of character
        List<Character> chars = input.get(0)
                .chars()
                .mapToObj(e -> (char)e)
                .collect(toList());

        List<Integer> inputList = chars.stream().map(e -> Integer.parseInt(String.valueOf(e))).collect(toList());

        System.out.println(inputList);

        List<Integer> parseList = new ArrayList<>();
        parseList.add(0);
        parseList.add(1);
        parseList.add(0);
        parseList.add(-1);

        System.out.println(parseList);

        List<Integer> result = parsePhase(inputList, parseList);
        for (int x=1; x<100; x++) {
            result = parsePhase(result, parseList);
        }

        System.out.println(result);
        return input.isEmpty() ? "" : String.valueOf(result);
    }

    @Override
    public String part2(List<String> input) {
        List<Character> chars = input.get(0)
                .chars()
                .mapToObj(e -> (char)e)
                .collect(toList());

        List<Integer> initialList = chars.stream().map(e -> Integer.parseInt(String.valueOf(e))).collect(toList());

        String offSet = "";
        for (int count=0; count<7; count++) {
            offSet = offSet + initialList.get(count);
        }

        long offSetNum = Long.parseLong(offSet);

        List<Integer> inputList = new ArrayList<>();

        for (int count=0; count<10000; count++) {
            inputList.addAll(initialList);
        }

        List<Integer> finalInputList = inputList;
        inputList = IntStream.range(0, inputList.size())
                .skip(offSetNum)
                .map(index -> finalInputList.get(index))
                .boxed()
                .collect(toList());


        for (int x=0; x<100; x++) {
            int resulting = 0;
            for(int position = inputList.size() - 1; position >= 0; position--) {
                resulting += inputList.get(position);
                resulting = Math.abs(resulting%10);
                inputList.set(position, resulting);
            }
        }

        return input.isEmpty() ? "" : inputList.stream()
                .limit(8)
                .map(String::valueOf)
                .collect(joining(""));
    }
}
