package com.future.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
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

    public static void parse(Map<String, Object> beanMap, String path) {
        InputStream inputStream = Resources.getResourceAsStream(path);
        final Document document;
        try {
            document = new SAXReader().read(inputStream);
            // 解析beans.xml
            Element rootElement = document.getRootElement();
            final List<Element> beanList = rootElement.selectNodes("//bean");
            for (Element element : beanList) {
                final String name = element.attributeValue("id");
                final String value = element.attributeValue("class");
                final Class<?> aClass = Class.forName(value);
                final Object newInstance = aClass.newInstance();
                beanMap.put(name, newInstance);
            }
        } catch (Exception e) {
            log.error("parse bean xml error: ", e);
        }

        log.info("init beanMap success");
    }


}
