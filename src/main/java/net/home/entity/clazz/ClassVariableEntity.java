package net.home.entity.clazz;

import lombok.NonNull;
import net.home.enums.AccessModifier;
import net.home.enums.Modifier;
import net.home.util.ConstantStore;

import static net.home.util.ConstantStore.EQUAL_SIGN;
import static net.home.util.ConstantStore.LINE_SEPARATOR;
import static net.home.util.ConstantStore.OPERATION_END;
import static net.home.util.ConstantStore.WHITESPACE;

public class ClassVariableEntity extends VariableEntity {
    private final int countOfTabs;

    private JavaDocEntity javaDocEntity;

    public ClassVariableEntity(@NonNull String name, @NonNull String type, int countOfTabs) {
        super(name, type);
        this.countOfTabs = countOfTabs;
    }

    public ClassVariableEntity(@NonNull String name, @NonNull String type, @NonNull AccessModifier accessModifier, int countOfTabs) {
        super(name, type, accessModifier);
        this.countOfTabs = countOfTabs;
    }

    public void addAnnotation(@NonNull AnnotationEntity annotationEntity) {
        annotationEntities.add(annotationEntity);
    }

    public void addModifier(@NonNull Modifier modifier) {
        modifiers.add(modifier);
    }

    public void setValue(String value) {
        if (this.value != null) {
            throw new IllegalArgumentException("Variable value already set");
        }
        this.value = value;
    }

    public void setJavaDocEntity(JavaDocEntity javaDocEntity) {
        if (javaDocEntity == null) {
            throw new IllegalArgumentException("Java doc already set");
        }
        this.javaDocEntity = javaDocEntity;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        javaDocToString(builder).append(LINE_SEPARATOR);
        annotationsToString(builder).append(ConstantStore.getIndent(countOfTabs)).append(AccessModifier.toString(accessModifier));
        modifiersToString(builder).append(type).append(WHITESPACE).append(name);
        valueToString(builder).append(OPERATION_END);
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
        modifiers.forEach(modifier -> builder.append(Modifier.toString(modifier)));
        return builder;
    }

    private StringBuilder valueToString(StringBuilder builder) {
        if (value != null) {
            builder.append(WHITESPACE).append(EQUAL_SIGN).append(WHITESPACE).append(value);
        }
        return builder;
    }
}
