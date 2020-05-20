package net.homecredit.builder;

import net.homecredit.entity.clazz.JavaDocEntity;

import java.util.List;

public class JavaDocBuilder implements Builder {
    private final JavaDocEntity javaDocEntity;

    public JavaDocBuilder(List<String> content, int countOfTabs) {
        javaDocEntity = new JavaDocEntity(content, countOfTabs);
    }

    @Override
    public JavaDocEntity getEntity() {
        return javaDocEntity;
    }
}
