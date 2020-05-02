package com.zhangmengcong.www.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author:zmc
 * @function: 密码加密的工具类
 * @date: 2020/4/24 22:38
 */
public class Encode {
    public static String shaEncode1(String inStr)  {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        StringBuilder hexValue = new StringBuilder();
        byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = sha.digest(byteArray);

        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();


    }

    /**
     *
     * @param inStr 需加密的字符串
     * @return 加密后的字符串 加后缀 二次加密 比较安全
     */
    public String shaEncode2(String inStr){
        return shaEncode1(inStr+"rushnbdy123");
    }
    /**
     *
     * @param inStr 需加密的字符串
     * @return 加密后的字符串 加后缀 二次加密 比较安全
     */
    public String shaEncode(String inStr){
        return shaEncode2(shaEncode2(inStr));
    }
}
