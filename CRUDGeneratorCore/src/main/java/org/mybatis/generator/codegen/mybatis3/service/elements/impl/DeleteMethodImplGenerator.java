package org.mybatis.generator.codegen.mybatis3.service.elements.impl;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DeleteMethodImplGenerator extends AbstractServiceImplMethodGenerator {
    public void addInterfaceElements(TopLevelClass clazz) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method=new Method("delete");
        method.setVisibility(JavaVisibility.PUBLIC);
        String paramStr="";
        List<IntrospectedColumn> keyColumns=introspectedTable.getPrimaryKeyColumns();
        for(IntrospectedColumn kc:keyColumns){
            importedTypes.add(kc.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(kc.getFullyQualifiedJavaType(), kc.getJavaProperty()));
            paramStr+=kc.getJavaProperty()+",";
        }
        paramStr=paramStr.substring(0,paramStr.length()-1);
        FullyQualifiedJavaType mapperType=new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        importedTypes.add(mapperType);
        /**
         *  dao.delete..();
         */
        clazz.addMethod(method);
        clazz.addImportedTypes(importedTypes);
        method.addBodyLine(StringUtility.convertFieldName(mapperType.getShortName())+".deleteByPrimaryKey"+"("+paramStr+");");
    }
}
