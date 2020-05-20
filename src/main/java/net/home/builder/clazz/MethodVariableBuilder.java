package net.home.builder.clazz;

import net.home.entity.clazz.MethodVariableEntity;
import net.home.util.Assert;

import static net.home.util.ConstantStore.ANNOTATION_ERROR;

public class MethodVariableBuilder implements Builder {
    private final MethodVariableEntity entity;

    private AnnotationBuilder lastAnnotation;

    public MethodVariableBuilder(String name, String type) {
        entity = new MethodVariableEntity(name, type);
    }

    @Override
    public MethodVariableEntity getEntity() {
        return entity;
    }

    public MethodVariableBuilder addAnnotation(String name) {
        lastAnnotation = new AnnotationBuilder(name);
        entity.addAnnotation(lastAnnotation.getEntity());
        return this;
    }

    public MethodVariableBuilder addParameterToAnnotation(String name, String value) {
        Assert.notNull(ANNOTATION_ERROR, lastAnnotation);
        lastAnnotation.addParameter(name, value);
        return this;
    }
}
