package aoc.day07;

import aoc.Day;
import aoc.helper.StringHelper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day07 implements Day {

    @Data
    class Amplifier {
        private int number;
        private int count;
        private int oppCode;
        private int parameter1;
        private int parameter2;
        private int parameter3;
        private int result;
        private boolean phase;
        private Integer[] inputProgram ;
        private int phaseSetting;
        private boolean hasOutputted;
        private boolean halted;

        public Amplifier(Integer[] inputProgram, int number) {
            this.number = number;
            this.count = 0;
            this.oppCode = 0;
            this.result = 0;
            this.inputProgram = inputProgram;
            this.phase = true;
            this.hasOutputted = false;
            this.halted = false;

        }

        public Integer runAmplifier(int input) {
            while ((oppCode != 99) && (!hasOutputted)) {
                oppCode = inputProgram[count] % 10;
                oppCode = (oppCode == 9) ? 99 : oppCode;
                boolean positionMode1 = (0 == ( inputProgram[count] / 100) % 10);
                boolean positionMode2 = (0 == ( inputProgram[count] / 1000) % 10);

                switch (oppCode) {
                    case 99:
                        halted = true;
                        break;
                    case 1:
                    case 2: {
                        parameter1 = inputProgram[count + 1];
                        parameter2 = inputProgram[count + 2];
                        parameter3 = inputProgram[count + 3];
                        if (positionMode1) parameter1 = inputProgram[parameter1];
                        if (positionMode2) parameter2 = inputProgram[parameter2];

                        inputProgram[parameter3] = (oppCode == 1) ? parameter1 + parameter2 : parameter1 * parameter2;
                        count += 4;
                        break;
                    }
                    case 3: {
                        parameter1 = inputProgram[count + 1];
                        inputProgram[parameter1] = (phase) ? phaseSetting : input;
                        phase = false;
                        count += 2;
                        break;
                    }
                    case 4: {
                        parameter1 = (positionMode1) ?
                                inputProgram[inputProgram[count + 1]] :
                                inputProgram[count + 1];

                        result = parameter1;
                        hasOutputted = true;
                        count += 2;
                        break;
                    }
                    case 5:
                    case 6: {
                        parameter1 = inputProgram[count + 1];
                        parameter2 = inputProgram[count + 2];

                        if (positionMode1) parameter1 = inputProgram[parameter1];
                        if (positionMode2) parameter2 = inputProgram[parameter2];

                        count = (((oppCode == 5) && (parameter1 != 0)) || ((oppCode == 6) && (parameter1 == 0))) ? parameter2 : count + 3;
                        break;
                    }
                    case 7:
                    case 8: {
                        parameter1 = inputProgram[count + 1];
                        parameter2 = inputProgram[count + 2];
                        parameter3 = inputProgram[count + 3];

                        if (positionMode1) parameter1 = inputProgram[parameter1];
                        if (positionMode2) parameter2 = inputProgram[parameter2];

                        boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                        inputProgram[parameter3] = (conditionMet) ? 1 : 0;

                        count += 4;
                        break;
                    }
                }
            }
            return result;
        }
    }

    private boolean isSequence01234(String s) {
        return  (StringHelper.countChars(s,'0') == 1) &&
                (StringHelper.countChars(s,'1') == 1) &&
                (StringHelper.countChars(s,'2') == 1) &&
                (StringHelper.countChars(s,'3') == 1) &&
                (StringHelper.countChars(s,'4') == 1) ;
    }

    private List<String> generateInputSequences01234 (){
        return IntStream.rangeClosed(0, 44444).boxed().map(StringHelper::intToString5).
                filter(this::isSequence01234).collect(Collectors.toList());
    }

    private boolean isSequence56789(String s) {
        return  (StringHelper.countChars(s,'5') == 1) &&
                (StringHelper.countChars(s,'6') == 1) &&
                (StringHelper.countChars(s,'7') == 1) &&
                (StringHelper.countChars(s,'8') == 1) &&
                (StringHelper.countChars(s,'9') == 1) ;
    }

    private List<String> generateInputSequences56789 (){
        return IntStream.rangeClosed(55555, 99999).boxed().map(StringHelper::intToString5).
                filter(this::isSequence56789).collect(Collectors.toList());
    }

    @Override
    public String part1(List<String> input) {
        int maxValue = 0;

        List<Integer> integerProgram = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (String inputSequence : generateInputSequences01234()) {
            List<Amplifier> amplifiers = new ArrayList<>();

            for (int x=0; x < 5; x++) {
                Amplifier amplifier = new Amplifier(integerProgram.toArray(new Integer[0]), x);
                amplifiers.add(amplifier);
            }

            Integer output = 0;
            for (int y=0; y<5; y++) {
                amplifiers.get(y).setPhaseSetting(Integer.parseInt(String.valueOf(inputSequence.charAt(y))));
                output = amplifiers.get(y).runAmplifier(output);
            }
            if (output > maxValue) maxValue = output;
        }
        return input.isEmpty() ? "" : String.valueOf(maxValue);
    }

    @Override
    public String part2(List<String> input) {
        int maxValue = 0;

        List<Integer> integerProgram = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (String inputSequence : generateInputSequences56789()) {
            List<Amplifier> amplifiers = new ArrayList<>();
            for (int x=0; x < 5; x++) {
                Amplifier amplifier = new Amplifier(integerProgram.toArray(new Integer[0]), x);
                amplifiers.add(amplifier);
            }

            boolean stop = (amplifiers.get(0)).isHalted();
            Integer output = 0;
            while (!stop) {
                stop = true;
                for (int y=0; y<5; y++)
                    if (!(amplifiers.get(y).isHalted())) {
                        amplifiers.get(y).setPhaseSetting(Integer.parseInt(String.valueOf(inputSequence.charAt(y))));
                        output = amplifiers.get(y).runAmplifier(output);
                        amplifiers.get(y).setHasOutputted(false);
                        stop = false;
                    }
            }
            if (output > maxValue) maxValue = output;
        }
        return input.isEmpty() ? "" : String.valueOf(maxValue);
    }
}
