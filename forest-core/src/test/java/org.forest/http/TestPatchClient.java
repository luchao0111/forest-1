package org.forest.http;

import org.forest.config.ForestConfiguration;
import org.forest.http.client.PatchClient;
import org.forest.mock.PatchMockServer;
import org.forest.mock.PutMockServer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-11 17:13
 */
public class TestPatchClient {

    private final static Logger log = LoggerFactory.getLogger(TestPatchClient.class);

    @Rule
    public PatchMockServer server = new PatchMockServer(this);

    private static ForestConfiguration configuration;

    private static PatchClient patchClient;


    @BeforeClass
    public static void prepareClient() {
        configuration = ForestConfiguration.configuration();
        patchClient = configuration.createInstance(PatchClient.class);
    }

    @Before
    public void prepareMockServer() {
        server.initServer();
    }

    @Test
    public void testSimplePut() {
        String result = patchClient.simplePut();
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PutMockServer.EXPECTED, result);
    }

    @Test
    public void testTextParamPut() {
        String result = patchClient.textParamPut("foo", "123456");
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PutMockServer.EXPECTED, result);
    }

    @Test
    public void testAnnParamPut() {
        String result = patchClient.annParamPut("foo", "123456");
        log.info("response: " + result);
        assertNotNull(result);
        assertEquals(PutMockServer.EXPECTED, result);
    }


}
