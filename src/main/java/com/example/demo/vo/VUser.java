package com.example.demo.vo;

public class VUser {

    private String name;
    private int age;

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

    public VUser(){}
    public VUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "VUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
