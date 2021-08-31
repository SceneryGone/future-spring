package com.future.factory;

import com.future.util.TransactionManager;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-31 17:31
 */
@Slf4j
public class ProxyFactory {

    public static Object wrap(Object target) {
        return proxy(target);
    }

    private static Object proxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("-----方法拦截-----");
                    final Object invoke;
                    try {
                        TransactionManager.getInstance().begin();
                        invoke = method.invoke(target, args);
                        TransactionManager.getInstance().commit();
                    } catch (Exception e) {
                        log.error("call exception : " + e);
                        return e;
                    } finally {
                        TransactionManager.getInstance().rollback();
                    }
                    return invoke;
                });
    }

    private ProxyFactory() {

    }
}
