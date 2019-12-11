package aoc.helper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IntegerComputerV1 {
    private int number;
    private int count;
    private int oppCode;
    private long parameter1;
    private long parameter2;
    private long parameter3;
    private List<Long> result;
    private long relativeBias;
    private List<Long> inputProgram ;
    private long inputParameter;
    private boolean positionMode1;
    private boolean positionMode2;
    private boolean positionMode3;
    private boolean immediateMode1;
    private boolean immediateMode2;
    private boolean immediateMode3;
    private boolean relativeMode1;
    private boolean relativeMode2;
    private boolean relativeMode3;
    private boolean halted;
    private boolean hasOutput;
    private boolean haltOnOutput;

    public IntegerComputerV1(List<Long> inputProgram) {
        this.count = 0;
        this.oppCode = 0;
        this.result = new ArrayList<>();
        this.relativeBias = 0;
        this.inputProgram = inputProgram;
        this.positionMode1 = false;
        this.positionMode2 = false;
        this.positionMode3 = false;
        this.immediateMode1 = false;
        this.immediateMode2 = false;
        this.immediateMode3 = false;
        this.relativeMode1 = false;
        this.relativeMode2 = false;
        this.relativeMode3 = false;
        this.hasOutput = false;
        this.halted = false;
        this.haltOnOutput = false;
        for (int x = inputProgram.size(); x < 100000; x++) inputProgram.add((long) 0);
    }

    private int getOppCode() {
        return (int) ((((inputProgram.get(count) / 10) % 10) * 10) + (inputProgram.get(count) % 10));
    }

    private void setModes() {
        this.positionMode1 = (0 == ( inputProgram.get(count) / 100) % 10);
        this.positionMode2 = (0 == ( inputProgram.get(count) / 1000) % 10);
        this.positionMode3 = (0 == ( inputProgram.get(count) / 10000) % 100);
        this.immediateMode1 = (1 == ( inputProgram.get(count) / 100) % 10);
        this.immediateMode2 = (1 == ( inputProgram.get(count) / 1000) % 10);
        this.immediateMode3 = (1 == ( inputProgram.get(count) / 10000) % 100);
        this.relativeMode1 = (2 == ( inputProgram.get(count) / 100) % 10);
        this.relativeMode2 = (2 == ( inputProgram.get(count) / 1000) % 10);
        this.relativeMode3 = (2 == ( inputProgram.get(count) / 10000) % 10);
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

    private long checkMode(long parameter, boolean positionMode , boolean relativeMode) {
        long result = parameter;
        if (positionMode) result = getParameter(parameter);
        if (relativeMode) {
            long newIndex = parameter + relativeBias;
            if (newIndex >= 0) {
                result = getParameter(newIndex);
            }
        }
        return result;
    }

    private long checkIndex(long parameter, boolean positionMode, boolean relativeMode) {
        long result = parameter;
        if (positionMode) result = getParameter(parameter);
        if (relativeMode) {
            long newIndex = parameter + relativeBias;
            if (newIndex >= 0) {
                result = newIndex;
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

    private void setParameter(int index, long value){
        inputProgram.set(index, value);
    }

    private void setParameter(long index, long value){
        inputProgram.set((int) index, value);
    }

    public List<Long> runIntegerComputer() {
        hasOutput = false;
        while ( ( oppCode != 99 ) && (!(halted)) ) {
            oppCode = getOppCode();
            setModes();
            switch (oppCode) {
                case 1: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                    parameter3 = checkIndex(getParameter(count + 3), relativeMode3);
                    setParameter(parameter3, parameter1 + parameter2);
                    count += 4;
                    break;
                }
                case 2: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                    parameter3 = checkIndex(getParameter(count + 3), relativeMode3);
                    setParameter(parameter3, parameter1 * parameter2);
                    count += 4;
                    break;
                }
                case 3: {
                    parameter1 = checkIndex(getParameter(count + 1), positionMode1, relativeMode1);
                    setParameter(parameter1, inputParameter);
                    count += 2;
                    break;
                }
                case 4: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    result.add(parameter1);
                    hasOutput = true;
                    count += 2;
                    break;
                }
                case 5: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                    count += 3;
                    if (parameter1 != 0) count = (int) parameter2;
                    break;
                }
                case 6: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                    count += 3;
                    if ((oppCode == 6) && (parameter1 == 0)) count = (int) parameter2;
                    break;
                }
                case 7: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                    parameter3 = checkIndex(getParameter(count + 3), relativeMode3);
                    if (parameter1 < parameter2) {
                        setParameter(parameter3, 1);
                    } else{
                        setParameter(parameter3, 0);
                    }
                    count += 4;
                    break;
                }
                case 8: {
                    parameter1 = checkMode(getParameter(count + 1), positionMode1, relativeMode1);
                    parameter2 = checkMode(getParameter(count + 2), positionMode2, relativeMode2);
                    parameter3 = checkIndex(getParameter(count + 3), relativeMode3);
                    if (parameter1 == parameter2) {
                        setParameter(parameter3,1);
                    } else {
                        setParameter(parameter3,0);
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
                default: {
                    halted = true;
                    break;
                }
            }
        }
        return result;
    }
}

