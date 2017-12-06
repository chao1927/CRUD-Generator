package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * 通过可选字段新增
 */
public class SelectSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator{

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName("selectSelective");

        FullyQualifiedJavaType parameterType;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getRecordWithBLOBsType());

        } else {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        }
        method.addParameter(new Parameter(parameterType,
                "record", "@Param(\"record\")")); //$NON-NLS-1$ //$NON-NLS-2$
        importedTypes.add(parameterType);

        importedTypes.add(new FullyQualifiedJavaType(
                "org.apache.ibatis.annotations.Param")); //$NON-NLS-1$

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);
        FullyQualifiedJavaType returnType=new FullyQualifiedJavaType("java.util.List<"+parameterType.getShortName()+">");
        interfaze.addImportedType(returnType);
        method.setReturnType(returnType);
        //if (context.getPlugins()
                //.clientUpdateByExampleSelectiveMethodGenerated(method, interfaze,
                       // introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
       // }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }
}
