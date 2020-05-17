package net.homecredit.builder;

import net.homecredit.entity.clazz.MethodEntity;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.Modifier;
import net.homecredit.util.Assert;

import static net.homecredit.util.ConstantStore.ANNOTATION_ERROR;
import static net.homecredit.util.ConstantStore.VARIABLE_ERROR;

public class MethodBuilder implements Builder {
    private final MethodEntity entity;

    private AnnotationBuilder lastAnnotation;
    private MethodVariableBuilder lastArgument;

    public MethodBuilder(String name, int countOfTabs) {
        entity = new MethodEntity(name, AccessModifier.DEFAULT, countOfTabs);
    }

    public MethodBuilder(String name, AccessModifier accessModifier, int countOfTabs) {
        entity = new MethodEntity(name, accessModifier, countOfTabs);
    }

    @Override
    public MethodEntity getEntity() {
        return entity;
    }

    public MethodBuilder addModifier(Modifier modifier) {
        entity.addModifier(modifier);
        return this;
    }

    public MethodBuilder addContent(String content) {
        entity.addContent(content);
        return this;
    }

    public MethodBuilder addAnnotation(String name) {
        lastAnnotation = new AnnotationBuilder(name);
        entity.addAnnotation(lastAnnotation.getEntity());
        return this;
    }

    public MethodBuilder addParameterToAnnotation(String name, String value) {
        Assert.notNull(ANNOTATION_ERROR, lastAnnotation);
        lastAnnotation.addParameter(name, value);
        return this;
    }

    public MethodBuilder addArgument(String name, String type) {
        lastArgument = new MethodVariableBuilder(name, type);
        entity.addArgument(lastArgument.getEntity());
        return this;
    }

    public MethodBuilder addAnnotationToArgument(String name) {
        Assert.notNull(VARIABLE_ERROR, lastArgument);
        lastArgument.addAnnotation(name);
        return this;
    }

    public MethodBuilder addParameterToArgumentAnnotation(String name, String value) {
        Assert.notNull(VARIABLE_ERROR, lastArgument);
        lastArgument.addParameterToAnnotation(name, value);
        return this;
    }
}