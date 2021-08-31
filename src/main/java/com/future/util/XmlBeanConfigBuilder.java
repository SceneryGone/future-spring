package com.future.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:29
 */
@Slf4j
public class XmlBeanConfigBuilder {

    private static final XmlBeanConfigBuilder INSTANCE = new XmlBeanConfigBuilder();

    private XmlBeanConfigBuilder() {

    }

    public static XmlBeanConfigBuilder getInstance() {
        return INSTANCE;
    }

    public void parse(Map<String, Object> beanMap, String path) {
        InputStream inputStream = Resources.getResourceAsStream(path);
        final Document document;
        try {
            document = new SAXReader().read(inputStream);
            // 解析beans.xml
            Element rootElement = document.getRootElement();
            final List<Element> beanList = rootElement.selectNodes("//bean");
            for (Element element : beanList) {
                final String beanId = element.attributeValue("id");
                final String value = element.attributeValue("class");
                final Class<?> aClass = Class.forName(value);
                final Object bean = aClass.newInstance();
                beanMap.put(beanId, bean);
            }

            // 实例化之后，维护对象的依赖关系
            final List<Element> properties = rootElement.selectNodes("//property");
            properties.forEach(property -> {
                final String name = property.attributeValue("name");
                final String ref = property.attributeValue("ref");

                // 获取父节点
                final Element parent = property.getParent();
                final String parentId = parent.attributeValue("id");
                final Object parentBean = beanMap.get(parentId);

                // 遍历父对象的set方法 找到需要注入的方法
                final Method[] methods = parentBean.getClass().getMethods();
                for (Method method : methods) {
                    if (StringUtils.equalsIgnoreCase("set" + name, method.getName())) {
                        final Object refBean = beanMap.get(ref);
                        try {
                            method.invoke(parentBean, refBean);
                        } catch (Exception e) {
                            log.info("call method error : ", e);
                        }
                    }
                }

                // 把parent重新放到map中
                beanMap.put(parentId, parentBean);
            });
        } catch (Exception e) {
            log.error("parse bean xml error: ", e);
        }

        log.info("init beanMap success");
    }


}
