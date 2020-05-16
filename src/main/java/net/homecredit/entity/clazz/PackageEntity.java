package net.homecredit.entity.clazz;

import lombok.NonNull;

import static net.homecredit.util.ConstantStore.OPERATION_END;
import static net.homecredit.util.ConstantStore.WHITESPACE;

public class PackageEntity {

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
