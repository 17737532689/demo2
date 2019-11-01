package com.example.demo.webservice;

import com.example.demo.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface UserService {
    @WebMethod
    public String sayHi(String name);

    @WebMethod
    public String work(String work);

    @WebMethod
    public User getUser(User user);
}
