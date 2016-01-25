package com.vip.athena.job.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author waying.he
 */
public class ZooKeeperConfigFactoryTest {

    @Test
    public void testGetZooKeeperConfig() throws Exception {
        ZooKeeperConfig config = ZooKeeperConfigFactory.getInstance().getZooKeeperConfig();
        assertEquals("namespace", config.getNamespace());
        assertEquals(1000, config.getSessionTimeoutMs());
        assertEquals(5, config.getMaxRetryTimes());
    }
}