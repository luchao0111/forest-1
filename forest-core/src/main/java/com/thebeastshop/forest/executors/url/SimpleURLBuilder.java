package com.thebeastshop.forest.executors.url;

import com.thebeastshop.forest.http.ForestRequest;
import org.apache.commons.lang3.StringUtils;


/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-19 14:10
 */
public class SimpleURLBuilder extends URLBuilder {

    @Override
    public String buildUrl(ForestRequest request) {
        String url = request.getUrl();
        String query = request.getQuery();
        if (StringUtils.isNotBlank(query)) {
            url += "?" + query;
        }
        return url;
    }

}
