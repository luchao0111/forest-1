package org.forest.proxy;

import org.forest.config.ForestConfiguration;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gongjun[dt_flys@hotmail.com]
 * @since 2016-03-25 18:17
 */
public class ProxyFactory<T> {
    private Map<Class<T>, T> instanceCache = new HashMap<Class<T>, T>();
    private ForestConfiguration configuration;
    private Class<T> interfaceClass;

    public ProxyFactory(ForestConfiguration configuration, Class<T> interfaceClass) {
        this.configuration = configuration;
        this.interfaceClass = interfaceClass;
    }

    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public synchronized T createInstance() {
        T instance = instanceCache.get(interfaceClass);
        if (instance != null) {
            return instance;
        }
        InterfaceProxyHandler<T> interfaceProxyHandler = new InterfaceProxyHandler<T>(configuration, this, interfaceClass);
        instance = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, interfaceProxyHandler);
        instanceCache.put(interfaceClass, instance);
        return instance;
    }

}
