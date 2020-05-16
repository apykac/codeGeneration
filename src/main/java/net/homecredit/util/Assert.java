package net.homecredit.util;

public class Assert {

    public void notNull(Object o) {
        notNull("Argument must not be null", o);
    }

    public void notNull(String message, Object o) {
        if (o == null) throw new IllegalArgumentException(message);
    }
}
