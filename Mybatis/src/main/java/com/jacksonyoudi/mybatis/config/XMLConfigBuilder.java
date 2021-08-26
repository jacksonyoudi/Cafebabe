package com.jacksonyoudi.mybatis.config;


import com.jacksonyoudi.mybatis.io.Resources;
import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class XMLConfigBuilder {
    private Configuration config;

    public XMLConfigBuilder() {
        this.config = new Configuration();
    }

    public Configuration parseConfig(InputStream in) throws DocumentException, PropertyVetoException, IOException {
        Document document = new SAXReader().read(in);

        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();

        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }


        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("driverClass"));
        dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        this.config.setDataSource(dataSource);

        //  xml
        List<Element> mapperList = rootElement.selectNodes("//mapper");

        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);

            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(config);

            xmlMapperBuilder.parse(resourceAsStream);
        }


        return config;
    }

}
