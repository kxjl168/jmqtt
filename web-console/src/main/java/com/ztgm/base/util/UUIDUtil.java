package com.ztgm.base.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
