package com.thebeastshop.forest.http;

import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.mock.GetMockServer;
import com.thebeastshop.forest.http.client.BaseURLClient;
import com.thebeastshop.forest.http.client.BaseURLVarClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.assertEquals;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-17 16:12
 */
public class TestBaseURLClient {


    private final static Logger log = LoggerFactory.getLogger(TestGetClient.class);

    @Rule
    public GetMockServer server = new GetMockServer(this);

    private static ForestConfiguration configuration;

    private static BaseURLClient baseURLClient;

    private static BaseURLVarClient baseURLVarClient;

    @BeforeClass
    public static void prepareClient() {
        configuration = ForestConfiguration.configuration();
        configuration.setVariableValue("baseURL", "http://localhost:5000/");
        baseURLClient = configuration.createInstance(BaseURLClient.class);
        baseURLVarClient = configuration.createInstance(BaseURLVarClient.class);
    }


    @Before
    public void prepareMockServer() {
        server.initServer();
    }

    @Test
    public void testBaseURL() {
        String result = baseURLClient.simpleGet();
        assertEquals(GetMockServer.EXPECTED, result);
    }

    @Test
    public void testBaseURLVar() {
        String result = baseURLVarClient.simpleGet();
        assertEquals(GetMockServer.EXPECTED, result);
    }

}
