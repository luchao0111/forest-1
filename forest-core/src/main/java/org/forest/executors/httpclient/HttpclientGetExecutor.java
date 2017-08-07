package org.forest.executors.httpclient;

import org.apache.http.client.methods.HttpGet;
import org.forest.executors.httpclient.request.HttpclientRequestSender;
import org.forest.executors.httpclient.response.HttpclientResponseHandler;
import org.forest.executors.url.URLBuilder;
import org.forest.http.ForestRequest;

/**
 * @author gongjun
 * @since 2016-05-24
 */
public class HttpclientGetExecutor extends AbstractHttpclientExecutor<HttpGet> {

    private final static HttpclientRequestProvider<HttpGet> httpGetProvider =
            new HttpclientRequestProvider<HttpGet>() {
        @Override
        public HttpGet getRequest(String url) {
            return new HttpGet(url);
        }
    };

    @Override
    protected HttpclientRequestProvider<HttpGet> getRequestProvider() {
        return httpGetProvider;
    }

    @Override
    protected URLBuilder getURLBuilder() {
        return URLBuilder.getQueryableURLBuilder();
    }

    public HttpclientGetExecutor(ForestRequest request, HttpclientResponseHandler httpclientResponseHandler, HttpclientRequestSender requestSender) {
        super(request, httpclientResponseHandler, requestSender);
    }


}
