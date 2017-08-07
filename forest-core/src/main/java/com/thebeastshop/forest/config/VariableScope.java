package com.thebeastshop.forest.config;

import com.thebeastshop.forest.mapping.MappingVariable;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-17 14:44
 */
public interface VariableScope {

    Object getVariableValue(String name);

    MappingVariable getVariable(String name);

    ForestConfiguration getConfiguration();
}
