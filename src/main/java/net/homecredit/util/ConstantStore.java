package net.homecredit.util;

public class ConstantStore {
    public static final String OPERATION_END = ";";
    public static final String WHITESPACE = " ";
    public static final String TAB = "\t";

    private ConstantStore() {
    }

    public static String getIndent(int countOfTabs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < countOfTabs; i++) builder.append(TAB);
        return builder.toString();
    }
}
