package com.thebeastshop.forest.mapping;

/**
 * Created by Administrator on 2016/5/17.
 */
public class MappingIndex extends MappingExpr {

    private final Integer index;

    public MappingIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "[Index: " + index + "]";
    }
}
