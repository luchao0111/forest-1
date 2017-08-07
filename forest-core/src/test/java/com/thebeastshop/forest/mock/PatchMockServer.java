package com.thebeastshop.forest.mock;

import org.apache.http.HttpHeaders;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-17 16:08
 */
public class PatchMockServer extends MockServerRule {

    public final static String EXPECTED = "{\"status\": \"ok\"}";

    public PatchMockServer(Object target) {
        super(target, 5000);
    }

    public void initServer() {
        MockServerClient mockClient = new MockServerClient("localhost", 5000);
        mockClient.when(
                request()
                        .withPath("/hello")
                        .withMethod("PATCH")
                        .withHeader(new Header(HttpHeaders.ACCEPT, "text/plan"))
                        .withBody("username=foo&password=123456")
        ).respond(
                response()
                        .withStatusCode(200)
                        .withBody(EXPECTED)
        );
    }

}
