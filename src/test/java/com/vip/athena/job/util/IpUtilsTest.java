package com.vip.athena.job.util;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

/**
 * @author waying.he
 */
public class IpUtilsTest {

    @Test
    public void testGetIp() throws Exception {
        System.out.println(IpUtils.getIp());
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

    @Test
    public void testGetHostName() throws Exception {
        assertEquals("waying-he", IpUtils.getHostName());
    }
}