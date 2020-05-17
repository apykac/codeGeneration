package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.Modifier;

import java.util.Objects;

import static net.homecredit.util.ConstantStore.OPERATION_END;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class ImportEntity implements Comparable<ImportEntity>, Entity {
    private static final String IMPORT = "import";
    private static final String JAVA_IMPORT_PREFIX = "java.";

    private final String name;
    private Modifier modifier;

    public ImportEntity(@NonNull String name) {
        this.name = name;
    }

    public ImportEntity(@NonNull String name, @NonNull Modifier modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    public boolean isStatic() {
        return Objects.equals(modifier, Modifier.STATIC);
    }

    public boolean isJavaImport() {
        return name.startsWith(JAVA_IMPORT_PREFIX);
    }

    @Override
    public String toString() {
        return IMPORT + WHITESPACE + Modifier.toString(modifier) + name + OPERATION_END;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportEntity that = (ImportEntity) o;
        return Objects.equals(name, that.name) &&
                modifier == that.modifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, modifier);
    }

    @Override
    public int compareTo(ImportEntity o) {
        return name.compareTo(o.name);
    }
}
