package com.cmj.example.vo;

import org.apache.curator.framework.CuratorFramework;

/**
 * @author mengjie_chen
 * @description 节点的Zk协调客户端（单例）
 * @date 2020/11/5
 */
public enum ImWorker {

    /**
     * host和port可以从配置文件中读取
     */
    INstance("",0);

    /**
     * zk客户端
     */
    private final CuratorFramework curatorFramework;
    /**
     * zk里面注册的当前节点路径
     */
    private final String pathRegistered;
    /**
     * 当前节点
     */
    private final ImNode currentNode;

    ImWorker(String host, Integer port) {
        this.curatorFramework = null;
        this.pathRegistered = null;
        this.currentNode = new ImNode(host, port);
    }
}
