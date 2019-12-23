package aoc.day23;

import aoc.Day;
import aoc.helper.IntegerComputerV9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 implements Day {

    public class Nat {
        private List<IntegerComputerV9> computers;
        private long xRegister;
        private long yRegister;
        boolean first;
        private long previousYRegister;


        public Nat(List<String> input) {
            computers = new ArrayList<>();
            for (int x=0; x<50; x++) {
                computers.add(new IntegerComputerV9(Arrays.stream(input.get(0).split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toList())));
                computers.get(x).addInput((long) x);
                computers.get(x).runComputer();
                previousYRegister = 0;
                first = true;
            }
        }

        private boolean isIdle() {
            for (int x=0; x<50; x++) {
                if (!computers.get(x).getInputs().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        private long runPart1() {
            boolean running = true;
            long result = 0;

            while (running) {
                for (int x=0; x<50; x++) {
                    if (computers.get(x).getInputs().isEmpty()) {
                        computers.get(x).addInput(-1L);
                        computers.get(x).runComputer();

                    } else {
                        while (!computers.get(x).getInputs().isEmpty()) {
                            computers.get(x).runComputer();
                            computers.get(x).runComputer();
                        }
                    }

                    while (!computers.get(x).getOutputs().isEmpty()) {
                        long parameter1 = computers.get(x).getOutputs().remove();
                        long parameter2 = computers.get(x).getOutputs().remove();
                        long parameter3 = computers.get(x).getOutputs().remove();

                        if (parameter1 == 255) {

                            result = parameter3;
                            running = false;
                            break;
                        }

                        if (parameter1 >= 0 && parameter1<50) {
                            computers.get((int) parameter1).addInput(parameter2);
                            computers.get((int) parameter1).addInput(parameter3);
                        }
                    }
                }
            }
            return result;
        }


        private long runPart2() {
            boolean running = true;
            long result = 0;

            while (running) {
                for (int x=0; x<50; x++) {
                    if (computers.get(x).getInputs().isEmpty()) {
                        computers.get(x).addInput(-1L);
                        computers.get(x).runComputer();

                    } else {
                        while (!computers.get(x).getInputs().isEmpty()) {
                            computers.get(x).runComputer();
                            computers.get(x).runComputer();
                        }
                    }

                    while (!computers.get(x).getOutputs().isEmpty()) {
                        long parameter1 = computers.get(x).getOutputs().remove();
                        long parameter2 = computers.get(x).getOutputs().remove();
                        long parameter3 = computers.get(x).getOutputs().remove();

                        if (parameter1 == 255) {
                            xRegister = parameter2;
                            yRegister = parameter3;
                        }

                        if (parameter1 >= 0 && parameter1<50) {
                            computers.get((int) parameter1).addInput(parameter2);
                            computers.get((int) parameter1).addInput(parameter3);
                        }
                    }

                    if (isIdle()) {
                        computers.get(0).addInput(xRegister);
                        computers.get(0).addInput(yRegister);
                        if (!first) {
                            if (yRegister == previousYRegister) {
                                result = yRegister;
                                running = false;
                                break;
                            } else {
                                previousYRegister = yRegister;
                            }
                        }
                        first = false;
                    }
                }
            }
            return result;
        }
    }


    @Override
    public String part1(List<String> input) {
        Nat nat = new Nat(input);
        return input.isEmpty() ? "" : String.valueOf(nat.runPart1());
    }

    @Override
    public String part2(List<String> input) {
        Nat nat = new Nat(input);
        return input.isEmpty() ? "" : String.valueOf(nat.runPart2());
    }
}
