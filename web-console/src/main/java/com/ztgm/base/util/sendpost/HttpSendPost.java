package com.ztgm.base.util.sendpost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HttpClient实现post请求
 */

public class HttpSendPost {

	private static final Logger logger = LoggerFactory.getLogger(HttpSendPost.class);

	/**
	 * post
	 * @param url
	 * @param str
	 * @return
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月29日
	 */
	public static String sendHttpData(String url, String str) throws Exception {

		logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(60000);

		// httpPost.setRequestHeader("Content-type", "application/json");
		httpPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");

		httpPost.setRequestHeader("Accept", "application/json");
		httpPost.setRequestHeader("Connection", "close");
		// httpPost.setRequestHeader("Authorization", "Basic YWRtaW46MTIz");
		httpPost.setRequestBody(is);

		String responseData = null;
		try {
			Exception exception = null;
			client.executeMethod(httpPost);
			int resStatusCode = httpPost.getStatusCode();
			if (resStatusCode == HttpStatus.SC_OK) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpPost.getResponseBodyAsStream(), "utf-8"));
				logger.info("HTTP Request CHARSET:" + httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();
				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();
			} else {
				logger.error("http请求失败 " + resStatusCode + ":" + httpPost.getStatusText());
				exception = new Exception("[SerialHttpSender] HttpErrorCode:" + resStatusCode);
			}
			if (exception != null) {
				throw exception;
			}
		} catch (java.net.ConnectException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
			// org.apache.commons.httpclient.HttpRecoverableException:
			// java.net.SocketTimeoutException: Read timed out

			String message = ex.getMessage();
			if (message != null && message.toLowerCase().indexOf("read timed") > -1) {
				throw new Exception(ex.getMessage());
			} else {
				ex.printStackTrace();
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			httpPost.releaseConnection();

		}

		logger.info("HTTP Request Result:" + responseData);
		return responseData;
	}

}
