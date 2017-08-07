package org.forest.request;

import junit.framework.Assert;
import org.forest.config.ForestConfiguration;
import org.forest.http.ForestRequest;
import org.junit.Test;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2017-05-09 23:10
 */
public class TestRequest {

    @Test
    public void testDefaultRequst() {
        ForestConfiguration configuration = ForestConfiguration.configuration();
        ForestRequest request = new ForestRequest(configuration);
        Assert.assertEquals(configuration, request.getConfiguration());
        Assert.assertEquals(configuration.getTimeout().intValue(),
                request.getTimeout());
        Assert.assertEquals(0, request.getRetryCount());
    }
}
