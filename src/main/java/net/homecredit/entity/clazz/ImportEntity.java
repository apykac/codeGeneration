package net.homecredit.entity.clazz;

import lombok.NonNull;
import net.homecredit.enums.Modifier;

import static net.homecredit.util.ConstantStore.OPERATION_END;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class ImportEntity {
    private static final String IMPORT = "import";

    private final String name;
    private Modifier modifier;

    public ImportEntity(@NonNull String name) {
        this.name = name;
    }

    public ImportEntity(@NonNull String name, @NonNull Modifier modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return IMPORT + WHITESPACE + Modifier.toString(modifier) + name + OPERATION_END;
    }
}
