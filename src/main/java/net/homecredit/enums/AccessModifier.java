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
}
