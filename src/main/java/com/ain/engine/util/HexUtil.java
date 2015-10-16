package com.ain.engine.util;

import java.nio.ByteBuffer;

public class HexUtil {
	
	private static final char[] HEX_DIGITS = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8',
		'9', 'A', 'B', 'C', 'D', 'E', 'F'
	}; 
	
	static char hi(byte b) { return HEX_DIGITS[(b & 0xF0) >>> 4]; }
	static char lo(byte b) { return HEX_DIGITS[b & 0x0F]; }

	public static String toHex(int x) {
		char[] hexChars = new char[8];
		
		byte b3 = (byte)(x >> 24);
		byte b2 = (byte)(x >> 16);
		byte b1 = (byte)(x >> 8);
		byte b0 = (byte)(x >> 0);
		
		hexChars[0] = hi(b3); hexChars[1] = lo(b3);
		hexChars[2] = hi(b2); hexChars[3] = lo(b2);
		hexChars[4] = hi(b1); hexChars[5] = lo(b1);
		hexChars[6] = hi(b0); hexChars[7] = lo(b0);
		
		return new String(hexChars);
	}

	public static String toHex(short x) {
		char[] hexChars = new char[4];
		
		byte b1 = (byte)(x >> 8);
		byte b0 = (byte)(x >> 0);
		
		
		hexChars[0] = hi(b1); hexChars[1] = lo(b1);
		hexChars[2] = hi(b0); hexChars[3] = lo(b0);
		         
		return new String(hexChars);
	}

	public static String toHex(byte b) {
		char[] hexChars = new char[4];
		
		hexChars[0] = '0'; hexChars[1] = 'x';
		hexChars[2] = hi(b); hexChars[3] = lo(b);
		
		return new String(hexChars);
	}

	public static String toHex(byte[] b) {
		StringBuffer out = new StringBuffer();
		
		out.append(hi(b[0])).append(lo(b[0]));
		
		for (int i = 1; i < b.length; i++) {
			out.append(' ');
			out.append(hi(b[i])).append(lo(b[i]));
		}
		
		return out.toString();
	}

	public static String toHex(byte[] b, int startIdx, int length) {
		if (b.length < (startIdx + length))
			return "";
		
		StringBuffer out = new StringBuffer();
		for (int i = startIdx; i < (startIdx + length); i++) {
			if (i != startIdx) 
				out.append(' ');
			out.append(hi(b[i])).append(lo(b[i]));
		}
		
		return out.toString();
	}

	public static String toHex(ByteBuffer buffer) {
		StringBuffer out = new StringBuffer();
		
		byte b;
		int mark = buffer.position();
		
		buffer.position(0);		//	시작위치
		
		
		if (buffer.hasRemaining()) {
			b = buffer.get();
			out.append(hi(b)).append(lo(b));
		}
		
		while (buffer.hasRemaining()) {
			out.append(' ');

			b = buffer.get();
			out.append(hi(b)).append(lo(b));
		}
		
		buffer.position(mark);	// 초기 위치로	
		
		return out.toString();
	}

	public static ByteBuffer toByteBuffer(String hexStr) {
		if (hexStr == null || hexStr.length() == 0) {
			return null;
		}
		if (hexStr.length() % 2 != 0) {
			throw new IllegalArgumentException("Invalid hex value.");
		}
		byte[] ba = new byte[hexStr.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
		}
		ByteBuffer buffer = ByteBuffer.allocate(ba.length);
		buffer.clear();
		buffer.put(ba);
		buffer.flip();
		return buffer;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		long a = Long.MAX_VALUE - 1;
		
		for (int i=0; i<10; i++) {
			a++;
			if (a<0) break;
		}
		
		System.out.println(a);
		//byte cmd = (byte) Integer.parseInt("11");
		//byte cmd = (int)Byte.parseByte("11", 16);
		//cmd = (byte)cmd;
		
		
	}
}
