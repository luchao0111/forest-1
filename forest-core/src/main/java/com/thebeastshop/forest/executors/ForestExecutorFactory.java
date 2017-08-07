package com.thebeastshop.forest.executors;

import com.thebeastshop.forest.handler.ResponseHandler;
import com.thebeastshop.forest.http.ForestRequest;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-04-20 18:22
 */
public interface ForestExecutorFactory {

    HttpExecutor create(ForestRequest request, ResponseHandler responseHandler);

}
