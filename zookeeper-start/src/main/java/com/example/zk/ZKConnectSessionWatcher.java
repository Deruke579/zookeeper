package com.example.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Kelly on 2018/11/25.
 */
public class ZKConnectSessionWatcher implements Watcher{

    private static Logger log = LoggerFactory.getLogger(ZKConnectSessionWatcher.class);
    public static final String zkServerPath = "119.23.106.27";
    public static final Integer timeout = 5000;

    public static void main(String[] args) {
        try {
            ZooKeeper zk = new ZooKeeper(zkServerPath,timeout,new ZKConnectSessionWatcher());
            Long sessionId = zk.getSessionId();
            String ssid = "0x" + Long.toHexString(sessionId);
            System.out.println(ssid);
            byte[] sessionPassword = zk.getSessionPasswd();

            log.warn("客户端开始连接zookeeper服务器");
            log.warn("连接状态:{}",zk.getState());

            new Thread().sleep(200);

            log.warn("开始会话连接...");

            ZooKeeper zkSession = new ZooKeeper(zkServerPath
                    ,timeout
                    ,new ZKConnectSessionWatcher()
                    ,sessionId
                    ,sessionPassword);
            log.warn("重新连接状态zkSession:{}",zkSession.getState());
            new Thread().sleep(1000);
            log.warn("重新连接状态zkSession:{}",zkSession.getState());

        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void process(WatchedEvent watchedEvent) {
        log.warn("接受到watch通知:{}",watchedEvent);
    }
}
