package net.homecredit;

import net.homecredit.builder.ClassBuilder;
import net.homecredit.enums.AccessModifier;
import net.homecredit.enums.ClassType;
import net.homecredit.enums.Modifier;

public class CodeGenerationApplication {
    public static void main(String[] args) {
        ClassBuilder builder = new ClassBuilder("ValueWrapper", AccessModifier.PUBLIC, ClassType.DEFAULT, "org.opentest4j", 0);
        builder.addModifier(Modifier.FINAL)
                .addAnnotation("Data")
                .addParameterToAnnotation("some")
                .addParameterToAnnotation("some", "value")
                .addVariable("serialVersionUID", "long", AccessModifier.PRIVATE)
                .setValueToVariable("new SomeClass()")
                .addModifierToVariable(Modifier.STATIC)
                .addModifierToVariable(Modifier.FINAL);
    }
}
