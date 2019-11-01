package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NioServer {

    //发送消息的Buffer
    private ByteBuffer sendMsg = ByteBuffer.allocate(1024);

    private ByteBuffer receiveMsg = ByteBuffer.allocate(1024);

    Map<String, SocketChannel> clients = new HashMap<>();

    private Selector selector;
    //初始化

    public void init() {


        try {
            ServerSocketChannel serverScockerChannel = ServerSocketChannel.open();
            serverScockerChannel.configureBlocking(false);
            ServerSocket serverSocket = serverScockerChannel.socket();

            serverSocket.bind(new InetSocketAddress(999));


            selector = Selector.open();

            serverScockerChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端已经启动");


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
        if (key.isAcceptable()) {

            System.out.println("客户端连接" + key.toString());
            try {

                ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();


                SocketChannel schannel = socketChannel.accept();

                //设置IO的阻塞模式为true,非阻塞false
                schannel.configureBlocking(false);
                //加入到线程队列
                schannel.register(selector, SelectionKey.OP_READ);
                clients.put(schannel.socket().getInetAddress().getHostAddress(), schannel);
                sendMsg("欢迎加入高新聊天室", schannel, 1);
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


                    String m = schannel.getRemoteAddress() + "说" + rmsg;
                    System.out.println(m);
                    sendMsg(m, schannel, 2);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    //发送消息 type:类型 1转发 2单发
    private void sendMsg(String msg, SocketChannel socketChannel, int type) {
        if (type == 1) {
            if (clients.size() > 0) {
                for (String s : clients.keySet()) {
                    SocketChannel sc = clients.get(s);

                    if (!sc.equals(socketChannel)) {
                        sendMsg.clear();
                        sendMsg.put(msg.getBytes());
                        sendMsg.flip();//切换
                        try {
                            sc.write(sendMsg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            sendMsg.clear();
            sendMsg.put(msg.getBytes());
            sendMsg.flip();//切换
            try {
                System.out.println("指针" + sendMsg.position());
                socketChannel.write(sendMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
