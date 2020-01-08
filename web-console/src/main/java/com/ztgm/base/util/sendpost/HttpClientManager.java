package com.ztgm.base.util.sendpost;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * <HTTP连接管理类>
 * 
 * @author gejq
 * @version 1.0.4
 * @since 1.0.0
 */

public class HttpClientManager {
	private static final Log log = LogFactory.getLog(HttpClientManager.class);
	private static PoolingHttpClientConnectionManager connManager=null;
	/**
	 * 最大连接数 
	 */
	private final static int MAX_TOTAL_CONNECTIONS = 200000;
	/**
	 * 每个路由最大连接数
	 */
	private final static int MAX_ROUTE_CONNECTIONS = 20000;
	/**
	 * 获取连接的最大等待时间 - 3s
	 */
	private final static int WAIT_TIMEOUT = 10;
	/**
	 * 连接超时时间 - 1s
	 */
	private  static int CONNECT_TIMEOUT = 10000;
	/**
	 * 读取超时时间 - 1s
	 */
	private  static int READ_TIMEOUT = 10000;
	
	/**
	 * 闲置时间
	 */
	private  static long IDLE_TIMEOUT = 60000*30;
	
	/**
	 * 清理闲置连接的间隔时间
	 */
	private  static long release_connection_TIME = 60000*10;
	
	/**
	 * 访问异常时重试次数
	 */
	private static int retryCount=1;
	
	/**
	 * Socket 缓冲大小 - 1024字节
	 */
	private final static int SOCKET_BUFFER_SIZE = 10240;
	
	private static RequestConfig defaultRequestConfig = RequestConfig.custom()
  		    .setSocketTimeout(READ_TIMEOUT)
  		    .setConnectTimeout(CONNECT_TIMEOUT)
  		    .setConnectionRequestTimeout(READ_TIMEOUT)        	
  		    .build();	

	static{

		try {

			//信任所有
			SSLContext	sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
			
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        
	        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
			    .register("http", PlainConnectionSocketFactory.INSTANCE)
	    		    .register("https", sslsf)
	    		    .build();
	        connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
	        connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);	        
	        connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);     
	        
	        ConnectionConfig connectionConfig = ConnectionConfig.custom()
	       		    .setBufferSize(SOCKET_BUFFER_SIZE)        		
	       		    .setMalformedInputAction(CodingErrorAction.IGNORE)
	       		    .setUnmappableInputAction(CodingErrorAction.IGNORE)
	       		    .setCharset(Consts.UTF_8)
	       		    
	       		    .build();	        
	       connManager.setDefaultConnectionConfig(connectionConfig);
	       
	       
	       
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			e.printStackTrace();
		}


	}
	
	protected static void setReleaseConnectionTime(long releaseConnectionTime){
		release_connection_TIME=releaseConnectionTime;
	}	
	
	protected static void setIdleTimeout(long idleTimeout){
		IDLE_TIMEOUT=idleTimeout;
	}	
	
	protected static void setConnectionTimeOut(int readTimeout){
		CONNECT_TIMEOUT=readTimeout;
		defaultRequestConfig = RequestConfig.custom()
	  		    .setSocketTimeout(READ_TIMEOUT)
	  		    .setConnectTimeout(CONNECT_TIMEOUT)
	  		    .setConnectionRequestTimeout(READ_TIMEOUT)        	
	  		    .build();	
	}
	
	protected static void setReadTimeOut(int readTimeout){
		READ_TIMEOUT=readTimeout;
		defaultRequestConfig = RequestConfig.custom()
	  		    .setSocketTimeout(READ_TIMEOUT)
	  		    .setConnectTimeout(CONNECT_TIMEOUT)
	  		    .setConnectionRequestTimeout(READ_TIMEOUT)        	
	  		    .build();	
	}	
	
	/**
	 * 设置重试次数
	 * @param time
	 */

	public static void setRetryCount(int time){
		retryCount=time;
	}
	
	/**
	 * 返回一个可用的HttpClient实例
	 * @return HttpClient实例
	 */
	public static HttpClient getHttpClient() {
		try{
	        HttpClientBuilder httpClientBuilder= HttpClients.custom();
	
	        httpClientBuilder.setConnectionManager(connManager);	       
	             
	        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
	        
	        if(retryCount>0){
	        	httpClientBuilder.setRetryHandler(new HttpRequestRetryHandler(){

					@Override
					public boolean retryRequest(IOException arg0, int arg1, HttpContext arg2) {
						if(arg1>retryCount){
							return false;
						}else{
							return true;
						}						
					}
	        		
	        	} );
	        }
	     
	        return httpClientBuilder.build();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return   HttpClients.createDefault();
	     }
		
	}

	/**
	 * 释放连接 - HTTP
	 */
	protected static void release() {

		if (connManager != null) {
			connManager.shutdown();
			System.gc();
		}
	}
	

}
