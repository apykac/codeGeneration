package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.Modifier;
import net.homecredit.util.ConstantStore;

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

    public ClassVariableEntity(@NonNull String name, @NonNull String type, @NonNull AccessModifier accessModifier, int countOfTabs, Modifier... modifiers) {
        super(name, type, accessModifier, modifiers);
        this.countOfTabs = countOfTabs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        annotationsToString(builder).append(ConstantStore.getIndent(countOfTabs)).append(AccessModifier.toString(accessModifier));
        modifiersToString(builder).append(type).append(WHITESPACE).append(name).append(OPERATION_END);
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
}
