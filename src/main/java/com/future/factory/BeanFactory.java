package com.future.factory;

import com.future.exception.BeanNotFoundException;
import com.future.util.XmlBeanConfigBuilder;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * 功能描述: 生产对象-反射
 *
 * @author future
 * @date 2021-08-30 16:05
 */
@Slf4j
public class BeanFactory {

    private static final Map<String, Object> BEAN_MAP = Maps.newHashMap();

    private static final BeanFactory INSTANCE = new BeanFactory();

    public static BeanFactory getInstance() {
        return INSTANCE;
    }

    static {
        log.info("begin loading bean...");
        XmlBeanConfigBuilder.getInstance().parse(BEAN_MAP, "beans.xml");
    }

    public Object getBean(String beanId) {
        final Object bean = BEAN_MAP.get(beanId);
        if (Objects.isNull(bean)) {
            log.error("not found " + beanId);
            throw new BeanNotFoundException(beanId + " not found ");
        }

        return bean;
    }

    private BeanFactory() {

    }
}
