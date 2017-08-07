package com.thebeastshop.forest.proxy;

import com.thebeastshop.forest.annotation.BaseURL;
import com.thebeastshop.forest.annotation.Request;
import com.thebeastshop.forest.config.ForestConfiguration;
import com.thebeastshop.forest.http.client.GetClient;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-17 19:32
 */
public class TestInterfaceProxyHandler {

    private static ForestConfiguration configuration = ForestConfiguration.configuration();


    @Test
    public void testGetProxyFactory() {
        ProxyFactory<GetClient> getClientProxyFactory = new ProxyFactory<>(configuration, GetClient.class);
        InterfaceProxyHandler<GetClient> interfaceProxyHandler =
                new InterfaceProxyHandler(configuration, getClientProxyFactory, GetClient.class);
        assertEquals(getClientProxyFactory, interfaceProxyHandler.getProxyFactory());
    }

    @BaseURL("http://localhost")
    interface LocalhostBaseURLClient {
    }


    @BaseURL("localhost")
    interface NonProtocolBaseURLClient {
    }


    @BaseURL("")
    interface EmptyBaseURLClient {
    }

    interface RenamedMethodClient {

        @Request(url = "http://localhost/test")
        String test();

        @Request(url = "http://localhost/test/${0}")
        String test(String a);

    }


    @Test
    public void testBaseURL() {
        ProxyFactory<LocalhostBaseURLClient> getClientProxyFactory = new ProxyFactory<>(configuration, LocalhostBaseURLClient.class);
        InterfaceProxyHandler<LocalhostBaseURLClient> interfaceProxyHandler =
                new InterfaceProxyHandler(configuration, getClientProxyFactory, LocalhostBaseURLClient.class);
        assertEquals("http://localhost", interfaceProxyHandler.getBaseURL());

    }

    @Test
    public void testNonProtocolBaseURL() {
        ProxyFactory<NonProtocolBaseURLClient> getClientProxyFactory = new ProxyFactory<>(configuration, NonProtocolBaseURLClient.class);
        InterfaceProxyHandler<NonProtocolBaseURLClient> interfaceProxyHandler =
                new InterfaceProxyHandler(configuration, getClientProxyFactory, NonProtocolBaseURLClient.class);
        assertEquals("http://localhost", interfaceProxyHandler.getBaseURL());
    }



    @Test
    public void testEmptyBaseURL() {
        ProxyFactory<EmptyBaseURLClient> getClientProxyFactory = new ProxyFactory<>(configuration, EmptyBaseURLClient.class);
        InterfaceProxyHandler<EmptyBaseURLClient> interfaceProxyHandler =
                new InterfaceProxyHandler(configuration, getClientProxyFactory, EmptyBaseURLClient.class);
        assertNull(interfaceProxyHandler.getBaseURL());
    }


    @Test
    public void testRenamedMethod() {
        ProxyFactory<RenamedMethodClient> getClientProxyFactory = new ProxyFactory<>(configuration, RenamedMethodClient.class);
        InterfaceProxyHandler<RenamedMethodClient> interfaceProxyHandler =
                new InterfaceProxyHandler(configuration, getClientProxyFactory, RenamedMethodClient.class);
    }


}
