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

    private BeanFactory() {

    }

    private static final Map<String, Object> BEAN_MAP = Maps.newHashMap();

    static {
        log.info("begin loading bean...");
        XmlBeanConfigBuilder.parse(BEAN_MAP, "beans.xml");
    }

    public static Object getBean(String beanName) {
        final Object bean = BEAN_MAP.get(beanName);
        if (Objects.isNull(bean)) {
            log.error("not found " + beanName);
            throw new BeanNotFoundException("not found " + beanName);
        }

        return bean;
    }

}
