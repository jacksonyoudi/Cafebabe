package com.youdi.gof.builder;

public class Director {
    public void construct(Builder builder) {
        builder.buildpartA(1);
        builder.buildpartB("test-test");
        builder.buildpartC(2);
    }

    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilder();
        director.construct(builder);
        System.out.println(builder.getResult());
    }
}
