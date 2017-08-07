package org.forest.mapping;

import org.forest.config.VariableScope;
import org.forest.reflection.ForestMethod;

/**
 * Created by Administrator on 2016/5/26.
 */
public class MappingReference extends MappingExpr {

    private String name;

    public MappingReference(VariableScope variableScope, String name) {
        this.variableScope = variableScope;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object render(Object[] args) {
        MappingVariable variable = variableScope.getVariable(name);
        if (variable != null) {
            return args[variable.getIndex()];
        }
        return variableScope.getVariableValue(name);
    }

    @Override
    public String toString() {
        return "[Refer: " + name + "]";
    }
}
