package net.vpg;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// -ve => a is faster, +ve => b is faster
public class TimeUtil {
    public static <T1, T2> int calculate(BiConsumer<T1, T2> a, T1 t1, T2 t2) {
        return calculate(a, t1, t2, 1);
    }

    public static <T1, T2> int calculate(BiConsumer<T1, T2> a, T1 t1, T2 t2, int times) {
        return calculate(() -> a.accept(t1, t2), times);
    }

    public static <T> int calculate(Consumer<T> a, T input) {
        return calculate(a, input, 1);
    }

    public static <T> int calculate(Consumer<T> a, T input, int times) {
        return calculate(() -> a.accept(input), times);
    }

    public static int calculate(Runnable a, int times) {
        double score = 0;
        for (int i = 1; i <= times; i++) {
            score += calculate(a);
        }
        return (int) (score / times);
    }

    public static int calculate(Runnable a) {
        long start = System.nanoTime();
        a.run();
        return (int) (System.nanoTime() - start);
    }

    public static <T1, T2> int compare(BiConsumer<T1, T2> a, BiConsumer<T1, T2> b, T1 t1, T2 t2) {
        return compare(a, b, t1, t2, 1);
    }

    public static <T1, T2> int compare(BiConsumer<T1, T2> a, BiConsumer<T1, T2> b, T1 t1, T2 t2, int times) {
        return compare(() -> a.accept(t1, t2), () -> b.accept(t1, t2), times);
    }

    public static <T> int compare(Consumer<T> a, Consumer<T> b, T input) {
        return compare(a, b, input, 1);
    }

    public static <T> int compare(Consumer<T> a, Consumer<T> b, T input, int times) {
        return compare(() -> a.accept(input), () -> b.accept(input), times);
    }

    public static int compare(Runnable a, Runnable b, int times) {
        return (compare0(a, b, times) - compare0(b, a, times)) / 2;
    }

    private static int compare0(Runnable a, Runnable b, int times) {
        double score = 0;
        for (int i = 1; i <= times; i++) {
            score += compare(a, b);
        }
        return (int) (score / times);
    }

    public static int compare(Runnable a, Runnable b) {
        long start = System.nanoTime();
        a.run();
        long mid = System.nanoTime();
        b.run();
        long end = System.nanoTime();
        return (int) (2 * mid - start - end);
    }
}
