package io.sarc.bomboot.aloft;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.sarc.bomboot.util.StringUtil;

public class NetworkUtil {
	private static int MIN_PORT_NUMBER = 0;
	private static int MAX_PORT_NUMBER = 65535;

	public static InetAddress getFirstNonLoopbackAddress(boolean preferIpv4, boolean preferIPv6)
			throws SocketException {
		Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
		while (en.hasMoreElements()) {
			NetworkInterface i = (NetworkInterface) en.nextElement();
			for (Enumeration<InetAddress> en2 = i.getInetAddresses(); en2.hasMoreElements();) {
				InetAddress addr = (InetAddress) en2.nextElement();
				if (!addr.isLoopbackAddress()) {
					if (addr instanceof Inet4Address) {
						if (preferIPv6) {
							continue;
						}
						return addr;
					}
					if (addr instanceof Inet6Address) {
						if (preferIpv4) {
							continue;
						}
						return addr;
					}
				}
			}
		}
		return null;
	}

	public static ArrayList<String> getNonLoopbackAddressList(boolean preferIpv4, boolean preferIPv6)
			throws SocketException, UnknownHostException {
		ArrayList<String> al = new ArrayList<String>();

		Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
		while (en.hasMoreElements()) {
			NetworkInterface i = (NetworkInterface) en.nextElement();
			for (Enumeration<InetAddress> en2 = i.getInetAddresses(); en2.hasMoreElements();) {
				InetAddress addr = (InetAddress) en2.nextElement();
				if (!addr.isLoopbackAddress()) {
					if (addr instanceof Inet4Address) {
						if (preferIPv6) {
							continue;
						}
						al.add(addr.getHostAddress());
					}
					if (addr instanceof Inet6Address) {
						if (preferIpv4) {
							continue;
						}
						al.add(addr.getHostAddress());
					}
				}
			}
		}
		return al;
	}

	public static HashMap<String, String> getNetworkInterfaceList() throws SocketException {
		HashMap<String, String> hm = new HashMap<String, String>();

		for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
			String k = null;
			String v = null;

			NetworkInterface intf = en.nextElement();
			String intfName = intf.getName();
			if (intfName != null && ( intfName.startsWith("eth") || intfName.startsWith("bon") ) ) {
				k = intfName;
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					v = enumIpAddr.nextElement().toString();
					if (v.startsWith("/"))
						v = StringUtil.erase(v, "/");
					hm.put(k, v);
				}
			}
		}

		return hm;
	}

	public static boolean isPortAvailable(int p) {
		if (p < MIN_PORT_NUMBER || p > MAX_PORT_NUMBER) {
			throw new IllegalArgumentException("Invalid start port: " + p);
		}

		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(p);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(p);
			ds.setReuseAddress(true);
			return true;
		} catch (Exception e) {
			//
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (Exception e) {
					//
				}
			}
		}

		return false;
	}
	
	public static String findIpAddress(String line) {
		String IPADDRESS_PATTERN = 
		        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
		    return matcher.group();
		} else{
		    return null;
		}
	}
	
	public static boolean isPrivateAddress(String s) {
        InetAddress ia = null;

        try {
            InetAddress ad = InetAddress.getByName(s);
            byte[] ip = ad.getAddress();
            ia = InetAddress.getByAddress(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ia.isSiteLocalAddress();
    }
}