package net.home.entity.clazz;

import lombok.NonNull;
import net.home.enums.AccessModifier;
import net.home.enums.Modifier;
import net.home.util.ConstantStore;

import java.util.ArrayList;
import java.util.List;

import static net.home.util.ConstantStore.CLOSING_BRACKET;
import static net.home.util.ConstantStore.COMMA;
import static net.home.util.ConstantStore.LINE_SEPARATOR;
import static net.home.util.ConstantStore.OPENING_BRACE;
import static net.home.util.ConstantStore.OPENING_BRACKET;
import static net.home.util.ConstantStore.WHITESPACE;

public class MethodEntity implements Entity {
    private final String name;
    private final AccessModifier accessModifier;
    private final List<Modifier> modifiers = new ArrayList<>();
    private final List<String> content = new ArrayList<>();
    private final List<AnnotationEntity> annotationEntities = new ArrayList<>();
    private final List<MethodVariableEntity> arguments = new ArrayList<>();
    private final int countOfTabs;

    private JavaDocEntity javaDocEntity;

    public MethodEntity(@NonNull String name, int countOfTabs) {
        this(name, AccessModifier.DEFAULT, countOfTabs);
    }

    public MethodEntity(@NonNull String name, @NonNull AccessModifier accessModifier, int countOfTabs) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.countOfTabs = countOfTabs;
    }

    public void addModifier(@NonNull Modifier modifier) {
        modifiers.add(modifier);
    }

    public void addContent(@NonNull String content) {
        this.content.add(content);
    }

    public void addAnnotation(@NonNull AnnotationEntity annotationEntity) {
        annotationEntities.add(annotationEntity);
    }

    public void addArgument(@NonNull MethodVariableEntity methodVariableEntity) {
        arguments.add(methodVariableEntity);
    }

    public void setJavaDocEntity(JavaDocEntity javaDocEntity) {
        if (javaDocEntity == null) {
            throw new IllegalArgumentException("Java doc already set");
        }
        this.javaDocEntity = javaDocEntity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        javaDocToString(builder).append(LINE_SEPARATOR);
        annotationsToString(builder).append(ConstantStore.getIndent(countOfTabs)).append(WHITESPACE).append(AccessModifier.toString(accessModifier));
        modifiersToString(builder).append(name).append(OPENING_BRACKET);
        argumentsToString(builder).append(CLOSING_BRACKET).append(WHITESPACE).append(OPENING_BRACE).append(LINE_SEPARATOR);
        contentToString(builder).append(LINE_SEPARATOR).append(ConstantStore.getIndent(countOfTabs)).append(CLOSING_BRACKET);
        return builder.toString();
    }

    private StringBuilder javaDocToString(StringBuilder builder) {
        builder.append(javaDocEntity);
        return builder;
    }

    private StringBuilder annotationsToString(StringBuilder builder) {
        annotationEntities.forEach(annotation -> builder.append(ConstantStore.getIndent(countOfTabs)).append(annotation).append(LINE_SEPARATOR));
        return builder;
    }

    private StringBuilder modifiersToString(StringBuilder builder) {
        modifiers.stream().sorted(Modifier::compare).forEach(modifier -> builder.append(Modifier.toString(modifier)).append(" "));
        return builder;
    }

    private StringBuilder contentToString(StringBuilder builder) {
        content.forEach(contentLine -> builder.append(ConstantStore.getIndent(countOfTabs + 1)).append(contentLine).append(LINE_SEPARATOR));
        return builder;
    }

    private StringBuilder argumentsToString(StringBuilder builder) {
        arguments.forEach(argument -> builder.append(argument).append(COMMA).append(WHITESPACE));
        return builder.delete(builder.length() - 2, builder.length());
    }
}
