package com.thebeastshop.forest.executors.url;


import com.thebeastshop.forest.http.ForestRequest;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-19 14:09
 */
public abstract class URLBuilder {

    private final static URLBuilder simpleInstance = new SimpleURLBuilder();

    private final static URLBuilder queryableInstance = new QueryableURLBuilder();

    public abstract String buildUrl(ForestRequest request);

    public static URLBuilder getSimpleURLBuilder() {
        return simpleInstance;
    }

    public static URLBuilder getQueryableURLBuilder() {
        return queryableInstance;
    }

}
