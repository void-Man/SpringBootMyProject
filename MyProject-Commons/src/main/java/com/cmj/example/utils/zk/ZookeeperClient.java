package com.cmj.example.utils.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/7
 */
public enum ZookeeperClient {
    INSTANCE;

    private static final CuratorFramework CURATORFRAMEWORK;

    static {
        CURATORFRAMEWORK = ZookeeperClientFactory.createSimple(ZookeeperConstants.ZOOKEEPER_ADDRESS);
    }

    /**
     * 获取实例
     *
     * @param
     * @return com.cmj.example.utils.zk.ZookeeperClient
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public static ZookeeperClient getInstance() {
        return INSTANCE;
    }

    /**
     * 创建节点
     *
     * @param path 节点路径
     * @return void
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public String createIfNotExists(String path) throws Exception {
        return createIfNotExistsWithMode(path, CreateMode.PERSISTENT, null);
    }

    /**
     * 创建节点
     *
     * @param path
     * @param mode
     * @return void
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public String createIfNotExistsWithMode(String path, CreateMode mode, byte[] data) throws Exception {
        Stat stat = CURATORFRAMEWORK.checkExists().forPath(path);
        if (Objects.isNull(stat)) {
            return CURATORFRAMEWORK.create()
                    .creatingParentsIfNeeded()
                    .withProtection()
                    .withMode(mode)
                    .forPath(path, data);
        }
        return path;
    }

}
