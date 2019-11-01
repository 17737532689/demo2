package com.example.demo.webservice.impl;

import com.example.demo.entity.User;
import com.example.demo.webservice.UserService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService
public class UserServiceImpl implements UserService {


    @Override
    public String sayHi(String name) {
        return "Hi, " + name;
    }

    @Override
    public String work(String work) {
        return "He is working " + work;
    }

    @Override
    public User getUser(User user) {
        user.setName(user.getName() + "-service");
        return user;
    }
}
