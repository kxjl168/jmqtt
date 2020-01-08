/*
 * @(#)Rc4Util.java
 * @author: chenenqiang
 * @Date: 2018/11/28 16:06
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util.yd;

import java.util.Objects;

/**
 * @author: chenenqiang
 * @createDate: 2018/11/28 16:06
 * @description:
 */
public class Rc4Util {


    /**
     * 加密
     **/
    public static String encry_number_Rc4(String data, String key) {
        if (data == null || key == null || data.length() < 6) {
            return null;
        }
        byte[] bytes = new byte[12];
        for (int i = 0; i < bytes.length; i++) {
            if (i < 6) {
                bytes[i] = Byte.parseByte(data.substring(i, i + 1));
            } else {
                bytes[i] = (byte) 0xff;
            }
        }
        return bytes2hexstring(rc4base(bytes, key)).toLowerCase();
    }

    private static byte[] rc4base(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = initKey(mKkey);
        int xorIndex;
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 0xff;
            y = ((Objects.requireNonNull(key)[x] & 0xff) + y) & 0xff;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }

    private static byte[] initKey(String aKey) {
        byte[] bKey = aKey.getBytes();
        byte[] state = new byte[256];
        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int index1 = 0;
        int index2 = 0;
        if (bKey.length == 0) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            index2 = ((bKey[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
            byte tmp = state[i];
            state[i] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % bKey.length;
        }
        return state;
    }

    private static String bytes2hexstring(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

}
