package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Set;
import java.util.TreeSet;

public class UpdateMethodGenerator extends AbstractControllerMethodGenerator {
    public void addInterfaceElements(TopLevelClass clazz) {

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method=new Method("update");
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
        FullyQualifiedJavaType serviceType=new FullyQualifiedJavaType(introspectedTable.getServiceType());
        importedTypes.add(serviceType);
        /**
         *  dao.delete..();
         */
        method.addBodyLine(StringUtility.convertFieldName(serviceType.getShortName())+".update("+paramName+");");
        method.addAnnotation("@RequestMapping(\"/update\")");
        clazz.addMethod(method);
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        clazz.addImportedTypes(importedTypes);
    }
}
