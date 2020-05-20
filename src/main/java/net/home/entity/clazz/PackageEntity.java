package net.home.entity.clazz;

import lombok.NonNull;

import static net.home.util.ConstantStore.OPERATION_END;
import static net.home.util.ConstantStore.WHITESPACE;

public class PackageEntity implements Entity {
    private static final String PACKAGE = "package";

    private final String name;

    public PackageEntity(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return PACKAGE + WHITESPACE + name + OPERATION_END;
    }
}
