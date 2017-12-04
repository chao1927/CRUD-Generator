package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Set;
import java.util.TreeSet;

public class GetMethodGenerator extends AbstractControllerMethodGenerator {
    public void addInterfaceElements(TopLevelClass clazz) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method("get");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType parameterType;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getRecordWithBLOBsType());

        } else {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        }
        String returnTypeStr = "java.util.List<" + parameterType.getShortName() + ">";
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(returnTypeStr);
        importedTypes.add(parameterType);
        importedTypes.add(returnType);
        String paramName = StringUtility.convertFieldName(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        method.addParameter(new Parameter(parameterType, paramName));
        method.setReturnType(returnType);
        clazz.addImportedTypes(importedTypes);
        method.addParameter(new Parameter(parameterType, paramName));
        method.addAnnotation("@RequestMapping(\"/get\")");
        FullyQualifiedJavaType serviceType = new FullyQualifiedJavaType(introspectedTable.getServiceType());
        importedTypes.add(serviceType);
        /**
         *return  dao.get..();
         */
        method.addBodyLine("return " + StringUtility.convertFieldName(serviceType.getShortName()) + ".get(" + paramName + ");");
        clazz.addMethod(method);
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        clazz.addImportedTypes(importedTypes);
    }
}
