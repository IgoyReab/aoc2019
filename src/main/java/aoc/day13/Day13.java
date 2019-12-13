package aoc.day13;

import aoc.Day;
import aoc.intcodeComputer.IntcodeComputer;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 implements Day {

    private Integer getParameter(List<Integer> input, int a, int b) {
        for (int x=0; x<input.size(); x+=3) {
            if ((input.get(x) != -1) && (input.get(x) == a)  && (input.get(x+1) == b)) {
                return input.get(x+2);
            }
        }
        return null;
    }


    public void printGame(List<Integer> result) {
        int xCount = 0;
        int yCount = 0;

        System.out.println(result);

        for (int x=0; x<result.size(); x+=3) {
            if (xCount < result.get(x)) xCount = result.get(x);
        }
        for (int y=1; y<result.size(); y+=3) {
            if (yCount < result.get(y)) yCount = result.get(y);
        }

        System.out.println("x = " + xCount + " y = " + yCount);


        Integer[][] grid = new Integer[xCount+1][yCount+1];

        for (int b = 0; b<=yCount; b++) {
            for (int a = 0; a<=xCount; a++) {
                grid[a][b] = 0;
            }
        }

        for (int a = 0; a<=xCount; a++) {
            for (int b = 0; b<=yCount; b++) {
                grid[a][b] = getParameter(result, a, b);
            }
        }

        for (int b = 0; b<=yCount; b++) {
            for (int a = 0; a<=xCount; a++) {
                if (a == 0) System.out.println();
                if (grid[a][b] == null) System.out.println(" null at " + a + " " + b);
                switch (grid[a][b]) {
                    case 0: {
                        System.out.print(" ");
                        break;
                    }
                    case 1: {
                        System.out.print("W");
                        break;
                    }
                    case 2: {
                        System.out.print("B");
                        break;
                    }
                    case 3: {
                        System.out.print("P");
                        break;
                    }
                    case 4: {
                        System.out.print("o");
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + grid[a][b]);
                }
            }
        }
    }

    @Override


    public String part1(List<String> input) {
        List<Long> longInput =  Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        IntcodeComputer computer = new IntcodeComputer(longInput);
        computer.runProgram();

        Map<Position, Tile> board = new HashMap<>();
        addTilesToBoard(computer.getOutputs(), board, null);

        long numBlocks =  board.values().stream().filter(x -> x == Tile.BLOCK).count();
        System.out.println("num of blocks : " +  numBlocks);

        return input.isEmpty() ? "" : String.valueOf(numBlocks);
    }
//    public String part1(List<String> input) {
//        List<Long> longInput = input.stream().
//                map(Long::parseLong).collect(Collectors.toList());
//
//        IntegerComputerV3 arcade = new IntegerComputerV3(longInput);
//        List<Integer> result = arcade.runIntegerComputer();
//        printGame(result);
//
//        long count = 0;
//        for (int x=2; x<result.size(); x+=3) {
//            if (result.get(x) == 2) count++;
//        }
//
//
//        return input.isEmpty() ? "" : String.valueOf(count);
//    }



    @Override
    public String part2(List<String> input) {
        List<Long> longInput =  Arrays.stream(input.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        longInput.set(0,(long)2);
        IntcodeComputer computer = new IntcodeComputer(longInput);
        ScoreBoard scoreBoard = new ScoreBoard();
        Map<Position, Tile> board = new HashMap<>();
        boolean notDone = true;
        while(notDone) {
            String exitCode = computer.runProgram();

            addTilesToBoard(computer.getOutputs(), board, scoreBoard);

            boolean blocksAllBroken = board.values().stream().noneMatch(x -> x == Tile.BLOCK);
            boolean systemExit = exitCode.equals("EXITED");

            if(systemExit && !blocksAllBroken) {
                throw new RuntimeException("EXITED BUT NOT BROKEN");
            }
            else if (blocksAllBroken) {
                notDone = false;
            }
            else {
                computer.addInput(findInputForComputer(board));
            }
        }

        System.out.println("Final Score After All blocks Are Broken: " + scoreBoard.getScore());

        return input.isEmpty() ? "" : String.valueOf(scoreBoard.getScore());
    }

    private static long findInputForComputer(Map<Position, Tile> board) {
        long paddleXPosition = board.entrySet().stream().filter(positionTileEntry -> positionTileEntry.getValue() == Tile.HORIZONTAL_PADDLE).map(entry -> entry.getKey().x).findFirst().get();
        long ballXPosition = board.entrySet().stream().filter(positionTileEntry -> positionTileEntry.getValue() == Tile.BALL).map(entry -> entry.getKey().x).findFirst().get();

        if(paddleXPosition < ballXPosition) {
            return 1;
        }
        else if (paddleXPosition > ballXPosition){
            return -1;
        }
        else {
            return 0;
        }
    }


    public static void addTilesToBoard(Queue<Long> tiles, Map<Position, Tile> board, ScoreBoard scoreBoard) {
        while(tiles.size() > 0) {
            Position position = new Position(tiles.remove(), tiles.remove());
            if(position.x != -1) {
                Tile tile = Tile.getFromId(tiles.remove());
                board.put(position, tile);
            } else {
                scoreBoard.setScore(tiles.remove());
            }
        }
    }

    public static class ScoreBoard{
        long score = 0;
        public void setScore(long score) {
            this.score = score;
        }

        public long getScore() {
            return score;
        }

    }

    public enum Tile{
        EMPTY(0),
        WALL(1),
        BLOCK(2),
        HORIZONTAL_PADDLE(3),
        BALL(4);

        private long id;
        Tile(long id) {
            this.id = id;
        }

        public static Tile getFromId(long idToGet){
            final Tile tile = Arrays.stream(Tile.values()).filter(x -> x.id == idToGet).findFirst().get();
            return tile;
        }

    }

    public static class Position {
        long x;
        long y;

        Position(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Position other = (Position) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Math.toIntExact(x + y);
        }

        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }
}
