package com.youdi.gof.builder;

public class ConcreteBuilder implements Builder {
    private int partA;
    private String partB;
    private int partC;

    @Override
    public void buildpartA(int partA) {
        this.partA = partA;
    }

    @Override
    public void buildpartB(String partB) {
        this.partB = partB;
    }

    @Override
    public void buildpartC(int partC) {
        this.partC = partC;
    }

    @Override
    public Product getResult() {
        return new Product(partA, partB, partC);
    }
}
