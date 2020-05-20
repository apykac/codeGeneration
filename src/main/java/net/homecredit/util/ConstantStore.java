package net.homecredit.util;

public class ConstantStore {
    public static final String LINE_SEPARATOR = "\n";
    public static final String OPERATION_END = ";";
    public static final String WHITESPACE = " ";
    public static final String TAB = "\t";
    public static final String OPENING_BRACKET = "(";
    public static final String CLOSING_BRACKET = ")";
    public static final String OPENING_BRACE = "{";
    public static final String CLOSING_BRACE = "}";
    public static final String COMMA = ",";
    public static final String DOUBLE_QUOTES = "\"";
    public static final String EQUAL_SIGN = "=";

    public static final String VARIABLE_ERROR = "No variables added";
    public static final String ANNOTATION_ERROR = "No annotations added";
    public static final String METHOD_ERROR = "No methods added";

    private ConstantStore() {
    }

    public static String getIndent(int countOfTabs) {
        return TAB.repeat(Math.max(0, countOfTabs));
    }



    public static final String ClASS = "package org.opentest4j;\n" +
            "\n" +
            "import java.io.Serializable;\n" +
            "\n" +
            "/**\n" +
            " * Serializable representation of a value that was used in an assertion.\n" +
            " *\n" +
            " * <p>This class only stores the value if it implements {@link Serializable}.\n" +
            " * In any case, it stores its {@linkplain #getType() runtime type}, {@linkplain\n" +
            " * #getIdentityHashCode() identity hash code}, and {@linkplain\n" +
            " * #getStringRepresentation() string representation} determined via {@link\n" +
            " * String#valueOf(Object)}. If the invocation of {@code String.valueOf(Object)}\n" +
            " * throws an {@link Exception}, the string representation will take the form of\n" +
            " * {@code \"<Exception in toString(): \" + e + \">\"}, where \"e\" is the caught\n" +
            " * exception.\n" +
            " *\n" +
            " * <p>The {@link #toString()} method returns the string representation of the\n" +
            " * value along with its type and identity hash code.\n" +
            " *\n" +
            " * @author Marc Philipp\n" +
            " * @author Sam Brannen\n" +
            " * @since 1.0\n" +
            " * @see System#identityHashCode\n" +
            " */\n" +
            "public final class ValueWrapper implements Serializable {\n" +
            "\n" +
            "\tprivate static final long serialVersionUID = 1L;\n" +
            "\n" +
            "\tprivate static final ValueWrapper nullValueWrapper = new ValueWrapper(null);\n" +
            "\n" +
            "\t/**\n" +
            "\t * Factory for creating a new {@code ValueWrapper} for the supplied {@code value}.\n" +
            "\t *\n" +
            "\t * <p>If the supplied {@code value} is {@code null}, this method will return a\n" +
            "\t * cached {@code ValueWrapper} suitable for all {@code null} values.\n" +
            "\t * If the supplied {@code value} is already an instance of {@link ValueWrapper},\n" +
            "\t * it will be returned as is.\n" +
            "\t *\n" +
            "\t * @param value the value to wrap; may be {@code null}\n" +
            "\t * @return a wrapper for the supplied value; never {@code null}\n" +
            "\t */\n" +
            "\tpublic static ValueWrapper create(Object value) {\n" +
            "\t\tif (value instanceof ValueWrapper)\n" +
            "\t\t\treturn (ValueWrapper) value;\n" +
            "\t\treturn (value == null) ? nullValueWrapper : new ValueWrapper(value);\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Factory for creating a new {@code ValueWrapper} for the supplied {@code value}\n" +
            "\t * using the supplied custom {@code stringRepresentation}.\n" +
            "\t *\n" +
            "\t * <p>You should use this method when you don't want to rely on the result of the\n" +
            "\t * value's {@link Object#toString() toString()} method.\n" +
            "\t *\n" +
            "\t * <p>If the supplied {@code value} is {@code null}, this method will return a\n" +
            "\t * cached {@code ValueWrapper} suitable for all {@code null} values.\n" +
            "\t * If the supplied {@code value} is already an instance of {@link ValueWrapper},\n" +
            "\t * it will be returned as is if the {@code stringRepresentation} match, otherwise\n" +
            "\t * the original value will be unwrapped and a new {@code ValueWrapper} with the\n" +
            "\t * new {@code stringRepresentation} will be created.\n" +
            "\t *\n" +
            "\t * @param value the value to wrap; may be {@code null}\n" +
            "\t * @param stringRepresentation a custom rendering of the value; will fallback to\n" +
            "\t * the default behavior if {@code null}\n" +
            "\t * @return a wrapper for the supplied value; never {@code null}\n" +
            "\t * @since 1.2\n" +
            "\t */\n" +
            "\tpublic static ValueWrapper create(Object value, String stringRepresentation) {\n" +
            "\t\tif (value instanceof ValueWrapper) {\n" +
            "\t\t\tValueWrapper wrapper = (ValueWrapper) value;\n" +
            "\t\t\treturn wrapper.stringRepresentation.equals(stringRepresentation) ? wrapper\n" +
            "\t\t\t\t\t: create(wrapper.value, stringRepresentation);\n" +
            "\t\t}\n" +
            "\t\treturn (value == null ? nullValueWrapper : new ValueWrapper(value, stringRepresentation));\n" +
            "\t}\n" +
            "\n" +
            "\tprivate final Serializable value;\n" +
            "\tprivate final Class<?> type;\n" +
            "\tprivate final String stringRepresentation;\n" +
            "\tprivate final int identityHashCode;\n" +
            "\tprivate final transient Object ephemeralValue;\n" +
            "\n" +
            "\t/**\n" +
            "\t * Reads and stores the supplied value's runtime type, string representation, and\n" +
            "\t * identity hash code.\n" +
            "\t */\n" +
            "\tprivate ValueWrapper(Object value, String stringRepresentation) {\n" +
            "\t\tthis.value = value instanceof Serializable ? (Serializable) value : null;\n" +
            "\t\tthis.type = value != null ? value.getClass() : null;\n" +
            "\t\tthis.stringRepresentation = stringRepresentation == null ? safeValueToString(value) : stringRepresentation;\n" +
            "\t\tthis.identityHashCode = System.identityHashCode(value);\n" +
            "\t\tthis.ephemeralValue = value;\n" +
            "\t}\n" +
            "\n" +
            "\tprivate ValueWrapper(Object value) {\n" +
            "\t\tthis(value, safeValueToString(value));\n" +
            "\t}\n" +
            "\n" +
            "\tprivate static String safeValueToString(Object value) {\n" +
            "\t\ttry {\n" +
            "\t\t\treturn String.valueOf(value);\n" +
            "\t\t}\n" +
            "\t\tcatch (Exception e) {\n" +
            "\t\t\treturn \"<Exception in toString(): \" + e + \">\";\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Returns the value supplied to {@link #create(Object)} if the value\n" +
            "\t * implements {@link Serializable}; otherwise, {@code null}.\n" +
            "\t *\n" +
            "\t * @see #getEphemeralValue()\n" +
            "\t */\n" +
            "\tpublic Serializable getValue() {\n" +
            "\t\treturn this.value;\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Returns the value's runtime type or {@code null} if the value is\n" +
            "\t * {@code null}.\n" +
            "\t */\n" +
            "\tpublic Class<?> getType() {\n" +
            "\t\treturn this.type;\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Returns the value's string representation.\n" +
            "\t *\n" +
            "\t * <p>The string representation is generated by invoking\n" +
            "\t * {@link String#valueOf(Object) String.valueOf(value)} for the value\n" +
            "\t * supplied to {@link #create(Object)}.\n" +
            "\t *\n" +
            "\t * @see #getValue()\n" +
            "\t */\n" +
            "\tpublic String getStringRepresentation() {\n" +
            "\t\treturn this.stringRepresentation;\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Returns the value's identity hash code.\n" +
            "\t *\n" +
            "\t * <p>The identity hash code is generated by invoking\n" +
            "\t * {@link System#identityHashCode(Object) System.identityHashCode(value)}\n" +
            "\t * for the value supplied to {@link #create(Object)}.\n" +
            "\t *\n" +
            "\t * @see #getValue()\n" +
            "\t */\n" +
            "\tpublic int getIdentityHashCode() {\n" +
            "\t\treturn this.identityHashCode;\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Returns the original value supplied to {@link #create(Object) create()}.\n" +
            "\t *\n" +
            "\t * <p>If this {@code ValueWrapper} was created by deserialization this method\n" +
            "\t * returns {@code null}.\n" +
            "\t *\n" +
            "\t * @see #getValue()\n" +
            "\t * @since 1.2\n" +
            "\t */\n" +
            "\tpublic Object getEphemeralValue() {\n" +
            "\t\treturn this.ephemeralValue;\n" +
            "\t}\n" +
            "\n" +
            "\t/**\n" +
            "\t * Returns the value's string representation along with its type and\n" +
            "\t * identity hash code.\n" +
            "\t */\n" +
            "\t@Override\n" +
            "\tpublic String toString() {\n" +
            "\t\tif (this.type == null) {\n" +
            "\t\t\treturn \"null\";\n" +
            "\t\t}\n" +
            "\t\treturn this.stringRepresentation + //\n" +
            "\t\t\t\t\" (\" + this.type.getName() + \"@\" + Integer.toHexString(this.identityHashCode) + \")\";\n" +
            "\t}\n" +
            "\n" +
            "}";
}
