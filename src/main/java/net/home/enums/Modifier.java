package net.home.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.home.util.ConstantStore.WHITESPACE;

public enum Modifier {
    STATIC("static"),
    FINAL("final");

    private final String value;

    Modifier(String value) {
        this.value = value;
    }

    public static String toString(Modifier modifier) {
        if (modifier == null) return "";

        switch (modifier) {
            case STATIC:
            case FINAL:
                return modifier.value + WHITESPACE;
            default:
                return "";
        }
    }

    public static int compare(Modifier m1, Modifier m2) {
        return Integer.compare(getModifierWeight(m1), getModifierWeight(m2));
    }

    private static int getModifierWeight(Modifier modifier) {
        if (modifier == null) return -1;

        switch (modifier) {
            case STATIC:
                return 2;
            case FINAL:
                return 1;
            default:
                return 0;
        }
    }

    public static List<Modifier> rightSequence(List<Modifier> list) {
        if (list.size() > 2) {
            throw new IllegalArgumentException("The number of modifiers should not exceed two");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("Modifier can't be null in list");
        }
        if (list.size() == 1 || list.isEmpty()) {
            return list;
        }
        if (list.get(0) == list.get(1)) {
            throw new IllegalArgumentException("Еhere cannot be two identical modifiers in the list");
        }
        return new ArrayList<>(Arrays.asList(Modifier.values()));
    }
}
