package org.mybatis.generator.codegen.mybatis3.service.elements.impl;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Set;
import java.util.TreeSet;

public class DeleteMethodImplGenerator extends AbstractServiceImplMethodGenerator {
    public void addInterfaceElements(TopLevelClass clazz) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method=new Method("delete");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType parameterType;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getRecordWithBLOBsType());
        } else {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        }
        importedTypes.add(parameterType);
        String paramName= StringUtility.convertFieldName(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        method.addParameter(new Parameter(parameterType,paramName));
        FullyQualifiedJavaType mapperType=new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        importedTypes.add(mapperType);
        /**
         *  dao.delete..();
         */
        method.addBodyLine(StringUtility.convertFieldName(mapperType.getShortName())+".deleteByExample("+paramName+");");
        clazz.addMethod(method);
        clazz.addImportedTypes(importedTypes);
    }
}
