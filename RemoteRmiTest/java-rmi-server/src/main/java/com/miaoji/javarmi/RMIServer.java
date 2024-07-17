// RMIServer2.java
package com.miaoji.javarmi;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String[] args) {
        try {
            try {
                // 配置 java.security.policy
                System.setProperty("java.security.policy", RMIServer.class.getClassLoader().getResource("policyfile.txt").toString());
                // 配置 Java SecurityManager
                System.setSecurityManager(new RMISecurityManager());
                // 设置 java.rmi.server.useCodebaseOnly 为 false
                System.setProperty("java.rmi.server.useCodebaseOnly", "false");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 创建 Registry
            Registry registry = LocateRegistry.createRegistry(1099);
            // 实例化服务端远程对象
            ServicesImpl obj = new ServicesImpl();
            // 没有继承 UnicastRemoteObject 时需要使用静态方法 exportObject 处理
            Services services = (Services) UnicastRemoteObject.exportObject(obj, 0);
            // 绑定远程对象到 Registry
            registry.rebind("Services", services);

            System.out.println("java RMI registry created. port on 1099...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}