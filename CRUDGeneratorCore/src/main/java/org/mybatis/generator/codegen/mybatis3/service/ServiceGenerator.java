package org.mybatis.generator.codegen.mybatis3.service;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.service.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成service接口
 */
public class ServiceGenerator extends AbstractJavaGenerator{
    /**
     * add
     * get
     * delete
     * update
     *
     * @return
     */
    public List<CompilationUnit> getCompilationUnits() {

        List<CompilationUnit> answer=new ArrayList<CompilationUnit>();
        Interface interfaze=new Interface(introspectedTable.getServiceType());
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        addAddMethodGenerator(interfaze);
        addUpdateMethodGenerator(interfaze);
        addDeleteMethodGenerator(interfaze);
        addGetMethodGenerator(interfaze);
        answer.add(interfaze);
        return answer;
    }

    protected void addAddMethodGenerator(Interface interfaze){
        AbstractServiceMethodGenerator methodGenerator=new AddMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator,interfaze);
    }

    protected void addUpdateMethodGenerator(Interface interfaze){
        AbstractServiceMethodGenerator methodGenerator=new UpdateMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator,interfaze);
    }

    protected void addDeleteMethodGenerator(Interface interfaze){
        AbstractServiceMethodGenerator methodGenerator=new DeleteMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator,interfaze);
    }

    protected void addGetMethodGenerator(Interface interfaze){
        AbstractServiceMethodGenerator methodGenerator=new GetMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator,interfaze);
    }

    protected void initializeAndExecuteGenerator(
            AbstractServiceMethodGenerator methodGenerator,
            Interface interfaze) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(interfaze);
    }
}
