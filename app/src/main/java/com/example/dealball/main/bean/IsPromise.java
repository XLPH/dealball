package com.example.dealball.main.bean;

public enum IsPromise {
    PROMISE("isPromise");

    private final String name;

    private IsPromise(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
