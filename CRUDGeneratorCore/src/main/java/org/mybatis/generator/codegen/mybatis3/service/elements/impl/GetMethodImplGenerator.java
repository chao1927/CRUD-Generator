package org.mybatis.generator.codegen.mybatis3.service.elements.impl;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Set;
import java.util.TreeSet;

public class GetMethodImplGenerator extends AbstractServiceImplMethodGenerator {

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
        FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        importedTypes.add(mapperType);
        /**
         *return  dao.get..();
         */
        method.addBodyLine("return " + StringUtility.convertFieldName(mapperType.getShortName()) + ".selectByExample(" + paramName + ");");
        clazz.addMethod(method);
        clazz.addImportedTypes(importedTypes);
    }
}
