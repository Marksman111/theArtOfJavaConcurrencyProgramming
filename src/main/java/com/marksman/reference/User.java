package com.marksman.reference;

/**
 * @author weilb
 * @date 2018/7/21
 * @description
 */
public class User {

    private String name;
    private int age;

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}