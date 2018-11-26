package com.example.zk;

import org.apache.zookeeper.AsyncCallback.VoidCallback;

/**
 * Created by Kelly on 2018/11/26.
 */
public class DeleteCallBack implements VoidCallback {

    public void processResult(int rc, String path, Object ctx) {
        System.out.println("删除节点" + path);
        System.out.println((String)ctx);
    }
}
