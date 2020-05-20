package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.util.ConstantStore;

import java.util.List;

import static net.homecredit.util.ConstantStore.LINE_SEPARATOR;

public class JavaDocEntity implements Entity {
    private static final String BEGIN = "/** ";
    private static final String END = " */";
    private static final String INDENT = " * ";

    private final List<String> content;
    private final int countOfTabs;

    public JavaDocEntity(List<String> content, int countOfTabs) {
        if (content.isEmpty()) {
            throw new IllegalArgumentException("Content must be not empty");
        }
        this.content = content;
        this.countOfTabs = countOfTabs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(ConstantStore.getIndent(countOfTabs)).append(BEGIN).append(LINE_SEPARATOR);
        content.forEach(s -> builder.append(ConstantStore.getIndent(countOfTabs)).append(INDENT).append(s).append(LINE_SEPARATOR));
        builder.append(ConstantStore.getIndent(countOfTabs)).append(END);
        return builder.toString();
    }
}
