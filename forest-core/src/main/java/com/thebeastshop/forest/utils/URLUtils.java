package com.thebeastshop.forest.utils;

import com.thebeastshop.forest.exceptions.ForestRuntimeException;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-17 16:35
 */
public final class URLUtils {

    private URLUtils() {
    }

    public static boolean hasProtocol(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    public static void checkBaseURL(String baseUrl) {
        if (!hasProtocol(baseUrl)) {
            throw new ForestRuntimeException("base url has no protocol: " + baseUrl);
        }
    }

    public static String getValidBaseURL(String baseUrl) {
        if (baseUrl.endsWith("/")) {
            int i = baseUrl.length() - 1;
            do {
                char ch = baseUrl.charAt(i);
                if (ch != '/') {
                    break;
                }
                i--;
            } while (i > 0);
            return baseUrl.substring(0, i + 1);
        }
        return baseUrl;
    }


    public static String getValidURL(String baseURL, String uri) {
        if (!URLUtils.hasProtocol(uri)) {
            if (StringUtils.isNotEmpty(baseURL)) {
                if (baseURL.endsWith("/")) {
                    baseURL = getValidBaseURL(baseURL);
                }
                if (!uri.startsWith("/")) {
                    uri = "/" + uri;
                }
                return baseURL + uri;
            }
            else {
                return  "http://" + uri;
            }
        }
        return uri;
    }

}
