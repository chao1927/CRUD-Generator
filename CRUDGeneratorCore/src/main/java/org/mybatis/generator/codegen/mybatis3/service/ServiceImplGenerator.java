package org.mybatis.generator.codegen.mybatis3.service;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.service.elements.impl.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 生成service实现类
 */
public class ServiceImplGenerator extends AbstractJavaGenerator {
    public List<CompilationUnit> getCompilationUnits() {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        List<CompilationUnit> answer=new ArrayList<CompilationUnit>();
        TopLevelClass clazz=new TopLevelClass(introspectedTable.getServiceImplType());
        clazz.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType superInterfaceType=new FullyQualifiedJavaType(introspectedTable.getServiceType());
        importedTypes.add(superInterfaceType);
        clazz.addSuperInterface(superInterfaceType);
        FullyQualifiedJavaType autowireFieldType=new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        Field autowireField=new Field(StringUtility.convertFieldName(autowireFieldType.getShortName()),autowireFieldType);
        importedTypes.add(autowireFieldType);
        autowireField.setVisibility(JavaVisibility.PRIVATE);
        autowireField.addAnnotation("@Autowired");
        FullyQualifiedJavaType autowireType=new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired");
        importedTypes.add(autowireType);
        clazz.addField(autowireField);
        clazz.addAnnotation("@Service");
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        answer.add(clazz);
        clazz.addImportedTypes(importedTypes);
        addAddImplMethodGenerator(clazz);
        addDeleteImplMethodGenerator(clazz);
        addUpdateImplMethodGenerator(clazz);
        addGetImplMethodGenerator(clazz);
        return answer;
    }

    protected void addAddImplMethodGenerator(TopLevelClass clazz){
        AbstractServiceImplMethodGenerator methodGeneratorMethodGenerator= new AddMethodImplGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void addDeleteImplMethodGenerator(TopLevelClass clazz){
        AbstractServiceImplMethodGenerator methodGeneratorMethodGenerator= new DeleteMethodImplGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void addUpdateImplMethodGenerator(TopLevelClass clazz){
        AbstractServiceImplMethodGenerator methodGeneratorMethodGenerator= new UpdateMethodImplGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void addGetImplMethodGenerator(TopLevelClass clazz){
        AbstractServiceImplMethodGenerator methodGeneratorMethodGenerator= new GetMethodImplGenerator();
        initializeAndExecuteGenerator(methodGeneratorMethodGenerator,clazz);
    }

    protected void initializeAndExecuteGenerator(
            AbstractServiceImplMethodGenerator methodGenerator,
            TopLevelClass clazz) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(clazz);
    }
}
