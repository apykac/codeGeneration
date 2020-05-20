package net.home.entity.clazz;

import lombok.NonNull;

import static net.home.util.ConstantStore.WHITESPACE;

public class MethodVariableEntity extends VariableEntity {
    public MethodVariableEntity(@NonNull String name, @NonNull String type) {
        super(name, type);
    }

    public void addAnnotation(@NonNull AnnotationEntity annotationEntity) {
        annotationEntities.add(annotationEntity);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return annotationsToString(builder).append(type).append(WHITESPACE).append(name).toString();
    }

    private StringBuilder annotationsToString(StringBuilder builder) {
        annotationEntities.forEach(annotation -> builder.append(annotation).append(WHITESPACE));
        return builder;
    }
}
