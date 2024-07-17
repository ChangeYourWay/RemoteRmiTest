package com.miaoji.javarmi;

import com.miaoji.remoteclass.ExportObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.codebase", "http://127.0.0.1:8000/");
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        // 获取远程对象的引用
        Services services = (Services) registry.lookup("Services");
        ExportObject exportObject = new ExportObject();
        exportObject.setMessage("hahaha");

        services.sendMessage(exportObject);
    }
}
