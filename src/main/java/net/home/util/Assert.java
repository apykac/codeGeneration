package net.home.util;

public class Assert {
    private Assert() {
    }

    public static void notNull(Object o) {
        notNull("Argument must not be null", o);
    }

    public static void notNull(String message, Object o) {
        if (o == null) throw new IllegalArgumentException(message);
    }
}
