package com.example.demo.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

public class NioClient {

    //发送消息的Buffer
    private ByteBuffer sendMsg = ByteBuffer.allocate(1024);

    private ByteBuffer receiveMsg = ByteBuffer.allocate(1024);

    private Selector selector;
    //初始化

    public void init() {


        try {
            SocketChannel socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);

            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            //连接服务器

            socketChannel.connect(new InetSocketAddress("localhost", 999));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //轮询查询事件
    public void lxlistener() {
        while (true) {
            try {
                selector.select();//获取时间

                Set<SelectionKey> keys = selector.selectedKeys();
                //遍历所有的事件
                keys.forEach(k -> parse(k));
                keys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //处理四大事件
    private void parse(SelectionKey key) {
        if (key.isConnectable()) {
            try {

                SocketChannel schannel = (SocketChannel) key.channel();
                if (schannel.isConnectionPending()) {
                    //连接是否可用

                    schannel.finishConnect();//确认连接

                    System.out.println("连接成功");

                    sendMsg(schannel);
                }
                schannel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.isReadable()) {

            //获取客户端的消息
            SocketChannel schannel = (SocketChannel) key.channel();
            receiveMsg.clear();//清空缓冲区

            int blen = 0;
            try {
                blen = schannel.read(receiveMsg);

                if (blen > 0) {
                    receiveMsg.flip();//切换到读写模式

                    String rmsg = new String(receiveMsg.array(), 0, blen);

                    System.out.println("接收消息" + rmsg);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


    //发送消息 type:类型 1转发 2单发
    private void sendMsg(SocketChannel socketChannel) {
        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入要发送的内容");
                String s = scanner.next();
                sendMsg.clear();
                sendMsg.put(s.getBytes());
                sendMsg.flip();//切换

                try {
                    socketChannel.write(sendMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}