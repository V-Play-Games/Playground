package net.vpg;

import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

public class Calculator {
    private static final DoubleBinaryOperator O1 = Double::sum;
    private static final DoubleBinaryOperator O2 = (x, y) -> x * y;
    private static final DoubleBinaryOperator O3 = Math::pow;
    private static final Pattern tokens = Pattern.compile("\\(|\\)|\\+|-|\\*|/|\\^|e|pi|cosec|sec|cot|sin|cos|tan|abs|log|[\\d.]+");
    private String s = "";
    private Matcher m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter expression:");
        System.out.println(new Calculator().evaluate(in.nextLine()));
    }

    public double evaluate(String src) {
        m = tokens.matcher(src);
        return list(O1, false, null);
    }

    private String nextToken() {
        return !s.isEmpty() ? s + (s = "") : m.find() ? m.group() : "";
    }

    private double next(DoubleStream.Builder l, double d) {
        if (l != null)
            l.add(d);
        String token = nextToken();
        if (Pattern.matches("[\\d.]+", token))
            return Double.parseDouble(token);
        switch (token) {
            case "(":
                return list(O1, true, null);
            case "pi":
                return Math.PI;
            case "e":
                return Math.exp(1);
        }
        double n = next(null, 0);
        switch (token) {
            case "-":
                return -n;
            case "sin":
                return Math.sin(n);
            case "cos":
                return Math.cos(n);
            case "tan":
                return Math.tan(n);
            case "cosec":
                return 1 / Math.sin(n);
            case "sec":
                return 1 / Math.cos(n);
            case "cot":
                return 1 / Math.tan(n);
            case "log":
                return Math.log(n);
            case "abs":
                return Math.abs(n);
        }
        return n;
    }

    private double list(DoubleBinaryOperator op, boolean b, Double d) {
        return processNext(op, DoubleStream.builder(), b, d == null ? next(null, 0) : d).build().reduce(op).orElse(0D);
    }

    private DoubleStream.Builder processNext(DoubleBinaryOperator op, DoubleStream.Builder l, boolean b, double d) {
        s = (s = nextToken()).equals(")") && b ? "" : s;
        char c = s.isEmpty() ? '\0' : s.charAt(0);
        if (c == '^')
            return processNext(op, l, b, list(O3, false, op != O3 ? d : next(l, d)));
        else if ((c == '/' || c == '*') && op != O3)
            return processNext(op, l, b,
                op != O2
                    ? list(O2, false, d)
                    : Math.pow(next(l, d), c == '*' ? 1 : -1));
        else if ((c == '+' || c == '-') && op == O1)
            return processNext(op, l, b, next(l, d));
        l.add(d);
        return l;
    }
}
