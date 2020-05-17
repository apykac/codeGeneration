package net.homecredit.util;

public class ConstantStore {
    public static final String OPERATION_END = ";";
    public static final String WHITESPACE = " ";
    public static final String TAB = "\t";
    public static final String OPENING_BRACKET = "(";
    public static final String CLOSING_BRACKET = ")";
    public static final String OPENING_BRACE = "{";
    public static final String CLOSING_BRACE = "}";
    public static final String COMMA = ",";
    public static final String DOUBLE_QUOTES = "\"";

    private ConstantStore() {
    }

    public static String getIndent(int countOfTabs) {
        return TAB.repeat(Math.max(0, countOfTabs));
    }
}
