package net.homecredit.entity.clazz;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import static net.homecredit.util.ConstantStore.CLOSING_BRACKET;
import static net.homecredit.util.ConstantStore.COMMA;
import static net.homecredit.util.ConstantStore.OPENING_BRACKET;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class AnnotationEntity implements Entity {
    private static final String ANNOTATION_PREFIX = "@";
    private static final String EQUAL_SIGN = "=";

    private final String name;
    private final Map<String, String> parameters = new HashMap<>();

    public AnnotationEntity(@NonNull String name) {
        this.name = name;
    }

    public void addParameter(@NonNull String key, @NonNull String value) {
        parameters.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(ANNOTATION_PREFIX).append(name);
        return getAnnotationParameters(builder).toString();
    }

    private StringBuilder getAnnotationParameters(StringBuilder builder) {
        if (!parameters.isEmpty()) {
            builder.append(OPENING_BRACKET);
            parametersToString(builder).append(CLOSING_BRACKET);
        }
        return builder;
    }

    private StringBuilder parametersToString(StringBuilder builder) {
        parameters.forEach((k, v) -> builder.append(k).append(EQUAL_SIGN).append(v).append(COMMA).append(WHITESPACE));
        builder.delete(builder.length() - 2, builder.length());
        return builder;
    }
}
