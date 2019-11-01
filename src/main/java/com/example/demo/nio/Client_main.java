package com.example.demo.nio;

public class Client_main {

    public static void main(String[] args) {
        NioClient nioClient = new NioClient();
        nioClient.init();
        nioClient.lxlistener();
    }
}
