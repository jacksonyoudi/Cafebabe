package com.youdi.bigtable;

import java.util.LinkedList;
import java.util.List;

public class One {
    public static void main(String[] args) {

        List<String> list = new LinkedList<String>();
        list.add("a");

        HbaseUtils.createTable("hello2", list);
    }
}
