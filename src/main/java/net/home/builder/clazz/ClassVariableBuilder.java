package net.home.builder.clazz;

import net.home.entity.clazz.ClassVariableEntity;
import net.home.enums.AccessModifier;
import net.home.enums.Modifier;
import net.home.util.Assert;

import java.util.List;

import static net.home.util.ConstantStore.ANNOTATION_ERROR;

public class ClassVariableBuilder implements Builder {
    private final ClassVariableEntity entity;

    private AnnotationBuilder lastAnnotation;
    private JavaDocBuilder javaDoc;

    public ClassVariableBuilder(String name, String type, int countOfTabs) {
        entity = new ClassVariableEntity(name, type, countOfTabs);
    }

    public ClassVariableBuilder(String name, String type, AccessModifier accessModifier, int countOfTabs) {
        entity = new ClassVariableEntity(name, type, accessModifier, countOfTabs);
    }

    @Override
    public ClassVariableEntity getEntity() {
        return entity;
    }

    public ClassVariableBuilder addModifier(Modifier modifier) {
        entity.addModifier(modifier);
        return this;
    }

    public ClassVariableBuilder setValue(String value) {
        entity.setValue(value);
        return this;
    }

    public ClassVariableBuilder setJavaDoc(List<String> content, int countOfTabs) {
        if (javaDoc != null) {
            throw new IllegalArgumentException("Java doc already set");
        }
        javaDoc = new JavaDocBuilder(content, countOfTabs);
        return this;
    }

    public ClassVariableBuilder addAnnotation(String name) {
        lastAnnotation = new AnnotationBuilder(name);
        entity.addAnnotation(lastAnnotation.getEntity());
        return this;
    }

    public ClassVariableBuilder addParameterToAnnotation(String name, String value) {
        Assert.notNull(ANNOTATION_ERROR, lastAnnotation);
        lastAnnotation.addParameter(name, value);
        return this;
    }
}
