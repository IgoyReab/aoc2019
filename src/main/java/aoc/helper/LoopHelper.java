package aoc.helper;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LoopHelper {

    public static void loop(Integer[] values, Consumer<Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
                consumer.accept(i);
        }
    }

    public static void loop(Character[] values, Consumer<Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
                consumer.accept(i);
        }
    }

    public static void loop(String[] values, Consumer<Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            consumer.accept(i);
        }
    }

    public static void loop(float[] values, Consumer<Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            consumer.accept(i);
        }
    }

    public static void loop(long[] values, Consumer<Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            consumer.accept(i);
        }
    }

    public static void nestedLoop(Integer[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void nestedLoop(float[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void nestedLoop(String[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void nestedLoop(long[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }

    public static void nestedLoop(Character[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                consumer.accept(i, k);
            }
        }
    }
}
