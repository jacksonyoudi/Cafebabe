package com.jacksonyoudi.mybatis.config;


import com.jacksonyoudi.mybatis.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;





public class XMLConfigBuilder {
    public Configuration parseConfig(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);

        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();

        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }


        new ComboPooledDataSource()

        return null;
    }

}
