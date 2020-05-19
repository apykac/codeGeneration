package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.Modifier;
import net.homecredit.util.ConstantStore;

import static net.homecredit.util.ConstantStore.EQUAL_SIGN;
import static net.homecredit.util.ConstantStore.OPERATION_END;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class ClassVariableEntity extends VariableEntity {
    private final int countOfTabs;

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
        annotationsToString(builder).append(ConstantStore.getIndent(countOfTabs)).append(AccessModifier.toString(accessModifier));
        modifiersToString(builder).append(type).append(WHITESPACE).append(name);
        valueToString(builder).append(OPERATION_END);
        return builder.toString();
    }

    private StringBuilder annotationsToString(StringBuilder builder) {
        annotationEntities.forEach(annotation -> builder.append(ConstantStore.getIndent(countOfTabs)).append(annotation).append(System.lineSeparator()));
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
