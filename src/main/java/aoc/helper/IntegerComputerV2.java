package aoc.helper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IntegerComputerV2 {
    private int count;
    private int oppCode;
    private long firstParameter;
    private long secondParameter;
    private List<Long> result;
    private int relativeBase;
    private List<Long> inputProgram ;
    private long inputParameter;
    private boolean halted;
    private boolean hasOutput;
    private boolean haltOnOutput;

    public IntegerComputerV2(List<Long> inputProgram) {
        this.count = 0;
        this.oppCode = 0;
        this.result = new ArrayList<>();
        this.relativeBase = 0;
        this.inputProgram = inputProgram;
        this.hasOutput = false;
        this.halted = false;
        for (int x = inputProgram.size(); x < 100000; x++) inputProgram.add((long) 0);
    }

    private int getOppCode(int x) {
        return (int) ((((inputProgram.get(x) / 10) % 10) * 10) + (inputProgram.get(x) % 10));
    }

    private long getParameter(int parameterNumber) {
        return inputProgram.get(getPosition((parameterNumber)));
    }

    private int getPosition(int parameterNumber) {
        long instruction ;
        long result;
        try {
            instruction = inputProgram.get(count);
        } catch ( IndexOutOfBoundsException e ) {
            return 0;
        }
        int mode = (int) ((instruction / (10 * (int) Math.pow(10, parameterNumber))) % 10);
        switch (mode) {
            case 0 : {
                result =  inputProgram.get((count + parameterNumber));
                break;
            }
            case 1 : {
                result  = count + parameterNumber;
                break;
            }
            case 2 : {
                result = inputProgram.get(count + parameterNumber) + relativeBase;
                break;
            }
            default : {
                throw new IllegalStateException("unknown mode " + mode);
            }
        }
        return (int) result;
    }

    private void setParameter(int index, long value){
        inputProgram.set(index, value);
    }

    public List<Long> runIntegerComputer() {
        hasOutput = false;

        while ((!halted) && (!hasOutput))  {
            oppCode = getOppCode(count);
            switch (oppCode) {
                case 1: {
                    firstParameter = getParameter(1);
                    secondParameter = getParameter(2);
                    int writePosition = getPosition(3);
                    setParameter(writePosition, firstParameter + secondParameter);
                    count += 4;
                    break;
                }
                case 2: {
                    firstParameter = getParameter(1);
                    secondParameter = getParameter(2);
                    int writePosition = getPosition(3);
                    setParameter(writePosition, firstParameter * secondParameter);
                    count += 4;
                    break;
                }
                case 3: {
                    int writePosition = getPosition(1);
                    setParameter(writePosition, inputParameter);
                    System.out.println("Input " + inputParameter + " has been provided");
                    count += 2;
                    break;
                }
                case 4: {
                    firstParameter = getParameter(1);
                    result.add(firstParameter);
                    System.out.println("Result : " + result);
                    hasOutput = true;
                    count += 2;
                    break;
                }
                case 5: {
                    firstParameter = getParameter(1);
                    secondParameter = getParameter(2);
                    if (firstParameter != 0) {
                        count = (int) secondParameter;
                    } else {
                        count += 3;
                    }
                    break;
                }
                case 6: {
                    firstParameter = getParameter(1);
                    secondParameter = getParameter(2);
                    count += 3;
                    if (firstParameter == 0) {
                        count = (int) secondParameter;
                    } else {
                       count += 3;
                    }
                    break;
                }
                case 7: {
                    firstParameter = getParameter(1);
                    secondParameter = getParameter(2);
                    int writePosition = getPosition(3);
                    if (firstParameter < secondParameter) {
                        setParameter(writePosition, 1);
                    } else{
                        setParameter(writePosition, 0);
                    }
                    count += 4;
                    break;
                }
                case 8: {
                    firstParameter = getParameter(1);
                    secondParameter = getParameter(2);
                    int writePosition = getPosition(3);
                    if (firstParameter == secondParameter) {
                        setParameter(writePosition,1);
                    } else {
                        setParameter(writePosition,0);
                    }
                    count += 4;
                    break;
                }
                case 9: {
                    relativeBase += getParameter(1);
                    count+=2;
                }
                default: {
                    throw new IllegalStateException("unknown opcode :" + oppCode);
                }
            }
            halted = (inputProgram.get(count) == 99);
        }
        return result;
    }
}

