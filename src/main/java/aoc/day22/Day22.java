package aoc.day22;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day22 implements Day {

    List<Integer> testDeck = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private List<Long> getSpaceDeck2(){
        List<Long> spaceDeck = new ArrayList<>();
        for (long x=0;x<119315717514047L;x++) {
            spaceDeck.add(x);
        }
        return spaceDeck;
    }

    private List<Integer> getSpaceDeck(){
        List<Integer> spaceDeck = new ArrayList<>();
        for (int x=0;x<10007;x++) {
            spaceDeck.add(x);
        }
        return spaceDeck;
    }

    private List<Integer> cut(List<Integer> input, int amount) {
        Collections.rotate(input, amount * -1);
        return input;
    }

    private List<Integer> dealNewStack(List<Integer> input) {
        Collections.reverse(input);
        return input;
    }

    private List<Integer> dealWithIncrement(List<Integer> input, int increment) {
        List<Integer> result = new ArrayList<>(input);
        int position = 0;
        for (int x = 0; x < input.size(); x++) {
            result.set(position, input.get(x));
            position += increment;
            if (position > input.size()) position -= input.size();
        }
        return result;
    }

    private List<Long> cut2(List<Long> input, int amount) {
        Collections.rotate(input, amount * -1);
        return input;
    }

    private List<Long> dealNewStack2(List<Long> input) {
        Collections.reverse(input);
        return input;
    }

    @Override
    public String part1(List<String> input) {
        boolean deal = false;
        boolean cut = false;
        boolean stack = false;
        int increment = 0;

        List<Integer> result = new ArrayList<>(getSpaceDeck());

        for (String s: input) {
            String[] s1 = s.split(" ");
            if (s1.length == 4) {
                if (s1[3].equals("stack")) {
                    stack = true;
                    deal = false;
                    cut = false;
                } else {
                    increment = Integer.parseInt(s1[3]);
                    deal = true;
                    stack = false;
                    cut = false;
                }
            } else {
                cut = true;
                deal = false;
                stack = false;
                increment = Integer.parseInt(s1[1]);
            }

            if (cut) {
                result = cut(result, increment);
                System.out.println("cut increment " + increment);
            }
            if (stack) {
                result = dealNewStack(result);
                System.out.println("deal into new stack");
            }
            if (deal) {
                result = dealWithIncrement(result, increment);
                System.out.println("deal with increment " + increment);
            }

        }


        int foundPosition = 0;
        for (int x=0; x<result.size();x++) {
            System.out.println(" x = " + x + " card = " + result.get(x));
            if (result.get(x) == 2019) {
                foundPosition = x;
                break;
            }
        }

        return input.isEmpty() ? "" : String.valueOf(foundPosition);
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
