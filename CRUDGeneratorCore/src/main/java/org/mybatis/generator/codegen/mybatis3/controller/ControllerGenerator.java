package org.mybatis.generator.codegen.mybatis3.controller;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.controller.elements.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ControllerGenerator extends AbstractJavaGenerator {
    public List<CompilationUnit> getCompilationUnits() {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        List<CompilationUnit> answer=new ArrayList<CompilationUnit>();
        TopLevelClass clazz=new TopLevelClass(introspectedTable.getControllerType());
        clazz.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType superInterfaceType=new FullyQualifiedJavaType(introspectedTable.getServiceType());
        importedTypes.add(superInterfaceType);
        clazz.addSuperInterface(superInterfaceType);
        FullyQualifiedJavaType autowireFieldType=new FullyQualifiedJavaType(introspectedTable.getServiceType());
        Field autowireField=new Field(StringUtility.convertFieldName(autowireFieldType.getShortName()),autowireFieldType);
        importedTypes.add(autowireFieldType);
        autowireField.setVisibility(JavaVisibility.PRIVATE);
        autowireField.addAnnotation("@Autowired");
        FullyQualifiedJavaType autowireType=new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired");
        importedTypes.add(autowireType);
        clazz.addField(autowireField);
        clazz.addAnnotation("@Controller");
        clazz.addImportedTypes(importedTypes);
        addAddMethodGenerator(clazz);
        addDeleteMethodGenerator(clazz);
        addUpdateMethodGenerator(clazz);
        addGetMethodGenerator(clazz);
        answer.add(clazz);
        return answer;
    }

    protected void addAddMethodGenerator(TopLevelClass clazz){
        AbstractControllerMethodGenerator methodGeneratorMethodGenerator= new AddMethodGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void addDeleteMethodGenerator(TopLevelClass clazz){
        AbstractControllerMethodGenerator methodGeneratorMethodGenerator= new DeleteMethodGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void addUpdateMethodGenerator(TopLevelClass clazz){
        AbstractControllerMethodGenerator methodGeneratorMethodGenerator= new UpdateMethodGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void addGetMethodGenerator(TopLevelClass clazz){
        AbstractControllerMethodGenerator methodGeneratorMethodGenerator= new GetMethodGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void initializeAndExecuteGenerator(
            AbstractControllerMethodGenerator methodGenerator,
            TopLevelClass clazz) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(clazz);
    }
}
