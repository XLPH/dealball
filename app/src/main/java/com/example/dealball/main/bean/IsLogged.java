package com.example.dealball.main.bean;

public enum IsLogged {

    MARK("isLogged");


    private final String name;

    IsLogged(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
