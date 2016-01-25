package com.vip.athena.job.service;

import com.vip.athena.job.config.ZooKeeperConfig;
import com.vip.athena.job.config.ZooKeeperConfigFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author waying.he
 */
public class ZooKeeperService {

    private CuratorFramework client;

    public void init() {
        ZooKeeperConfig config = ZooKeeperConfigFactory.getInstance().getZooKeeperConfig();
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(config.getConnectString())
                .connectionTimeoutMs(config.getConnectionTimeoutMs())
                .sessionTimeoutMs(config.getSessionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(config.getBaseSleepTimeMs(), config.getMaxRetryTimes(), config.getMaxSleepTimeMs()))
                .canBeReadOnly(false)
                .defaultData(new byte[0])
                .build();
        client.start();
        this.client = client;
    }

    public void destroy() {
        client.close();
    }



}
