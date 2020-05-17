package net.homecredit.enums;

import static net.homecredit.util.ConstantStore.WHITESPACE;

public enum AccessModifier {
    PRIVATE("private"),
    PROTECTED("protected"),
    DEFAULT(""),
    PUBLIC("public");

    private final String value;

    AccessModifier(String value) {
        this.value = value;
    }

    public static String toString(AccessModifier accessModifier) {
        if (accessModifier == null) return "";

        switch (accessModifier) {
            case PUBLIC:
            case PRIVATE:
            case PROTECTED:
                return accessModifier.value + WHITESPACE;
            case DEFAULT:
            default:
                return "";
        }
    }

    public static int compare(AccessModifier am1, AccessModifier am2) {
        return Integer.compare(getModifierWeight(am1), getModifierWeight(am2));
    }

    private static int getModifierWeight(AccessModifier accessModifier) {
        if (accessModifier == null) return -1;

        switch (accessModifier) {
            case PRIVATE:
                return 4;
            case DEFAULT:
                return 3;
            case PROTECTED:
                return 2;
            case PUBLIC:
                return 1;
            default:
                return -1;
        }
    }
}
