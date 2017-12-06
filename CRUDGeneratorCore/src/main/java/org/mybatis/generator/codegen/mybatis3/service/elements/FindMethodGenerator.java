package org.mybatis.generator.codegen.mybatis3.service.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FindMethodGenerator extends AbstractServiceMethodGenerator{
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method("find");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            returnType = new FullyQualifiedJavaType(introspectedTable
                    .getRecordWithBLOBsType());

        } else {
            returnType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        }
        importedTypes.add(returnType);
        List<IntrospectedColumn> keyColumns=introspectedTable.getPrimaryKeyColumns();
        for(IntrospectedColumn kc:keyColumns){
            importedTypes.add(kc.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(kc.getFullyQualifiedJavaType(), kc.getJavaProperty()));
        }

        method.setReturnType(returnType);
        interfaze.addMethod(method);
        interfaze.addImportedTypes(importedTypes);
    }
}
