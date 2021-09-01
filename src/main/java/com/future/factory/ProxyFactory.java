package com.future.factory;

import com.future.util.TransactionManager;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
                new TransactionProxy(transactionManager, target));
    }

    public static class TransactionProxy implements InvocationHandler {

        private final TransactionManager transactionManager;

        private final Object target;

        public TransactionProxy(TransactionManager transactionManager, Object target) {
            this.transactionManager = transactionManager;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final Object invoke;
            try {
                transactionManager.begin();
                invoke = method.invoke(target, args);
                transactionManager.commit();
            } catch (Exception e) {
                log.error("call exception : " + e);
                transactionManager.rollback();
                throw e;
            }
            return invoke;
        }
    }
}
