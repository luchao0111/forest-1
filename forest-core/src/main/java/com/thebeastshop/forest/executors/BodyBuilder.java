package com.thebeastshop.forest.executors;

import com.thebeastshop.forest.http.ForestRequest;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-19 14:50
 */
public interface BodyBuilder<R> {

    void buildBody(R req, ForestRequest request);
}
