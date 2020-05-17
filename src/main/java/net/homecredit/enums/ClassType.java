package net.homecredit.enums;

import static net.homecredit.util.ConstantStore.WHITESPACE;

public enum ClassType {
    INTERFACE("interface"),
    ABSTRACT_CLASS("abstract class"),
    DEFAULT("class"),
    ENUM("enum"),
    ANNOTATION("@interface");

    private final String value;

    ClassType(String value) {
        this.value = value;
    }

    public static String toString(ClassType classType) {
        if (classType == null) return "";

        switch (classType) {
            case DEFAULT:
            case ENUM:
            case INTERFACE:
            case ANNOTATION:
            case ABSTRACT_CLASS:
                return classType.value + WHITESPACE;
            default:
                return "";
        }
    }
}
