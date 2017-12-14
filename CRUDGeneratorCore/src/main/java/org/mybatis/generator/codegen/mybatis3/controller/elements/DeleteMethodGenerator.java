package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DeleteMethodGenerator extends AbstractControllerMethodGenerator {
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
        FullyQualifiedJavaType serviceType=new FullyQualifiedJavaType(introspectedTable.getServiceType());
        importedTypes.add(serviceType);

        method.addBodyLine(StringUtility.convertFieldName(serviceType.getShortName())+".delete("+paramStr+");");
        method.addAnnotation("@RequestMapping(\"/delete\")");
        clazz.addMethod(method);
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        clazz.addImportedTypes(importedTypes);
    }
}
