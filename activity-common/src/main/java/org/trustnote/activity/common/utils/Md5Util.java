package org.trustnote.activity.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author zhuxl
 */
public class Md5Util {

	/**
	 * 全局数组
	 */
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public Md5Util() {
	}

	/**
	 * 返回形式为数字跟字符串
	 * @param bByte
	 * @return
	 */
	private static String byteToArrayString(final byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		final int iD1 = iRet / 16;
		final int iD2 = iRet % 16;
		return Md5Util.strDigits[iD1] + Md5Util.strDigits[iD2];
	}

	/**
	 * 转换字节数组为16进制字串
	 * @param bByte
	 * @return
	 */
	private static String byteToString(final byte[] bByte) {
		final StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(Md5Util.byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String getMd5Code(final String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			final MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = Md5Util.byteToString(md.digest(strObj.getBytes()));
		} catch (final NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static String getMd5ByObjAndSalt(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			strObj = "^YHN/.,mn" + strObj;
			final MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = Md5Util.byteToString(md.digest(strObj.getBytes()));
		} catch (final NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static void main(final String[] args) {
		//7337e2f117b38edd90ef8ddd50c31406
		//$gUA^u5$3EoHJ32O
		//tDIU4XCand23^S!I
		System.out.println(Md5Util.getMd5Code("^YHN/.,mn" + "111111"));
		System.out.println(Md5Util.getMd5ByObjAndSalt("222222"));
	}
}
