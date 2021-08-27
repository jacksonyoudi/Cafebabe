package com.jacksonyoudi.mybatis.config;

import com.jacksonyoudi.mybatis.utils.ParameterMapping;

import java.util.List;

public class BoundSql {
    private String sqlText;
    private List<ParameterMapping> parameterMappingList;

    public BoundSql() {
    }

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
