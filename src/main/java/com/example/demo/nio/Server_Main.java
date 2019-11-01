package com.example.demo.nio;

public class Server_Main {
    public static void main(String[] args) {

        //启动服务器

        NioServer nioServer = new NioServer();

        nioServer.init();

        nioServer.lxlistener();

        System.out.println("聊天室已经启动");
    }
}
