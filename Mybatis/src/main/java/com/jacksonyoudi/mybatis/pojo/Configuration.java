package com.jacksonyoudi.mybatis.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private DataSource dataSource;

    Map<String, MapperStatement> mapperStatement = new HashMap<String, MapperStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getMapperStatement() {
        return mapperStatement;
    }

    public void setMapperStatement(Map<String, MapperStatement> mapperStatement) {
        this.mapperStatement = mapperStatement;
    }
}
