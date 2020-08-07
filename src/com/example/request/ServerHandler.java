package com.example.request;

import com.example.info.RpcInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ServerHandler implements Runnable{
    private Socket socket;//封装好了tcp连接

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcInfo rpcInfo = (RpcInfo) objectInputStream.readObject();//获取
            Class forName = Class.forName(rpcInfo.getPackageName() + "." + rpcInfo.getClassName());
            Class[] classes = new Class[rpcInfo.getPara().length];
            for (int i = 0; i < classes.length; i++){
                classes[i] = rpcInfo.getPara()[i].getClass();
            }
            Method method = forName.getMethod(rpcInfo.getMethodName(), classes);
            method.invoke(forName.newInstance(),rpcInfo.getPara());
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
