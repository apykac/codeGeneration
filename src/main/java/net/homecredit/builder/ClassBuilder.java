package net.homecredit.builder;

import net.homecredit.entity.clazz.ClassEntity;
import net.homecredit.entity.clazz.ImportEntity;
import net.homecredit.entity.clazz.PackageEntity;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.ClassType;
import net.homecredit.enums.Modifier;
import net.homecredit.util.Assert;

import java.util.List;

import static net.homecredit.util.ConstantStore.ANNOTATION_ERROR;
import static net.homecredit.util.ConstantStore.METHOD_ERROR;
import static net.homecredit.util.ConstantStore.VARIABLE_ERROR;

public class ClassBuilder implements Builder {
    private final ClassEntity entity;

    private AnnotationBuilder lastAnnotation;
    private ClassVariableBuilder lastVariable;
    private MethodBuilder lastMethod;
    private JavaDocBuilder javaDoc;

    public ClassBuilder(String name, AccessModifier accessModifier, ClassType classType, String packageName, int countOfTabs) {
        entity = new ClassEntity(name, accessModifier, classType, new PackageEntity(packageName), countOfTabs);
    }

    @Override
    public ClassEntity getEntity() {
        return entity;
    }

    public ClassBuilder addImport(ImportEntity importEntity) {
        entity.addImport(importEntity);
        return this;
    }

    public ClassBuilder addModifier(Modifier modifier) {
        entity.addModifier(modifier);
        return this;
    }

    public ClassBuilder setJavaDoc(List<String> content, int countOfTabs) {
        if (javaDoc != null) {
            throw new IllegalArgumentException("Java doc already set");
        }
        javaDoc = new JavaDocBuilder(content, countOfTabs);
        return this;
    }

    public ClassBuilder addAnnotation(String name) {
        lastAnnotation = new AnnotationBuilder(name);
        entity.addAnnotation(lastAnnotation.getEntity());
        return this;
    }

    public ClassBuilder addParameterToAnnotation(String name, String value) {
        Assert.notNull(ANNOTATION_ERROR, lastAnnotation);
        lastAnnotation.addParameter(name, value);
        return this;
    }

    public ClassBuilder addParameterToAnnotation(String value) {
        Assert.notNull(ANNOTATION_ERROR, lastAnnotation);
        lastAnnotation.addParameter(value);
        return this;
    }

    public ClassBuilder addVariable(String name, String type) {
        lastVariable = new ClassVariableBuilder(name, type, entity.getCountOfTabs() + 1);
        entity.addVariable(lastVariable.getEntity());
        return this;
    }

    public ClassBuilder setJavaDocToVariable(List<String> content, int countOfTabs) {
        Assert.notNull(VARIABLE_ERROR, lastVariable);
        lastVariable.setJavaDoc(content, countOfTabs);
        return this;
    }

    public ClassBuilder addVariable(String name, String type, AccessModifier accessModifier) {
        lastVariable = new ClassVariableBuilder(name, type, accessModifier, entity.getCountOfTabs() + 1);
        entity.addVariable(lastVariable.getEntity());
        return this;
    }

    public ClassBuilder addModifierToVariable(Modifier modifier) {
        Assert.notNull(VARIABLE_ERROR, lastVariable);
        lastVariable.addModifier(modifier);
        return this;
    }

    public ClassBuilder setValueToVariable(String value) {
        Assert.notNull(VARIABLE_ERROR, lastVariable);
        lastVariable.setValue(value);
        return this;
    }

    public ClassBuilder addAnnotationToVariable(String name) {
        Assert.notNull(VARIABLE_ERROR, lastVariable);
        lastVariable.addAnnotation(name);
        return this;
    }

    public ClassBuilder addParameterToVariableAnnotation(String name, String value) {
        Assert.notNull(VARIABLE_ERROR, lastVariable);
        lastVariable.addParameterToAnnotation(name, value);
        return this;
    }

    public ClassBuilder addMethod(String name) {
        lastMethod = new MethodBuilder(name, entity.getCountOfTabs() + 1);
        return this;
    }

    public ClassBuilder addMethod(String name, AccessModifier accessModifier) {
        lastMethod = new MethodBuilder(name, accessModifier, entity.getCountOfTabs() + 1);
        return this;
    }

    public ClassBuilder setJavaDocToMethod(List<String> content, int countOfTabs) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.setJavaDoc(content, countOfTabs);
        return this;
    }

    public ClassBuilder addModifierToMethod(Modifier modifier) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addModifier(modifier);
        return this;
    }

    public ClassBuilder addContentToMethod(String content) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addContent(content);
        return this;
    }

    public ClassBuilder addAnnotationToMethod(String name) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addAnnotation(name);
        return this;
    }

    public ClassBuilder addParameterToMethodAnnotation(String name, String value) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addParameterToAnnotation(name, value);
        return this;
    }

    public ClassBuilder addArgumentToMethod(String name, String type) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addArgument(name, type);
        return this;
    }

    public ClassBuilder addAnnotationToMethodArgument(String name) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addAnnotationToArgument(name);
        return this;
    }

    public ClassBuilder addParameterToMethodArgumentAnnotation(String name, String value) {
        Assert.notNull(METHOD_ERROR, lastMethod);
        lastMethod.addParameterToArgumentAnnotation(name, value);
        return this;
    }
}
