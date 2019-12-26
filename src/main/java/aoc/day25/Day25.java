package aoc.day25;

import aoc.Day;
import aoc.helper.IntegerComputerV8;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day25 implements Day {

    private String getInput() {
        String result = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("  -> ");
        result = scanner.nextLine();
        if (result.equals("l")) return "east";
        if (result.equals("r")) return "west";
        if (result.equals("u")) return "north";
        if (result.equals("d")) return "south";

        if (result.equals("d1")) return "drop astronaut ice cream";
        if (result.equals("d2")) return "drop mutex";
        if (result.equals("d3")) return "drop spool of cat6";
        if (result.equals("d4")) return "drop hypercube";
        if (result.equals("d5")) return "drop antenna";
        if (result.equals("d6")) return "drop sand";
        if (result.equals("d7")) return "drop mouse";
        if (result.equals("d8")) return "drop boulder";

        if (result.equals("t1")) return "take astronaut ice cream";
        if (result.equals("t2")) return "take mutex";
        if (result.equals("t3")) return "take spool of cat6";
        if (result.equals("t4")) return "take hypercube";
        if (result.equals("t5")) return "take antenna";
        if (result.equals("t6")) return "take sand";
        if (result.equals("t7")) return "take mouse";
        if (result.equals("t8")) return "take boulder";

        if (result.equals("i")) return "inv";

        return result;
    }

    @Data
    private class SmallDroid {
        IntegerComputerV8 computer;


        public SmallDroid(List<Long> inputProgram) {
            computer = new IntegerComputerV8(inputProgram);
        }

        public void north() {
            List<Character> chars = "north"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }
            computer.addInput(10);

            computer.runComputer();
            showOutput();
        }

        public void south() {
            List<Character> chars = "south"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }
            computer.addInput(10);

            computer.runComputer();

            showOutput();
        }

        public void west() {
            List<Character> chars = "west"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }
            computer.addInput(10);

            computer.runComputer();

            showOutput();
        }

        public void east() {
            List<Character> chars = "east"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }
            computer.addInput(10);

            computer.runComputer();

           showOutput();
        }

        public void inv() {
            List<Character> chars = "inv"
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            for (Character c : chars) {
                computer.addInput((int) c);
            }
            computer.addInput(10);

            computer.runComputer();

            showOutput();
        }

        public void enterCommand(String command){

                List<Character> chars = command
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(Collectors.toList());

                for (Character c : chars) {
                    computer.addInput((int) c);
                }
                computer.addInput(10);
                computer.runComputer();
                showOutput();
        }

        public void enterCommands(List<String> logics) {
            for (String logic : logics) {
                List<Character> chars = logic
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(Collectors.toList());

                for (Character c : chars) {
                    computer.addInput((int) c);
                }
                computer.addInput(10);
                computer.runComputer();
            }
            showOutput();
        }

        public void showOutput () {

            String output = "";
            for (long l :computer.getOutputs()) {
                output = output + String.valueOf((char) l);
            }

            System.out.println(output);
            computer.getOutputs().clear();
        }
    }

    @Override
    public String part1(List<String> input) {
        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        SmallDroid smallDroid = new SmallDroid(longInput);
        smallDroid.computer.runComputer();
        smallDroid.inv();
        smallDroid.enterCommands(Arrays.asList( "south",
                                        "take astronaut ice cream",
                                        "north",
                                        "east",
                                        "take mouse",
                                        "north",
                                        "take spool of cat6",
                                        "north",
                                        "take hypercube",
                                        "east",
                                        "take sand",
                                        "south",
                                        "take antenna",
                                        "north",
                                        "west",
                                        "south",
                                        "south",
                                        "south",
                                        "take mutex",
                                        "west",
                                        "take boulder",
                                        "south",
                                        "south",
                                        "south",
                                        "west",
                                        "south",
                                        "drop astronaut ice cream",
                                        "drop mutex",
                                        "drop spool of cat6",
                                        "drop hypercube",
                                        "drop antenna",
                                        "drop sand",
                                        "drop mouse",
                                        "drop boulder",
                                        "inv"));

        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 2; b++) {
                for (int c = 0; c < 2; c++) {
                    for (int d = 0; d < 2; d++) {
                        for (int e = 0; e < 2; e++) {
                            for (int f = 0; f < 2; f++) {
                                for (int g = 0; g < 2; g++) {
                                    for (int h = 0; h < 2; h++) {
                                        smallDroid.enterCommands(Arrays.asList("drop astronaut ice cream",
                                                "drop mutex",
                                                "drop spool of cat6",
                                                "drop hypercube",
                                                "drop antenna",
                                                "drop sand",
                                                "drop mouse",
                                                "drop boulder"));
                                        if (a == 1) smallDroid.enterCommand("take astronaut ice cream");
                                        if (b == 1) smallDroid.enterCommand("take mutex");
                                        if (c == 1) smallDroid.enterCommand("take spool of cat6");
                                        if (d == 1) smallDroid.enterCommand("take hypercube");
                                        if (e == 1) smallDroid.enterCommand("take antenna");
                                        if (f == 1) smallDroid.enterCommand("take sand");
                                        if (g == 1) smallDroid.enterCommand("take mouse");
                                        if (h == 1) smallDroid.enterCommand("take boulder");
                                        System.out.println(a + "-" + b + "-" + c + "-" + d + "-" + e + "-" + f + "-" + g + "-" + h);
                                        smallDroid.enterCommand("south");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        String droidInput = "";



        while (!droidInput.equals("quit")) {
            droidInput = getInput();
            smallDroid.enterCommand(droidInput);
        }

        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        List<Long> longInput = Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());


        SmallDroid smallDroid = new SmallDroid(longInput);
        smallDroid.computer.runComputer();

        return input.isEmpty() ? "" : "";
    }

}
