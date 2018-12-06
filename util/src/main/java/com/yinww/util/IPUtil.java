package com.yinww.util;

/**
 * IP与Long的相互转换工具类, MySQL数据库中存取Long型ip的效率更高
 * @author yinww
 *
 */
public class IPUtil {
	/**
	 * ip地址转长整型
	 * @param ipStr, ip地址
	 * @return
	 */
	public static long ipToLong(String ipStr) {
		String[] ip = ipStr.split("\\.");
		return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
	}
	
	/**
	 * 长整型转ip地址
	 * @param longIp
	 * @return
	 */
	public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

}
