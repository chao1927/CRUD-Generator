package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractGenerator;

public abstract class AbstractControllerMethodGenerator extends AbstractGenerator {
    public abstract void addInterfaceElements(TopLevelClass clazz);
}
