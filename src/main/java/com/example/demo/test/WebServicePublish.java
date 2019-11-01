package com.example.demo.test;


import com.example.demo.webservice.impl.UserServiceImpl;

import javax.xml.ws.Endpoint;

public class WebServicePublish {
    public static void main(String[] args) {
        String wsAddress = "http://localhost:6868/01-ws-java-server/ws";
        Endpoint endpoint = Endpoint.publish(wsAddress, new UserServiceImpl());
        System.out.println("webservice发布成功：" + endpoint.isPublished());
    }
}
