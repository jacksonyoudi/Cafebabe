package com.youdi.gof.builder;

public interface Builder {
    void buildpartA(int partA);

    void buildpartB(String partB);

    void buildpartC(int partC);

    Product getResult();
}