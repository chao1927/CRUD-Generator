package org.mybatis.generator.codegen.mybatis3.service.elements.impl;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FindMethodImplGenerator  extends AbstractServiceImplMethodGenerator {

    @Override
    public void addInterfaceElements(TopLevelClass clazz) {
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
        String paramStr="";
        for(IntrospectedColumn kc:keyColumns){
            importedTypes.add(kc.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(kc.getFullyQualifiedJavaType(), kc.getJavaProperty()));
            paramStr+=kc.getJavaProperty()+",";
        }
        paramStr=paramStr.substring(0,paramStr.length()-1);
        FullyQualifiedJavaType mapperType=new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        importedTypes.add(mapperType);
        method.setReturnType(returnType);
        clazz.addMethod(method);
        clazz.addImportedTypes(importedTypes);
        method.addBodyLine("return "+StringUtility.convertFieldName(mapperType.getShortName())+".selectByPrimaryKey"+"("+paramStr+");");
    }
}
