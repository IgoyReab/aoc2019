package aoc.day21;

import aoc.Day;
import aoc.helper.IntegerComputerV8;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day21 implements Day {

    @Data
    private class SpringDroid {
        IntegerComputerV8 computer;


        public SpringDroid(List<Long> inputProgram) {
            computer = new IntegerComputerV8(inputProgram);
        }

        public List<Long> walk() {
            List<Character> chars = "WALK"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }

            computer.addInput(10);

            computer.runComputer();

            return new ArrayList<>(computer.getOutputs());
        }

        public List<Long> run() {
            List<Character> chars = "RUN"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }

            computer.addInput(10);

            computer.runComputer();

            return new ArrayList<>(computer.getOutputs());
        }

        public void enterCommand(List<String> logics) {
            for (String logic : logics) {
                List<Character> chars = logic
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(Collectors.toList());

                for (Character c : chars) {
                    computer.addInput((int) c);
                }
                computer.addInput(10);
            }
        }
    }

    @Override
    public String part1(List<String> input) {
        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        SpringDroid springDroid = new SpringDroid(longInput);
        springDroid.computer.runComputer();


        // jump if A or C are hole and D isn't
        springDroid.enterCommand(Arrays.asList( "NOT A T",    // T = !A
                                                "NOT C J",    // J = !C
                                                "OR T J",     // J = T || J
                                                "AND D J"));  // J = D && J
                                                                // so J = (!A || !C) && D


        List<Long> outputList = springDroid.walk();

        String hull = "";
        for (long l : outputList) {
            hull = hull + String.valueOf((char) l);
        }
        System.out.println(hull);
        System.out.println(outputList);

        return input.isEmpty() ? "" : String.valueOf(outputList.get(outputList.size()-1));
    }

    @Override
    public String part2(List<String> input) {
        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());


        SpringDroid springDroid = new SpringDroid(longInput);
        springDroid.computer.runComputer();


        springDroid.enterCommand(Arrays.asList( "NOT A J",     // J = !A
                                                "NOT C T",     // T = !C
                                                "AND H T",     // T = H && T
                                                "OR T J",
                                                "NOT B T",
                                                "AND A T",
                                                "AND C T",
                                                "OR T J",
                                                "AND D J"));

        List<Long> outputList = springDroid.run();

        String hull = "";
        for (long l : outputList) {
            hull = hull + String.valueOf((char) l);
        }
        System.out.println(hull);
        System.out.println(outputList);

        return input.isEmpty() ? "" : String.valueOf(outputList.get(outputList.size()-1));
    }
}
