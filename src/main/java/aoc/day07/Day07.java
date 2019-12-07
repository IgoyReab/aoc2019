package aoc.day07;

import aoc.Day;
import aoc.helper.StringHelper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
        private boolean positionMode1;
        private boolean positionMode2;
        private boolean phase;
        private String[] inputProgram ;
        private int phaseSetting;
        private boolean hasOutputted;
        private boolean halted;

        public Amplifier(String[] inputProgram, int number) {
            this.number = number;
            this.count = 0;
            this.oppCode = 0;
            this.result = 0;
            this.positionMode1 = true;
            this.positionMode2 = true;
            this.inputProgram = inputProgram;
            this.phase = true;
            this.hasOutputted = false;
            this.halted = false;

        }

        public Integer runAmplifier(int input) {
            while ((oppCode != 99) && (!hasOutputted)) {
                switch (inputProgram[count].length()) {
                    case 1:
                    case 2: {
                        oppCode = Integer.parseInt(inputProgram[count]);
                        positionMode2 = true;
                        positionMode1 = true;
                        break;
                    }
                    case 3: {
                        oppCode = Integer.parseInt(String.valueOf(inputProgram[count].charAt(2)));
                        positionMode2 = true;
                        positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                        break;
                    }
                    case 4: {
                        oppCode = Integer.parseInt(String.valueOf(inputProgram[count].charAt(3)));
                        positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                        positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                        break;
                    }
                    case 5: {
                        oppCode = Integer.parseInt(String.valueOf(inputProgram[count].charAt(4)));
                        positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                        positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(2))));
                        break;
                    }
                }

                if (oppCode == 9) oppCode = 99;

                switch (oppCode) {
                    case 99:
                        halted = true;
                        break;
                    case 1:
                    case 2: {
                        parameter1 = Integer.parseInt(inputProgram[count + 1]);
                        parameter2 = Integer.parseInt(inputProgram[count + 2]);
                        parameter3 = Integer.parseInt(inputProgram[count + 3]);
                        if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                        if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                        inputProgram[parameter3] = (oppCode == 1) ? String.valueOf(parameter1 + parameter2) : String.valueOf(parameter1 * parameter2);
                        count += 4;
                        break;
                    }
                    case 3: {
                        parameter1 = Integer.parseInt(inputProgram[count + 1]);
                        inputProgram[parameter1] = (phase) ? String.valueOf(phaseSetting) : String.valueOf(input);
                        phase = false;
                        count += 2;
                        break;
                    }
                    case 4: {
                        parameter1 = (positionMode1) ?
                                Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[count + 1])]) :
                                Integer.parseInt(inputProgram[count + 1]);

                        result = parameter1;
                        hasOutputted = true;
                        count += 2;
                        break;
                    }
                    case 5:
                    case 6: {
                        parameter1 = Integer.parseInt(inputProgram[count + 1]);
                        parameter2 = Integer.parseInt(inputProgram[count + 2]);

                        if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                        if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                        count = (((oppCode == 5) && (parameter1 != 0)) || ((oppCode == 6) && (parameter1 == 0))) ? parameter2 : count + 3;
                        break;
                    }
                    case 7:
                    case 8: {
                        parameter1 = Integer.parseInt(inputProgram[count + 1]);
                        parameter2 = Integer.parseInt(inputProgram[count + 2]);
                        parameter3 = Integer.parseInt(inputProgram[count + 3]);

                        if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                        if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                        boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                        inputProgram[parameter3] = (conditionMet) ? String.valueOf(1) : String.valueOf(0);

                        count += 4;
                        break;
                    }
                }
            }
            return result;
        }

    }


    private Integer runProgram(String[] inputProgram, int phaseSetting, int inputSetting){
        int count = 0;
        int oppCode = 0;
        int parameter1;
        int parameter2;
        int parameter3;
        int result = 0;
        boolean positionMode1 = true;
        boolean positionMode2 = true;

        boolean phase = true;

        while (oppCode != 99) {
            switch (inputProgram[count].length()) {
                case 1:
                case 2:    {
                    oppCode = Integer.parseInt(inputProgram[count]);
                    positionMode2 = true;
                    positionMode1 = true;
                    break;
                }
                case 3 : {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(2)));
                    positionMode2 = true;
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    break;
                }
                case 4: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(3)));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(0))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    break;
                }
                case 5: {
                    oppCode =  Integer.parseInt(String.valueOf(inputProgram[count].charAt(4)));
                    positionMode2 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(1))));
                    positionMode1 = (0 == Integer.parseInt(String.valueOf(inputProgram[count].charAt(2))));
                    break;
                }
            }

            if (oppCode == 9) oppCode = 99;

            switch (oppCode){
                case 1 :
                case 2 : {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    parameter2 = Integer.parseInt(inputProgram[count + 2]);
                    parameter3 = Integer.parseInt(inputProgram[count + 3]);
                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                    inputProgram[parameter3] = (oppCode == 1) ? String.valueOf(parameter1 + parameter2) : String.valueOf(parameter1 * parameter2);
                    count += 4;
                    break;
                }
                case 3: {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    inputProgram[parameter1] = (phase) ? String.valueOf(phaseSetting) : String.valueOf(inputSetting);
                    phase = false;
                    count += 2;
                    break;
                }
                case 4: {
                    parameter1 = (positionMode1) ?
                            Integer.parseInt(inputProgram[Integer.parseInt(inputProgram[count + 1])]) :
                            Integer.parseInt(inputProgram[count + 1]);
                    result = parameter1;
                    count += 2;
                    break;
                }
                case 5:
                case 6: {
                    parameter1 = Integer.parseInt(inputProgram[count + 1]);
                    parameter2 = Integer.parseInt(inputProgram[count + 2]);

                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                    count = (((oppCode == 5) && (parameter1 != 0)) || ((oppCode == 6 ) && (parameter1 == 0))) ? parameter2 : count + 3;
                    break;
                }
                case 7:
                case 8: {
                    parameter1 =  Integer.parseInt(inputProgram[count + 1]);
                    parameter2 =  Integer.parseInt(inputProgram[count + 2]);
                    parameter3 =  Integer.parseInt(inputProgram[count + 3]);

                    if (positionMode1) parameter1 = Integer.parseInt(inputProgram[parameter1]);
                    if (positionMode2) parameter2 = Integer.parseInt(inputProgram[parameter2]);

                    boolean conditionMet = (oppCode == 7) ? (parameter1 < parameter2) : (parameter1 == parameter2);
                    inputProgram[parameter3] = (conditionMet) ? String.valueOf(1) : String.valueOf(0);

                    count += 4;
                    break;
                }
            }
        }
        return result;
    }

    private List<String> generateInputSequencesPart1 (){
        List<String> result = new ArrayList<>();
        for (int a=0; a <= 4; a++) {
            for (int b=0; b <= 4; b++) {
                for (int c=0; c <= 4; c++) {
                    for (int d=0; d <= 4; d++) {
                        for (int e=0; e <= 4; e++) {
                            String s = a + String.valueOf(b) + c + d + e;
                            if ((StringHelper.countChars(s,'0') == 1) &&
                                (StringHelper.countChars(s,'1') == 1) &&
                                (StringHelper.countChars(s,'2') == 1) &&
                                (StringHelper.countChars(s,'3') == 1) &&
                                (StringHelper.countChars(s,'4') == 1)) result.add(s);
                        }
                    }
                }
            }
        }
        return result;
    }

    private List<String> generateInputSequencesPart2 (){
        List<String> result = new ArrayList<>();
        for (int a=5; a <= 9; a++) {
            for (int b=5; b <= 9; b++) {
                for (int c=5; c <= 9; c++) {
                    for (int d=5; d <= 9; d++) {
                        for (int e=5; e <= 9; e++) {
                            String s =  String.valueOf(a) + b + c + d + e;
                            if ((StringHelper.countChars(s,'5') == 1) &&
                                    (StringHelper.countChars(s,'6') == 1) &&
                                    (StringHelper.countChars(s,'7') == 1) &&
                                    (StringHelper.countChars(s,'8') == 1) &&
                                    (StringHelper.countChars(s,'9') == 1)) result.add(s);
                        }
                    }
                }
            }
        }
        return result;
    }




    @Override
    public String part1(List<String> input) {
        int maxValue = 0;

        for (String inputSequence : generateInputSequencesPart1()) {
            Integer output = 0;
            for (int i = 0; i < inputSequence.length(); i++) {
                output = runProgram(input.toArray(new String[0]), Integer.parseInt(String.valueOf(inputSequence.charAt(i))), output);
            }
            if (output > maxValue) maxValue = output;
        }

        return input.isEmpty() ? "" : String.valueOf(maxValue);
    }

    @Override
    public String part2(List<String> input) {
        int maxValue = 0;


        for (String inputSequence : generateInputSequencesPart2()) {
            List<Amplifier> amplifiers = new ArrayList<>();
            for (int x=0; x < 5; x++) {
                Amplifier amplifier = new Amplifier(input.toArray(new String[0]), x);
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
