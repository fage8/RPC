package com.example.request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerRequest {
    public void publishServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(666);
        ExecutorService executorService = Executors.newFixedThreadPool(5);//创建线程池
        while (true){//BIO阻塞
            System.out.println("wait");
            Socket accept = serverSocket.accept();//发布出去
            executorService.execute(new ServerHandler(accept));//在线程池中处理接收到的服务
            System.out.println("connected");
        }
    }
}
