package com.example.test;

import com.example.request.ServerRequest;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.publishServer();
    }
}
