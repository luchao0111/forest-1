package com.thebeastshop.forest.http;

import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.http.client.PostClient;
import com.thebeastshop.forest.http.model.UserParam;
import com.thebeastshop.forest.mock.PostMockServer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-11 16:55
 */
public class TestPostClient {

    private final static Logger log = LoggerFactory.getLogger(TestPostClient.class);

    @Rule
    public PostMockServer server = new PostMockServer(this);

    private static ForestConfiguration configuration;

    private static PostClient postClient;


    @BeforeClass
    public static void prepareClient() {
        configuration = ForestConfiguration.configuration();
        postClient = configuration.createInstance(PostClient.class);
    }


    @Before
    public void prepareMockServer() {
        server.initServer();
    }

    @Test
    public void testSimplePost() {
        String result = postClient.simplePost();
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PostMockServer.EXPECTED, result);
    }

    @Test
    public void testTextParamPost() {
        String result = postClient.textParamPost("foo", "123456");
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PostMockServer.EXPECTED, result);
    }

    @Test
    public void testAnnParamPost() {
        String result = postClient.textParamPost("foo", "123456");
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PostMockServer.EXPECTED, result);
    }

    @Test
    public void testVarParamPost() {
        String result = postClient.varParamPost("foo", "123456");
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PostMockServer.EXPECTED, result);
    }

    @Test
    public void testModelParamPost() {
        UserParam userParam = new UserParam();
        userParam.setUsername("foo");
        userParam.setPassword("123456");
        String result = postClient.modelParamPost(userParam);
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PostMockServer.EXPECTED, result);
    }


/*
    @Test
    public void testModelParamNumPost() {
        UserParam userParam = new UserParam();
        userParam.setUsername("foo");
        userParam.setPassword("123456");
        String result = postClient.modelParamNumPost(userParam);
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PostMockServer.EXPECTED, result);
    }
*/


}
