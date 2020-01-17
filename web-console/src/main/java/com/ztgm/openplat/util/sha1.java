package com.ztgm.openplat.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * HmacSHA1加密类
 *
 */
public class sha1 {

    public static String getHmacSHA1(String password,String loginname, String algorithm){
        byte[] keyBytes = password.getBytes();
        Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm);
        Mac mac=null;
        try {
            mac = Mac.getInstance(algorithm);
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return byteArrayToHex(mac.doFinal(loginname.getBytes()));
    }


    /**
     * 16进制加密
     * @param a
     * @return
     */
    protected static String byteArrayToHex(byte [] a) {
        int hn, ln, cx;
        String hexDigitChars = "0123456789abcdef";
        StringBuffer buf = new StringBuffer(a.length * 2);
        for(cx = 0; cx < a.length; cx++) {
            hn = ((int)(a[cx]) & 0x00ff) /16 ;
            ln = ((int)(a[cx]) & 0x000f);
            buf.append(hexDigitChars.charAt(hn));
            buf.append(hexDigitChars.charAt(ln));
        }
        return buf.toString();

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {

        String loginKey= getHmacSHA1("密码", "用户名", "HmacSHA1");
        System.out.println(loginKey);
        String loginKey2= getHmacSHA1("123456", "admin", "HmacSHA1");
        System.out.println(loginKey2);//3c39afa93e0b12c28f1f08b18488ebd4ad2e5858

    }
}