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

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object wrap(Object target) {
        return proxy(target);
    }

    private Object proxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("-----方法拦截-----");
                    final Object invoke;
                    try {
                        transactionManager.begin();
                        invoke = method.invoke(target, args);
                        transactionManager.commit();
                    } catch (Exception e) {
                        log.error("call exception : " + e);
                        return e;
                    } finally {
                        transactionManager.rollback();
                    }
                    return invoke;
                });
    }

}
