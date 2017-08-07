package org.forest.executors.httpclient.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.forest.exceptions.ForestRuntimeException;
import org.forest.executors.httpclient.conn.HttpclientConnectionManager;
import org.forest.executors.httpclient.response.HttpclientForestResponseFactory;
import org.forest.executors.httpclient.response.HttpclientResponseHandler;
import org.forest.http.ForestRequest;
import org.forest.http.ForestResponse;
import org.forest.http.ForestResponseFactory;

import java.io.IOException;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-19 20:16
 */
public class SyncHttpclientRequestSender extends AbstractHttpclientRequestSender {

    private HttpClient client;

    public SyncHttpclientRequestSender(HttpclientConnectionManager connectionManager, ForestRequest request) {
        super(connectionManager, request);
    }

    protected HttpClient getHttpClient() {
        HttpClient client = connectionManager.getHttpClient();
        setupHttpClient(client);
        return client;
    }

    protected void setupHttpClient(HttpClient client) {
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, request.getTimeout());
        if (client instanceof DefaultHttpClient) {
            ((DefaultHttpClient) client).getCookieSpecs().register("default", defaultCookieSF);
            client.getParams().setParameter(ClientPNames.COOKIE_POLICY, "default");
        }
    }

    private final static CookieSpecFactory defaultCookieSF = new CookieSpecFactory() {
        public CookieSpec newInstance(HttpParams params) {
            return new BrowserCompatSpec() {
                @Override
                public void validate(Cookie cookie, CookieOrigin origin)
                        throws MalformedCookieException {
                }
            };
        }
    };



    public static void logResponse(ForestRequest request, HttpClient client, HttpRequestBase httpReq, ForestResponse response) {
        if (!request.isLogEnable()) return;
        logContent("Response: Status=" + response.getStatusCode());
        if (response.isSuccess()) {
            logContent("Response: Content=" + response.getContent());
        }
    }


    @Override
    public void sendRequest(ForestRequest request, HttpclientResponseHandler responseHandler, HttpUriRequest httpRequest)
            throws IOException {
        HttpResponse httpResponse = null;
        client = getHttpClient();
        httpResponse = client.execute(httpRequest);
        ForestResponseFactory forestResponseFactory = new HttpclientForestResponseFactory();
        ForestResponse response = forestResponseFactory.createResponse(request, httpResponse);
        logResponse(request, client, (HttpRequestBase) httpRequest, response);
        try {
            responseHandler.handleSync(httpResponse, response);
        } catch (Exception ex) {
            if (ex instanceof ForestRuntimeException) {
                throw ex;
            }
            else {
                throw new ForestRuntimeException(ex);
            }
        }
    }

}
