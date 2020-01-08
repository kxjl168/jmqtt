package com.ztgm.base.util;

import java.io.*;
import java.util.*;

/*
* 用来判断generator是否已经生成表
* */
public class GeneratorUtils {
	static List<String> list2 = new ArrayList<>();

	public static boolean match(String input, List<String> list) throws IOException, ClassNotFoundException {

		for (String s : list) {
			if (input.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @description 扫描文件
	 */
	public static List<String> getFileNames(String path, String like) throws IOException, ClassNotFoundException {

		List<String> list = new ArrayList<>();

		File file = null;
		File[] files = null;
		Class<?> c = null;
		String fName = null;
		file = new File(path);
		if (file.isDirectory()) {
			files = file.listFiles();
			for (File fl : files) {
				if (fl.isDirectory()) {
					list.addAll(getFileNames(fl.getAbsolutePath(), like));
				} else {
					fName = fl.getName();
					fName = fName.substring(0, fName.lastIndexOf("."));

					if (like != null) {
						if (fName.contains(like))
							list.add(fName);
					} else
						list.add(fName);
				}
			}
		}
		return list;
	}

}
