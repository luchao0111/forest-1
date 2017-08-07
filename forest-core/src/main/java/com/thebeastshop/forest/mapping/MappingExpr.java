package com.thebeastshop.forest.mapping;

import com.thebeastshop.forest.config.VariableScope;

/**
 * @author gongjun
 * @since 2016-05-04
 */
public abstract class MappingExpr {

    protected VariableScope variableScope;

    public Object render(Object[] args) {
        return null;
    }
}
