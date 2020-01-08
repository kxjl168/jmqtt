package org.jmqtt.common.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient实现post请求
 */

public class HttpSendPostNew {

	private static final Logger logger = LoggerFactory.getLogger(HttpSendPostNew.class);

	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	private static CloseableHttpClient getClinet() throws Exception {

		SSLContext sslcontext = createIgnoreVerifySSL();

		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);

		/*
		 * if(agent!=null) { HttpHost host=new
		 * HttpHost(agent.getHost(),agent.getPort());
		 * HttpClients.custom().setProxy(host);
		 * System.out.println("代理:"+agent.getHost()+":"+agent.getPort()); }
		 */

		// 创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
		return client;

	}

	public static String sendHttpJSONDataNoSSL(String token, boolean jsonContent, String url, String str)
			throws Exception {

		return sendHttpJSONDataNoSSL(true, token, jsonContent, url, str, null, null);
	}

	public static String sendHttpJSONDataNoSSL(boolean isGet, String token, boolean jsonContent, String url, String str,
			HashMap<String, String> headerstrs, List<BasicClientCookie> cks) throws Exception {

		// logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);

		CloseableHttpClient client = getClinet();

		// HttpClient httpClient = HttpClientManager.getProxyHttpClient(host, port,
		// useProxy);

		HttpUriRequest httpPost = new HttpPost(url);
		if (isGet)
			httpPost = new HttpGet(url);
		// InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		// client.setTimeout(60000);

		// PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		// client.setTimeout(60000);

		if (jsonContent) {
			httpPost.setHeader("Content-type", "application/json");

		} else {
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");

			String commetdata = str;// jobj.toString();
			JSONObject jobj = new JSONObject(commetdata);
			String data2 = "";
			try {
				for (String key : jobj.keySet()) {
					data2 += key + "=" + jobj.optString(key) + "&";
				}
				if (!data2.equals(""))
					data2 = data2.substring(0, data2.length() - 1);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "";
			}

			str = data2;

		}

		// 设置参数到请求对象中
		if (!isGet) {
			HttpPost p = ((HttpPost) httpPost);
			p.setEntity(new StringEntity(str, "utf-8"));

			httpPost = p;
		}

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Connection", "close");
		// httpPost.setHeader("Token", token);

		// httpPost.setHeader("Referer", "http://www.gsxt.gov.cn");

		if (headerstrs != null && headerstrs.keySet().size() > 0) {
			for (String key : headerstrs.keySet()) {
				httpPost.setHeader(key, headerstrs.get(key));
			}
		}

		if (cks != null && cks.size() > 0) {

			String ck = "";
			for (BasicClientCookie basicClientCookie : cks) {
				ck += basicClientCookie.getName() + "=" + basicClientCookie.getValue() + "; ";
			}
			System.out.println("index set-cookie:" + ck);
			if (!ck.equals(""))
				httpPost.setHeader("Cookie", ck);
			// log.debug(ck);
		}

		// httpPost.setHeader("Cookie",
		// "__jsluid_h=ff98057ff8e815927d02583503ca4523;
		// __jsl_clearance=1571304495.958|0|I9XaAXksRSQ%2BdWaix8DtTHaG9EY%3D;
		// UM_distinctid=16dd90bec95310-02659fc2e08667-386a410b-1fa400-16dd90bec9651a;
		// gsxtBrowseHistory1=%0FS%04%06%1D%04%1D%10SNS%24%26%3B%22%3D%3A71%3A%3B01%3A%219GFDDDDGFDEDDDDDDDDDDEFCEAAGSXS%11%1A%00%1A%15%19%11SNS%E6%B0%AB%E8%8A%BB%E5%AE%8F%E6%A1%8D%E6%8B%A1%E8%B4%B0%E6%9D%BD%E9%98%A4%E5%84%98%E5%8E%8CSXS%11%1A%00%00%0D%04%11SNEEGDXS%02%1D%07%1D%00%00%1D%19%11SNEACEGD%40LD%40ABC%09;
		// CNZZDATA1261033118=688658235-1571299632-http%253A%252F%252Fwww.gsxt.gov.cn%252F%7C1571305032;
		// JSESSIONID=4E2E483D148DB46C7F403023D89F0E99-n1:1; tlb_cookie=S172.16.12.46");

		// httpPost.setEntity(entity);(is);

		String responseData = null;
		try {
			Exception exception = null;
			CloseableHttpResponse response = client.execute(httpPost);
			int resStatusCode = response.getStatusLine().getStatusCode();

			Header[] headers = response.getHeaders("Set-Cookie");
			// Set-Cookie:
			// acw_tc=7ceef51c15706890714395903e2b002608f81e957669a4989349d965cf;path=/;HttpOnly;Max-Age=2678401
			// Set-Cookie: QCCSESSID=g4oivaaqhu1ueoca87jtpbrr86; path=/; domain=qichacha.com
			for (Header header : headers) {
				try {

					String[] cooks = header.getValue().substring(0, header.getValue().indexOf(";")).split("=");
					String path = "";
					if (header.getValue().contains("path=")) {
						int pathindex = header.getValue().indexOf("path=");
						int nextflag = header.getValue().indexOf(";", pathindex);
						path = header.getValue().substring(pathindex + 5, nextflag);
					}
					String domain = "";
					if (header.getValue().contains("domain=")) {
						int pathindex = header.getValue().indexOf("domain=");
						// int nextflag= header.getValue().indexOf(";",pathindex);
						domain = header.getValue().substring(pathindex + 7);
					}
					BasicClientCookie ck = new BasicClientCookie(cooks[0], cooks[1]);
					ck.setDomain(domain);
					ck.setPath(path);

					cks.add(ck);
				} catch (Exception e) {
					continue;
				}

			}

			if (resStatusCode == HttpStatus.SC_OK) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent(), "utf-8"));
				// logger.info("HTTP Request CHARSET:" +
				// httpPost.getEntity().getContentEncoding().getName());
				String res = null;
				StringBuffer sb = new StringBuffer();
				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();

				if (cks != null && cks.size() == 0) { // 传入空列表，同时服务器有返回则填入cookie

				}

			}

			// 拿到第一次请求返回的JS
			else if (response.getStatusLine().getStatusCode() == 521) {

				responseData = "503js-cookie";
			}

			else {
				logger.error("http请求失败 " + resStatusCode);
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
			if (isGet) {
				((HttpGet) httpPost).releaseConnection();
			} else
				((HttpPost) httpPost).releaseConnection();

		}

		// logger.info("HTTP Request Result:" + responseData);
		return responseData;
	}

	/**
	 * post
	 * 
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
