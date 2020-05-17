package net.homecredit;

import net.homecredit.entity.clazz.AnnotationEntity;
import net.homecredit.entity.clazz.MethodVariableEntity;

public class CodeGenerationApplication {
    public static void main(String[] args) {
        MethodVariableEntity methodVariableEntity = new MethodVariableEntity("name", "String");
        methodVariableEntity.addAnnotation(new AnnotationEntity("NonNull"));
    }
}
