package com.ain.engine.util;

import java.net.InetAddress;
import java.util.regex.Pattern;

public class AddressUtil {
	
	private static final Pattern pattern = Pattern.compile("\\.", Pattern.CASE_INSENSITIVE);
//	return p.split(src);
	
	private static byte[] localAddress;

	public static byte[] getAddress(String addr) {
		String[] array = pattern.split(addr);
		
		if (array.length != 4) {
			return new byte[]{0x00,0x00,0x00,0x00};
		}
		
		byte[] bytes = new byte[4];
		
		for (int i = 0; i < array.length; i++) {
			try {
				bytes[i] = (byte)Integer.parseInt(array[i]);
			} catch (Exception e) {
				return new byte[]{0x00,0x00,0x00,0x00};
			}
		}
		
		return bytes;
	}
	
	
	
//	public static byte[] getLocalAddress() {
//		if (localAddress == null) {
//		
//			String local = Env.getLocalAddress();
//			if (local != null) {
//				localAddress = getAddress(local);
//			}
//			
//			if (localAddress == null) {
//				try {
//					InetAddress inetAddress = InetAddress.getLocalHost();
//						
//					localAddress = inetAddress.getAddress();
//				} catch (Exception e) {}
//			}
//		}
//		
//		return localAddress;
//	}

	public static byte[] getLocalAddress() {
		if (localAddress == null) {
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
					
				localAddress = inetAddress.getAddress();
			} catch (Exception e) {}
		}
		return localAddress;
	}
}
