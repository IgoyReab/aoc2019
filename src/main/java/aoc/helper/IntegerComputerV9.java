package aoc.helper;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class IntegerComputerV9 {
    private int count;
    private int oppCode;
    private long firstParameter;
    private long secondParameter;
    private int relativeBase;
    private List<Long> inputProgram ;
    private boolean halted;
    private boolean hasOutput;
    private boolean haltOnOutput;

    private Queue<Long> inputs = new LinkedList<>();
    private Queue<Long> outputs = new LinkedList<>();

    public IntegerComputerV9(List<Long> inputProgram) {
        this.count = 0;
        this.oppCode = 0;
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

    public void addInput(Long input) {
        inputs.add(input);
    }

    public Queue<Long> getOutputs() {
        return outputs;
    }

    public String runComputer() {

        while (true)  {
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
                    if (inputs.size() == 0) {
                        return "NEED_INPUT";
                    } else {
                            firstParameter = getParameter(1);
                            setParameter(writePosition, inputs.remove());
                            count += 2;
                        }
                    break;
                }
                case 4: {
                    firstParameter = getParameter(1);
                    outputs.add(firstParameter);
                    // System.out.println("Result : " + result);
                    // hasOutput = true;
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
                    break;
                }
                case 99: {
                    return "EXITED";
                }
                default: {
                    throw new IllegalStateException("unknown opcode :" + oppCode);
                }
            }
        }
    }
}
