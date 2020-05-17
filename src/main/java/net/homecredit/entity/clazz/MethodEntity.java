package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.Modifier;
import net.homecredit.util.ConstantStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.homecredit.util.ConstantStore.CLOSING_BRACKET;
import static net.homecredit.util.ConstantStore.COMMA;
import static net.homecredit.util.ConstantStore.OPENING_BRACE;
import static net.homecredit.util.ConstantStore.OPENING_BRACKET;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class MethodEntity {
    private final String name;
    private final AccessModifier accessModifier;
    private final List<Modifier> modifiers = new ArrayList<>();
    private final List<String> content = new ArrayList<>();
    private final List<AnnotationEntity> annotationEntities = new ArrayList<>();
    private final List<MethodVariableEntity> arguments = new ArrayList<>();
    private final int countOfTabs;

    public MethodEntity(@NonNull String name, int countOfTabs) {
        this(name, AccessModifier.DEFAULT, countOfTabs);
    }

    public MethodEntity(@NonNull String name, @NonNull AccessModifier accessModifier, int countOfTabs) {
        this(name, accessModifier, countOfTabs, new Modifier[0]);
    }

    public MethodEntity(@NonNull String name, @NonNull AccessModifier accessModifier, int countOfTabs, Modifier... modifiers) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.modifiers.addAll(Modifier.rightSequence(Arrays.asList(modifiers)));
        this.countOfTabs = countOfTabs;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        annotationsToString(builder).append(ConstantStore.getIndent(countOfTabs)).append(WHITESPACE).append(AccessModifier.toString(accessModifier));
        modifiersToString(builder).append(name).append(OPENING_BRACKET);
        argumentsToString(builder).append(CLOSING_BRACKET).append(WHITESPACE).append(OPENING_BRACE).append(System.lineSeparator());
        contentToString(builder).append(System.lineSeparator()).append(ConstantStore.getIndent(countOfTabs)).append(CLOSING_BRACKET);
        return builder.toString();
    }

    private StringBuilder annotationsToString(StringBuilder builder) {
        annotationEntities.forEach(annotation -> builder.append(ConstantStore.getIndent(countOfTabs)).append(annotation).append(System.lineSeparator()));
        return builder;
    }

    private StringBuilder modifiersToString(StringBuilder builder) {
        modifiers.forEach(modifier -> builder.append(Modifier.toString(modifier)).append(" "));
        return builder;
    }

    private StringBuilder contentToString(StringBuilder builder) {
        content.forEach(contentLine -> builder.append(ConstantStore.getIndent(countOfTabs + 1)).append(contentLine).append(System.lineSeparator()));
        return builder;
    }

    private StringBuilder argumentsToString(StringBuilder builder) {
        arguments.forEach(argument -> builder.append(argument).append(COMMA).append(WHITESPACE));
        return builder.delete(builder.length() - 2, builder.length());
    }
}
