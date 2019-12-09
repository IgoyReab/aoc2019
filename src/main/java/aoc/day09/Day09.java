package aoc.day09;

import aoc.Day;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day09 implements Day {
    public long initialInput;

    @Data
    public class IntegerComputer {
        private int number;
        private int count;
        private int oppCode;
        private long parameter1;
        private long parameter2;
        private long parameter3;
        private List<Long> result;
        private long relativeBias;
        private List<Long> inputProgram ;

        public IntegerComputer(List<Long> inputProgram, int number) {
            this.number = number;
            this.count = 0;
            this.oppCode = 0;
            this.result = new ArrayList<>();
            this.relativeBias = 0;
            this.inputProgram = inputProgram;
            for (int x = inputProgram.size(); x < 100000; x++) inputProgram.add((long) 0);
        }

        public List<Long> runIntegerComputer() {
            while ( oppCode != 99 ) {
                oppCode = (int) ((((inputProgram.get(count) / 10) % 10) * 10) + (inputProgram.get(count) % 10));
                boolean positionMode1 = (0 == ( inputProgram.get(count) / 100) % 10);
                boolean positionMode2 = (0 == ( inputProgram.get(count) / 1000) % 10);
                boolean positionMode3 = (0 == ( inputProgram.get(count) / 10000) % 100);
                boolean immediateMode1 = (1 == ( inputProgram.get(count) / 100) % 10);
                boolean immediateMode2 = (1 == ( inputProgram.get(count) / 1000) % 10);
                boolean immediateMode3 = (1 == ( inputProgram.get(count) / 10000) % 100);
                boolean relativeMode1 = (2 == ( inputProgram.get(count) / 100) % 10);
                boolean relativeMode2 = (2 == ( inputProgram.get(count) / 1000) % 10);
                boolean relativeMode3 = (2 == ( inputProgram.get(count) / 10000) % 10);

                // System.out.println("Program : " + inputProgram.get(count) + " " + inputProgram.get(count + 1) + " " + inputProgram.get(count + 2) + " " + inputProgram.get(count + 3) );

                switch (oppCode) {
                    case 99:
                        break;
                    case 1:
                    case 2: {
                        parameter1 = inputProgram.get(count + 1);
                        parameter2 = inputProgram.get(count + 2);
                        parameter3 = inputProgram.get(count + 3);

                        if (positionMode1) parameter1 = inputProgram.get((int) parameter1);
                        if (positionMode2) parameter2 = inputProgram.get((int) parameter2);
                        // if (positionMode3) parameter3 = inputProgram.get((int) parameter3);

                        if (relativeMode1) {
                            int newIndex = (int)(parameter1 + relativeBias);
                            if (newIndex >= 0) {
                                parameter1 = inputProgram.get(newIndex);
                            }
                        }

                        if (relativeMode2) {
                            int newIndex = (int)(parameter2 + relativeBias);
                            if (newIndex >= 0) {
                                parameter2 = inputProgram.get(newIndex);
                            }
                        }

                        if (relativeMode3) {
                            int newIndex = (int)(parameter3 + relativeBias);
                            if (newIndex >= 0) {
                                parameter3 = newIndex;
                            }
                        }


                        if (oppCode == 1) {
                            inputProgram.set((int) parameter3, parameter1 + parameter2);
                        } else {
                            inputProgram.set((int) parameter3, parameter1 * parameter2);
                        }
                        count += 4;
                        break;
                    }
                    case 3: {
                        parameter1 = inputProgram.get(count + 1);
                        if (positionMode1) parameter1 = inputProgram.get((int) parameter1);
                        if (relativeMode1) {
                            int newIndex = (int)(parameter1 + relativeBias);
                            if (newIndex >= 0) parameter1 = newIndex;
                        }
                        inputProgram.set((int) parameter1, initialInput);
                        count += 2;
                        break;
                    }
                    case 4: {
                        parameter1 = inputProgram.get(count + 1);
                        if (positionMode1) {
                            parameter1 = inputProgram.get((int) parameter1);
                        }

                        if (relativeMode1) {
                            int newIndex = ((int)(parameter1 + relativeBias));
                            if (newIndex >= 0) parameter1 = inputProgram.get(newIndex);
                        }

                        result.add(parameter1);
                        count += 2;
                        break;
                    }
                    case 5:
                    case 6: {
                        parameter1 = inputProgram.get(count + 1);
                        parameter2 = inputProgram.get(count + 2);

                        if (positionMode1) parameter1 = inputProgram.get((int) parameter1);
                        if (positionMode2) parameter2 = inputProgram.get((int) parameter2);

                        if (relativeMode1) {
                            int newIndex = ((int)(parameter1 + relativeBias));
                            if (newIndex >= 0) parameter1 = inputProgram.get(newIndex);
                        }

                        if (relativeMode2) {
                            int newIndex = ((int)(parameter2 + relativeBias));
                            if (newIndex >= 0) parameter2 = inputProgram.get(newIndex);
                        }

                        if (relativeMode3) {
                            int newIndex = ((int)(parameter3 + relativeBias));
                            if (newIndex >= 0) parameter3 = inputProgram.get(newIndex);
                        }

                        count += 3;
                        if ((oppCode == 5) && (parameter1 != 0)) count = (int) parameter2;
                        if ((oppCode == 6) && (parameter1 == 0)) count = (int) parameter2;

                        break;
                    }
                    case 7:
                    case 8: {
                        parameter1 = inputProgram.get(count + 1);
                        parameter2 = inputProgram.get(count + 2);
                        parameter3 = inputProgram.get(count + 3);

                        if (positionMode1) parameter1 = inputProgram.get((int) parameter1);
                        if (positionMode2) parameter2 = inputProgram.get((int) parameter2);
                        // if (positionMode3) parameter3 = inputProgram.get((int) parameter3);

                        if (relativeMode1) {
                            int newIndex = ((int)(parameter1 + relativeBias));
                            if (newIndex >= 0) parameter1 = inputProgram.get(newIndex);
                        }

                        if (relativeMode2) {
                            int newIndex = ((int)(parameter2 + relativeBias));
                            if (newIndex >= 0) parameter2 = inputProgram.get(newIndex);
                        }

                        if (relativeMode3) {
                            int newIndex = ((int)(parameter3 + relativeBias));
                            if (newIndex >= 0) parameter3 = newIndex;
                        }

                        boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                        if (conditionMet) {
                            inputProgram.set((int) parameter3, (long) 1);
                        } else {
                            inputProgram.set((int) parameter3, (long) 0);
                        }

                        count += 4;
                        break;
                    }
                    case 9: {
                        parameter1 = inputProgram.get(count + 1);

                        if (positionMode1) parameter1 = inputProgram.get((int) parameter1);

                        if (relativeMode1) {
                            int newIndex = (int) (relativeBias + parameter1);
                            if (newIndex >= 0) parameter1 = inputProgram.get(newIndex);
                        }
                        relativeBias += parameter1;
                        count+=2;
                    }
                }
            }
            return result;
        }
    }


    @Override
    public String part1(List<String> input) {
        List<Long> longInput = input.stream().
                map(Long::parseLong).collect(Collectors.toList());


        IntegerComputer ic = new IntegerComputer(longInput, 1);
        initialInput = 1;
        List<Long> result = ic.runIntegerComputer();

        System.out.println(result);

        return input.isEmpty() ? "" : String.valueOf(result);
    }

    @Override
    public String part2(List<String> input) {List<Long> longInput = input.stream().
            map(Long::parseLong).collect(Collectors.toList());


        IntegerComputer ic = new IntegerComputer(longInput, 2);
        initialInput = 2;
        List<Long> result = ic.runIntegerComputer();

        System.out.println(result);

        return input.isEmpty() ? "" : String.valueOf(result);
    }
}
