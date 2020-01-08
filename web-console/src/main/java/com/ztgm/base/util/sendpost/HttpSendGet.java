package com.ztgm.base.util.sendpost;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;


/**
 * HttpClient实现get请求
 */
@Component
public class HttpSendGet {

    private static final Log log = LogFactory.getLog(HttpSendGet.class);

    /**
     * HTTP HEAD CONTENT TYPE
     */
    private final static String CONTENT_TYPE = "application/json;charset=utf-8";

    public SendPostResponse doGet(String url, String json) throws Exception {

        SendPostResponse wzResponse = new SendPostResponse();


        org.apache.http.client.methods.HttpGet httpGet = new org.apache.http.client.methods.HttpGet(url);
        httpGet.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE);
//        httpGet.setEntity(new StringEntity(json,"utf-8"));
        HttpClient httpClient = HttpClientManager.getHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        wzResponse.setStatusCode(statusCode);
        //请求成功获取返回数据
        if (statusCode == 200) {
            HttpEntity reqHttpEntity = httpResponse.getEntity();
            wzResponse.setData(EntityUtils.toString(reqHttpEntity, "UTF-8"));
            EntityUtils.consume(reqHttpEntity);
        }
        httpGet.releaseConnection();
        httpGet.abort();
        return wzResponse;
    }

}
