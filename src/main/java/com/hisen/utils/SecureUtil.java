package com.hisen.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/1/23 18:53
 * @description 生成32位的MD5密码
 */
public class SecureUtil {

    private static int HEX_FLAG = 0x0F;

    @Test
    public void md5Test() {
        String text = "test";
        String pwd = Md5(text);
        System.out.println(pwd);
    }

    @Test
    public void sha1Test() {
        String test = "12106175|000000000014|702|3000|N|Khk3HqLXhj6BePZN07BNGFmPSEJ9vMsC";
        System.out.println(test.substring(0, test.length() - 1));
        try {
            System.out.println(getSHA1Str(test));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * md5加密
     */
    private static String Md5(String unSafePwd) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(unSafePwd.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从字节数组转换为16进制字符串
     *
     * @param md5Bytes md5加密之后的数据
     */
    private static String toHex(byte[] md5Bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(md5Bytes.length * 2);
        for (int i = 0; i < md5Bytes.length; i++) {
            ret.append(HEX_DIGITS[(md5Bytes[i] >> 4) & HEX_FLAG]);
            ret.append(HEX_DIGITS[md5Bytes[i] & HEX_FLAG]);
        }
        return ret.toString();
    }

    /**
     * SHA-1加密算法
     */
    public String getSHA1Str(String dataStr)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(dataStr.getBytes("UTF-8"), 0, dataStr.length());
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    private String convertToHex(byte[] sha1Bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sha1Bytes.length; ++i) {
            int halfByte = (sha1Bytes[i] >>> 4) & 15;
            int two_halfs = 0;
            do {
                if (halfByte >= 0 && halfByte <= 9) {
                    sb.append((char) (48 + halfByte));
                } else {
                    sb.append((char) (97 + (halfByte - 10)));
                }
                halfByte = sha1Bytes[i] & 15;
            } while (two_halfs++ < 1);
        }
        return sb.toString();
    }

    /**
     * @param secret 密钥
     * @param content 需要散列的内容
     * @return HmacSHA256 之后用 base64 加密
     * @throws Exception
     */
    public String hmacSHA256Base64(String secret,String content) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.encodeBase64String(sha256_HMAC.doFinal(content.getBytes()));
    }
    @Test
    public void testHMAC() {
        try {
            String secret = "secret";
            String message = "hisenyuan";
            String hmacSHA256Base64 = hmacSHA256Base64(secret, message);
            System.out.println(hmacSHA256Base64);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
