package com.thebeastshop.forest.http;

import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.exceptions.ForestNetworkException;
import com.thebeastshop.forest.exceptions.ForestRuntimeException;
import com.thebeastshop.forest.callback.OnError;
import com.thebeastshop.forest.callback.OnSuccess;
import com.thebeastshop.forest.http.client.GetClient;
import com.thebeastshop.forest.mock.AsyncGetMockServer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-11 15:02
 */
public class TestAsyncGetClient {

    private final static Logger log = LoggerFactory.getLogger(TestAsyncGetClient.class);

    @Rule
    public AsyncGetMockServer server = new AsyncGetMockServer(this);

    private static ForestConfiguration configuration;

    private static GetClient getClient;


    @BeforeClass
    public static void prepareClient() {
        configuration = ForestConfiguration.configuration();
        getClient = configuration.createInstance(GetClient.class);
    }


    @Before
    public void prepareMockServer() {
        server.initServer();
    }



    @Test
    public void testAsyncSimpleGet() throws InterruptedException {
        final AtomicBoolean success = new AtomicBoolean(false);
        getClient.asyncSimpleGet(
                new OnSuccess<String>() {
                    @Override
                    public void onSuccess(String data, ForestRequest request, ForestResponse response) {
                        log.info("data: " + data);
                        success.set(true);
                        assertEquals(AsyncGetMockServer.EXPECTED, data);
                    }
                });
        log.info("send async get request");
        assertFalse(success.get());
        Thread.sleep(2000L);
        assertTrue(success.get());
    }


    @Test
    public void testAsyncSimpleGetWithFuture() throws ExecutionException, InterruptedException {
        Future<String> future = getClient.asyncSimpleGetWithFuture();
        log.info("send async get request");
        assertNotNull(future);
        String data = future.get();
        log.info("data: " + data);
        assertNotNull(data);
        assertEquals(AsyncGetMockServer.EXPECTED, data);
    }


    @Test
    public void testAsyncVarParamGet() throws InterruptedException, ExecutionException {
        final AtomicBoolean success = new AtomicBoolean(false);
        Future<String> future = getClient.asyncVarParamGet("foo", new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object data, ForestRequest request, ForestResponse response) {
                log.info("data: " + data);
                success.set(true);
                assertEquals(AsyncGetMockServer.EXPECTED, data);
            }
        }, new OnError() {
            @Override
            public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
            }
        });
        log.info("send async get request");
        assertFalse(success.get());
        assertNotNull(future);
        Thread.sleep(2000L);
        assertTrue(success.get());
        assertTrue(future.isDone());
        assertEquals(AsyncGetMockServer.EXPECTED, future.get());
    }


    @Test
    public void testAsyncVarParamGetError() throws InterruptedException {
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean error = new AtomicBoolean(false);
        Future<String> future = getClient.asyncVarParamGet(
                "error param",
                new OnSuccess<Object>() {
                    @Override
                    public void onSuccess(Object data, ForestRequest request, ForestResponse response) {
                        error.set(false);
                        success.set(true);
                    }
                }, new OnError() {
                    @Override
                    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
                        error.set(true);
                        success.set(false);
                        assertTrue(ex instanceof ForestNetworkException);
                        int statusCode = ((ForestNetworkException) ex).getStatusCode();
                        log.error("status code = " + statusCode);
                        assertEquals(404, statusCode);
                    }
                });
        log.info("send async get request");
        assertFalse(error.get());
        assertNotNull(future);
        Thread.sleep(2000L);
        assertFalse(success.get());
        assertTrue(error.get());

    }

}
