package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.ClassType;
import net.homecredit.enums.Modifier;
import net.homecredit.util.ConstantStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.homecredit.util.ConstantStore.CLOSING_BRACE;
import static net.homecredit.util.ConstantStore.OPENING_BRACE;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class ClassEntity implements Entity {
    private final String name;
    private final AccessModifier accessModifier;
    private final ClassType classType;
    private final int countOfTabs;
    private final PackageEntity packageEntity;
    private final List<ImportEntity> importEntities = new ArrayList<>();
    private final List<Modifier> modifiers = new ArrayList<>();
    private final List<ClassVariableEntity> variableEntities = new ArrayList<>();
    private final List<MethodEntity> methodEntities = new ArrayList<>();
    private final List<AnnotationEntity> annotationEntities = new ArrayList<>();

    public ClassEntity(@NonNull String name, @NonNull AccessModifier accessModifier, @NonNull ClassType classType, PackageEntity packageEntity, int countOfTabs) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.classType = classType;
        this.countOfTabs = countOfTabs;
        this.packageEntity = packageEntity;
    }

    public void addModifier(@NonNull Modifier modifier) {
        modifiers.add(modifier);
    }

    public void addMethod(@NonNull MethodEntity methodEntity) {
        methodEntities.add(methodEntity);
    }

    public void addAnnotation(@NonNull AnnotationEntity annotationEntity) {
        annotationEntities.add(annotationEntity);
    }

    public void addVariable(@NonNull ClassVariableEntity classVariableEntity) {
        variableEntities.add(classVariableEntity);
    }

    public void addImport(@NonNull ImportEntity importEntity) {
        importEntities.add(importEntity);
    }

    public boolean isInnerClass() {
        return packageEntity == null &&
                importEntities.isEmpty();
    }

    public int getCountOfTabs() {
        return countOfTabs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(packageEntity).append(System.lineSeparator()).append(System.lineSeparator());
        importsToString(builder);
        annotationsToString(builder);
        builder.append(ConstantStore.getIndent(countOfTabs)).append(AccessModifier.toString(accessModifier));
        modifiersToString(builder).append(ClassType.toString(classType)).append(name).append(WHITESPACE).append(OPENING_BRACE).append(System.lineSeparator());
        variablesToString(builder).append(System.lineSeparator());
        methodsToString(builder).append(ConstantStore.getIndent(countOfTabs)).append(CLOSING_BRACE);

        return builder.toString();
    }

    private StringBuilder importsToString(StringBuilder builder) {
        importEntities.sort(ImportEntity::compareTo);
        List<ImportEntity> nonStaticImports = importEntities.stream().filter(importEntity -> !importEntity.isStatic()).collect(Collectors.toList());
        List<ImportEntity> nonStaticJavaImports = nonStaticImports.stream().filter(ImportEntity::isJavaImport).collect(Collectors.toList());
        List<ImportEntity> staticImports = importEntities.stream().filter(ImportEntity::isStatic).collect(Collectors.toList());
        List<ImportEntity> staticJavaImports = staticImports.stream().filter(ImportEntity::isJavaImport).collect(Collectors.toList());

        if (!nonStaticImports.isEmpty()) {
            nonStaticImports.forEach(importEntity -> builder.append(importEntity).append(System.lineSeparator()));
            builder.append(System.lineSeparator());
        }
        if (!nonStaticJavaImports.isEmpty()) {
            nonStaticJavaImports.forEach(importEntity -> builder.append(importEntity).append(System.lineSeparator()));
            builder.append(System.lineSeparator());
        }
        if (!staticImports.isEmpty()) {
            staticImports.forEach(importEntity -> builder.append(importEntity).append(System.lineSeparator()));
            builder.append(System.lineSeparator());
        }
        if (!staticJavaImports.isEmpty()) {
            staticJavaImports.forEach(importEntity -> builder.append(importEntity).append(System.lineSeparator()));
            builder.append(System.lineSeparator());
        }

        return builder;
    }

    private StringBuilder annotationsToString(StringBuilder builder) {
        annotationEntities.forEach(annotation -> builder.append(ConstantStore.getIndent(countOfTabs)).append(annotation).append(System.lineSeparator()));
        return builder;
    }

    private StringBuilder modifiersToString(StringBuilder builder) {
        modifiers.stream().sorted(Modifier::compare).forEach(modifier -> builder.append(Modifier.toString(modifier)));
        return builder;
    }

    private StringBuilder variablesToString(StringBuilder builder) {
        variableEntities.forEach(variable -> builder.append(variable).append(System.lineSeparator()));
        return builder;
    }

    private StringBuilder methodsToString(StringBuilder builder) {
        methodEntities.forEach(method -> builder.append(method).append(System.lineSeparator()).append(System.lineSeparator()));
        return builder;
    }
}
