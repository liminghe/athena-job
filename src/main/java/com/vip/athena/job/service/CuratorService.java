package com.vip.athena.job.service;

import com.vip.athena.job.config.ZooKeeperConfig;
import com.vip.athena.job.exception.JobException;
import com.vip.athena.job.util.PropertiesHelper;
import org.apache.curator.framework.CuratorFramework;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author waying.he
 */
public class CuratorService {

    private CuratorFramework client;
    private ZooKeeperConfig zkConfig;



    public void init() {
        Properties properties = loadPropertiesFromClasspath();
        PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
        ZooKeeperConfig zkConfig = new ZooKeeperConfig(propertiesHelper.getString("zk.connectString"),
                propertiesHelper.getString("zk.namespace"));


    }

    private Properties loadPropertiesFromClasspath() {
        Properties properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("zookeeper.properties");
        if (in == null) {
            throw new JobException("zookeeper.properties not fount from classpath");
        }
        return properties;
    }






}
