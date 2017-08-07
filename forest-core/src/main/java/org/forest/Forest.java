/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Jun Gong
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.forest;

import com.fasterxml.jackson.databind.util.Annotations;
import org.apache.commons.lang3.AnnotationUtils;
import org.forest.exceptions.ForestRuntimeException;
import org.forest.interceptor.Interceptor;
import org.forest.interceptor.InterceptorChain;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-15 11:18
 */
public class Forest {

    private final static Map<Class, Interceptor> interceptorMap = new ConcurrentHashMap<>();

    private static InterceptorChain interceptorChain = new InterceptorChain();

    public static InterceptorChain getInterceptorChain() {
        return interceptorChain;
    }

    public static <T extends Interceptor> Interceptor getInterceptor(Class<T> clazz) {
        Interceptor interceptor = interceptorMap.get(clazz);
        if (interceptor == null) {
            try {
                interceptor = clazz.newInstance();
                interceptorMap.put(clazz, interceptor);
            } catch (InstantiationException e) {
                throw new ForestRuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new ForestRuntimeException(e);
            }
        }
        return interceptor;
    }

}
