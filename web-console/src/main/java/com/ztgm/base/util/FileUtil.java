package com.ztgm.base.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @author zj
 * @date 2015-12-23 上午9:53:07
 */
public class FileUtil {
	

	

	/**
	 * 获取方便识别的文件大小 byte-->1.2M
	 * 
	 * @param filebyteSize
	 *            文件字节大小
	 * @return 0.4byte 1.3k 2.3M 1.4G
	 * @author zj
	 * @date 2015-12-23 上午9:53:47 *
	 */
	public static String getReadableFileSize(String StrFilebyteSize) {
		if (StrFilebyteSize == null || StrFilebyteSize.equals(""))
			return "";

		Long filebyteSize = Long.parseLong(StrFilebyteSize);

		String rst = "";

		int SIZE = 1024;

		if (filebyteSize / SIZE < SIZE) // k
		{
			double tp = filebyteSize / ((double) SIZE);
			rst = String.format("%.2f", tp) + "K";
		} else if (filebyteSize / (SIZE * SIZE) < SIZE) // k
		{
			double tp = filebyteSize / ((double) (SIZE * SIZE));
			rst = String.format("%.2f", tp) + "M";
		} else if (filebyteSize / (SIZE * SIZE * SIZE) < SIZE) // k
		{
			double tp = filebyteSize / ((double) (SIZE * SIZE * SIZE));
			rst = String.format("%.2f", tp) + "G";
		}

		return rst;
	}

	public static void main(String[] args) {
		String k = "123123132";
		System.err.println(getReadableFileSize(k));
	}

}
