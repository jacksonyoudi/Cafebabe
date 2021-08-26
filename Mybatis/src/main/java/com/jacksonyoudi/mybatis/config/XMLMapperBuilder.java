package com.jacksonyoudi.mybatis.config;

import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.jacksonyoudi.mybatis.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private Configuration config;

    public XMLMapperBuilder(Configuration config) {
        this.config = config;
    }

    public void parse(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {

            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");

            String sqlText = element.getTextTrim();

            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setResultType(resultType);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setSql(sqlText);

            String s = namespace + "." + id;
            config.getMapperStatement().put(s, mapperStatement);
        }


    }
}
