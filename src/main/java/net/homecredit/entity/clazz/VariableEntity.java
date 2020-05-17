package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.Modifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class VariableEntity implements Comparable<VariableEntity> {
    protected final String name;
    protected final String type;
    protected final AccessModifier accessModifier;
    protected final List<Modifier> modifiers = new ArrayList<>();
    protected final List<AnnotationEntity> annotationEntities = new ArrayList<>();

    public VariableEntity(@NonNull String name, @NonNull String type) {
        this(name, type, AccessModifier.DEFAULT);
    }

    public VariableEntity(@NonNull String name, @NonNull String type, @NonNull AccessModifier accessModifier) {
        this(name, type, accessModifier, new Modifier[0]);
    }

    public VariableEntity(@NonNull String name, @NonNull String type, @NonNull AccessModifier accessModifier, Modifier... modifiers) {
        this.name = name;
        this.type = type;
        this.accessModifier = accessModifier;
        this.modifiers.addAll(Modifier.rightSequence(Arrays.asList(modifiers)));
    }

    public void addAnnotation(@NonNull AnnotationEntity annotationEntity) {
        annotationEntities.add(annotationEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableEntity that = (VariableEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                accessModifier == that.accessModifier &&
                Objects.equals(modifiers, that.modifiers) &&
                Objects.equals(annotationEntities, that.annotationEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, accessModifier, modifiers, annotationEntities);
    }

    @Override
    public int compareTo(VariableEntity o) {
        return compareByAccessModifiers(o);
    }

    private int compareByAccessModifiers(VariableEntity o) {
        int compareResult = AccessModifier.compare(accessModifier, o.accessModifier);
        if (compareResult == 0) {
            return compareByStatic(o);
        } else {
            return compareResult;
        }
    }

    private int compareByStatic(VariableEntity o) {
        int s1 = modifiers.contains(Modifier.STATIC) ? 1 : -1;
        int s2 = o.modifiers.contains(Modifier.STATIC) ? 1 : -1;
        if (s1 == s2) {
            return compareByFinal(o);
        } else {
            return Integer.compare(s1, s2);
        }
    }

    private int compareByFinal(VariableEntity o) {
        int f1 = modifiers.contains(Modifier.FINAL) ? 1 : -1;
        int f2 = o.modifiers.contains(Modifier.FINAL) ? 1 : -1;
        if (f1 == f2) {
            return compareByType(o);
        } else {
            return Integer.compare(f1, f2);
        }
    }

    private int compareByType(VariableEntity o) {
        int compareResult = type.compareTo(o.type);
        if (compareResult == 0) {
            return name.compareTo(o.name);
        } else {
            return compareResult;
        }
    }
}
