package net.home.entity.clazz;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import static net.home.util.ConstantStore.CLOSING_BRACKET;
import static net.home.util.ConstantStore.COMMA;
import static net.home.util.ConstantStore.EQUAL_SIGN;
import static net.home.util.ConstantStore.OPENING_BRACKET;
import static net.home.util.ConstantStore.WHITESPACE;

public class AnnotationEntity implements Entity {
    private static final String ANNOTATION_PREFIX = "@";

    private final String name;
    private final Map<String, String> parameters = new HashMap<>();
    private String value;

    public AnnotationEntity(@NonNull String name) {
        this.name = name;
    }

    public void addParameter(@NonNull String key, @NonNull String value) {
        if (this.value != null) {
            throw new IllegalArgumentException("Some single parameter was set");
        }
        parameters.put(key, value);
    }

    public void addParameter(String value) {
        if (!parameters.isEmpty()) {
            throw new IllegalArgumentException("Some not single parameters was set");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(ANNOTATION_PREFIX).append(name);
        return parametersToString(builder).toString();
    }

    private StringBuilder parametersToString(StringBuilder builder) {
        if (!parameters.isEmpty()){
            builder.append(OPENING_BRACKET);
            parameters.forEach((k, v) -> builder.append(k).append(EQUAL_SIGN).append(v).append(COMMA).append(WHITESPACE));
            builder.delete(builder.length() - 2, builder.length()).append(CLOSING_BRACKET);
        } else if (value != null) {
            builder.append(OPENING_BRACKET).append(value).append(CLOSING_BRACKET);
        }
        return builder;
    }
}
