package net.homecredit.entity.clazz;

import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.Modifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodEntity {
    private final String name;
    private final AccessModifier accessModifier;
    private final List<Modifier> modifiers = new ArrayList<>();
    private final List<String> content = new ArrayList<>();
    private final int countOfTabs;

    public MethodEntity(String name, int countOfTabs) {
        this(name, AccessModifier.DEFAULT, countOfTabs);
    }

    public MethodEntity(String name, AccessModifier accessModifier, int countOfTabs) {
        this(name, accessModifier, countOfTabs, (Modifier[]) new Modifier[0]);
    }

    public MethodEntity(String name, AccessModifier accessModifier, int countOfTabs, Modifier... modifiers) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.modifiers.addAll(Arrays.asList(modifiers));
        this.countOfTabs = countOfTabs;
    }

    public void addContent(String content) {
        this.content.add(content);
    }

    @Override
    public String toString() {
        return "MethodEntity{}";
    }
}
