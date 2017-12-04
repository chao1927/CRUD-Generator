package org.mybatis.generator.codegen.mybatis3.service.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Set;
import java.util.TreeSet;

public class DeleteMethodGenerator extends AbstractServiceMethodGenerator {
    public void addInterfaceElements(Interface interfaze) {
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
        String paramName=StringUtility.convertFieldName(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        method.addParameter(new Parameter(parameterType,paramName));
        interfaze.addMethod(method);
        interfaze.addImportedTypes(importedTypes);
    }
}
