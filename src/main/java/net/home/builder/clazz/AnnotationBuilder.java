package net.home.builder.clazz;

import net.home.entity.clazz.AnnotationEntity;

public class AnnotationBuilder implements Builder {
    private final AnnotationEntity entity;

    public AnnotationBuilder(String name) {
        entity = new AnnotationEntity(name);
    }

    @Override
    public AnnotationEntity getEntity() {
        return entity;
    }

    public AnnotationBuilder addParameter(String name, String value) {
        entity.addParameter(name, value);
        return this;
    }

    public AnnotationBuilder addParameter(String value) {
        entity.addParameter(value);
        return this;
    }
}
