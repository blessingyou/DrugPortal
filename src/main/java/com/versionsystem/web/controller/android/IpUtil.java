package com.versionsystem.web.controller.android;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpUtil {
	
	private IpUtil(){
		
	}
	 /**
	  * æ­¤æ–¹æ³•æè¿°çš„æ˜¯ï¼šèŽ·å¾—æœåŠ¡å™¨çš„IPåœ°å€
	  */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			boolean bFindIP = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (bFindIP) {
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						bFindIP = true;
						break;
					}
				}
			}
		}catch (Exception e) {
//			OutUtil.error(IpUtil.class, e.getMessage());
			e.printStackTrace();
		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		
		return sIP;
	 }
	
	/**
	 * æ­¤æ–¹æ³•æè¿°çš„æ˜¯ï¼šèŽ·å¾—æœåŠ¡å™¨çš„IPåœ°å€(å¤šç½‘å¡)
	 */
	public static List<String> getLocalIPS() {
		InetAddress ip = null;
		List<String> ipList = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						ipList.add(ip.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
//			OutUtil.error(IpUtil.class, e.getMessage());
			e.printStackTrace();
		}
		
		return ipList;
	}
	 
	/**
	 * æ­¤æ–¹æ³•æè¿°çš„æ˜¯ï¼šèŽ·å¾—æœåŠ¡å™¨çš„MACåœ°å€
	 */
	public static String getMacId() {
		String macId = "";
		InetAddress ip = null;
		NetworkInterface ni = null;
		try {
			boolean bFindIP = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (bFindIP) {
					break;
				}
				ni = (NetworkInterface) netInterfaces.nextElement();
				// ----------ç‰¹å®šæƒ…å†µï¼Œå¯ä»¥è€ƒè™‘ç”¨ni.getNameåˆ¤æ–­
				// éåŽ†æ‰€æœ‰ip
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (!ip.isLoopbackAddress() // éž127.0.0.1
							&& ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						bFindIP = true;
						break;
					}
				}
			}
		} catch (Exception e) {
//			OutUtil.error(IpUtil.class, e.getMessage());
			e.printStackTrace();
		}
		if (null != ip) {
			try {
				macId = getMacFromBytes(ni.getHardwareAddress());
			} catch (SocketException e) {
//				OutUtil.error(IpUtil.class, e.getMessage());
				e.printStackTrace();
			}
		}
		
		return macId;
	 }
	
	/**
	 * æ­¤æ–¹æ³•æè¿°çš„æ˜¯ï¼šèŽ·å¾—æœåŠ¡å™¨çš„MACåœ°å€(å¤šç½‘å¡)
	 */
	public static List<String> getMacIds() {
		InetAddress ip = null;
		NetworkInterface ni = null;
		List<String> macList = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				ni = (NetworkInterface) netInterfaces.nextElement();
				// ----------ç‰¹å®šæƒ…å†µï¼Œå¯ä»¥è€ƒè™‘ç”¨ni.getNameåˆ¤æ–­
				// éåŽ†æ‰€æœ‰ip
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (!ip.isLoopbackAddress() // éž127.0.0.1
							&& ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						macList.add(getMacFromBytes(ni.getHardwareAddress()));
					}
				}
			}
		} catch (Exception e) {
//			OutUtil.error(IpUtil.class, e.getMessage());
			e.printStackTrace();
		}
		
		return macList;
	 }
	
	private static String getMacFromBytes(byte[] bytes) {
		StringBuffer mac = new StringBuffer();
		byte currentByte;
		boolean first = false;
		for (byte b : bytes) {
			if (first) {
				mac.append("-");
			}
			currentByte = (byte) ((b & 240) >> 4);
			mac.append(Integer.toHexString(currentByte));
			currentByte = (byte) (b & 15);
			mac.append(Integer.toHexString(currentByte));
			first = true;
		}
		
		return mac.toString().toUpperCase();
	 }

}

