package com.vip.athena.job.util;

import com.vip.athena.job.exception.JobException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author waying.he
 */
public class IpUtils {

    public static String getIp() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new JobException(e);
        }
        while (netInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = netInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (!address.isLoopbackAddress()
                        && !address.isSiteLocalAddress()
                        && !isIpV6Address(address)) {
                    return address.getHostAddress();
                }
                if (address.isSiteLocalAddress() && !address.isLoopbackAddress() && !isIpV6Address(address)) {
                    return address.getHostAddress();
                }
            }
        }
        return null;
    }

    private static boolean isIpV6Address(InetAddress address) {
        return address.getHostAddress().indexOf(":") != -1;
    }

    private static boolean isLocalIpV4Address(InetAddress address) {
        return address.isSiteLocalAddress()
                && !address.isLoopbackAddress()
                && !isIpV6Address(address);
    }

    private static boolean isPublicIpV4Address(InetAddress address) {
        return !address.isLoopbackAddress()
                && !address.isSiteLocalAddress()
                && !isIpV6Address(address);
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
