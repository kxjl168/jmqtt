package com.ztgm.openplat.util;

import java.util.UUID;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

public class UUIDUtil {

	public static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

	/**
	 * 获取UUID
	 *
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static Long getLongUUID() {
		return IdWorker.getId();
	}

}
