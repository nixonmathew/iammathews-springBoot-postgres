package com.self.portfolio.trial;

public class GenericClass<T, R> {

    public GenericClass() {
    }

    public GenericClass(T t, R r) {
        System.out.println(t.toString() + r.toString());

    }

}