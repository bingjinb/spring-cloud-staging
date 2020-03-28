package com.bugod.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'} ;

    /**
     * MD5加密
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        String outStr = null;
        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(inStr.getBytes("utf-8"));
            outStr = byteToString(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return outStr;
    }

    /**
     * 转换字符串
     * @param digest
     * @return
     */
    public static String byteToString(byte[] digest) {
        int len = digest.length;
        StringBuilder buf = new StringBuilder(2 * len);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(digest[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[digest[j] & 0x0f]);
        }
        return buf.toString();
    }

}