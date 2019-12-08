package aoc.helper;

import java.util.function.BiConsumer;

public class LoopHelper {
    public static void loop(Integer[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void loop(float[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void loop(String[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void loop(long[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void loop(Character[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }
}
