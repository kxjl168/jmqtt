/**
 * @(#)UploadUtils.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.base;

import java.util.ArrayList;
import java.util.List;

public class UploadUtils {

	public UploadUtils() {
		// TODO Auto-generated constructor stub
	}
	private static List<String> imgExt = new ArrayList<>();
	static {
		imgExt.add("png");
		imgExt.add("jpg");
		imgExt.add("bmp");
		imgExt.add("jpeg");
		imgExt.add("jpe");
		imgExt.add("gif");
	}
	

	/**
	 * 获取文件名后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName){
		String fileExt = null;
		int index = fileName.lastIndexOf(".") ;
		if(index ==-1){
			fileExt = "";
		}
		else{
			fileExt = fileName.substring(index+1)
				.toLowerCase();
		}
		return fileExt;
	}
	
	public static boolean isImage(String fileName){
		return imgExt.contains(getFileExt(fileName));
	}
	
	public static boolean isImageExt(String ext){
		return imgExt.contains(ext);
	}
}

