/*
 * @(#)SerializeUtil.java
 * @author: chenenqiang
 * @Date: 2018/12/5 16:36
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util;

import java.io.*;

/**
 * @author: chenenqiang
 * @createDate: 2018/12/5 16:36
 * @description:
 */
public class SerializeUtil {


    /**
     * 单个序列化
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(oos);
            close(baos);
        }
        return bytes;
    }

    /**
     * 单个反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bais);
            close(ois);
        }
        return null;
    }

    /**
     * 关闭io流对象
     *
     * @param closeable
     */
    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
