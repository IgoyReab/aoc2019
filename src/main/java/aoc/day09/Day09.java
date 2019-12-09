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
            // for (int x = inputProgram.size(); x < 100000; x++) inputProgram.add((long) 0);
        }

        private long getParameter(int index) {
            try {
                return inputProgram.get(index);
            } catch ( IndexOutOfBoundsException e ) {
                return 0;
            }
        }

        private long getParameter(long index) {
            try {
                return inputProgram.get((int) index);
            } catch ( IndexOutOfBoundsException e ) {
                return 0;
            }
        }

        private int getOppcode() {
            return (int) ((((getParameter(count) / 10) % 10) * 10) + (getParameter(count) % 10));
        }

        private long checkMode(long parameter, boolean positionMode , boolean relativeMode) {
            long result = parameter;
            if (positionMode) parameter1 = getParameter(parameter1);
            if (relativeMode) {
                long newIndex = parameter + relativeBias;
                if (newIndex >= 0) {
                    result = getParameter(newIndex);
                }
            }
            return result;
        }

        private long checkMode(long parameter, boolean relativeMode) {
            long result = parameter;

            if (relativeMode) {
                long newIndex = parameter + relativeBias;
                if (newIndex >= 0) {
                    result = getParameter(newIndex);
                }
            }
            return result;
        }

        private long checkIndex(long parameter, boolean relativeMode) {
            long result = parameter;

            if (relativeMode) {
                long newIndex = parameter + relativeBias;
                if (newIndex >= 0) {
                    result = newIndex;
                }
            }
            return result;
        }

        public List<Long> runIntegerComputer() {
            while ( oppCode != 99 ) {
                oppCode = getOppcode();

                boolean positionMode1 = (0 == ( getParameter(count) / 100) % 10);
                boolean positionMode2 = (0 == ( getParameter(count) / 1000) % 10);

                boolean relativeMode1 = (2 == ( getParameter(count) / 100) % 10);
                boolean relativeMode2 = (2 == ( getParameter(count) / 1000) % 10);
                boolean relativeMode3 = (2 == ( getParameter(count) / 10000) % 10);

                switch (oppCode) {
                    case 1:
                    case 2: {
                        parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                        parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                        parameter3 = checkIndex(getParameter(count + 3), relativeMode3);
                        if (oppCode == 1) {
                            try {
                                inputProgram.set((int) parameter3, parameter1 + parameter2);
                            } catch ( IndexOutOfBoundsException e ) {
                                for (int x = inputProgram.size(); x < parameter3; x++) {
                                    inputProgram.add(x, (long) 0);
                                }
                                inputProgram.add((int) parameter3, parameter1 + parameter2);
                            }
                        } else {
                            try {
                                inputProgram.set((int) parameter3, parameter1 * parameter2);
                            } catch ( IndexOutOfBoundsException e ) {
                                for (int x = inputProgram.size(); x < parameter3; x++) inputProgram.add(x, (long) 0);
                                inputProgram.add((int) parameter3, parameter1 * parameter2);
                            }
                        }
                        count += 4;
                        break;
                    }

                    case 3: {
                        parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                        try {
                            inputProgram.set((int) parameter1, initialInput);
                        } catch (IndexOutOfBoundsException e ) {
                            for (int x = inputProgram.size(); x < parameter1; x++) inputProgram.add(x, (long) 0);
                            inputProgram.add((int) parameter1, initialInput);
                        }
                        count += 2;
                        break;
                    }

                    case 4: {
                        result.add(checkMode(getParameter(count + 1), positionMode1, relativeMode1));
                        count += 2;
                        break;
                    }

                    case 5:
                    case 6: {
                        parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                        parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode1);
                        count += 3;
                        if ((oppCode == 5) && (parameter1 != 0)) count = (int) parameter2;
                        if ((oppCode == 6) && (parameter1 == 0)) count = (int) parameter2;
                        break;
                    }

                    case 7:
                    case 8: {
                        parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                        parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                        parameter3 = checkIndex(getParameter(count + 3), relativeMode3);
                        boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                        if (conditionMet) {
                            try {
                                inputProgram.set((int) parameter3, (long) 1);
                            } catch (IndexOutOfBoundsException e ) {
                                for (int x = inputProgram.size(); x < parameter1; x++) inputProgram.add(x, (long) 0);
                                inputProgram.add((int) parameter3, (long) 1);
                            }
                        } else {
                            try {
                                inputProgram.set((int) parameter3, (long) 0);
                            } catch (IndexOutOfBoundsException e ) {
                                for (int x = inputProgram.size(); x < parameter1; x++) inputProgram.add(x, (long) 0);
                                inputProgram.add((int) parameter3, (long) 0);
                            }
                        }
                        count += 4;
                        break;
                    }

                    case 9: {
                        parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                        relativeBias += parameter1;
                        count+=2;
                    }
                    case 99: {
                        break;
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
    public String part2(List<String> input) {
        List<Long> longInput = input.stream().
            map(Long::parseLong).collect(Collectors.toList());

        IntegerComputer ic = new IntegerComputer(longInput, 2);
        initialInput = 2;
        List<Long> result = ic.runIntegerComputer();

        System.out.println(result);

        return input.isEmpty() ? "" : String.valueOf(result);
    }
}
