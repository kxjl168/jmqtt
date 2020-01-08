package com.ztgm.base.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AliSendSMS {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";


    private static String ACCESS_KEY_ID = "";
    private static String ACCESS_KEY_SECRET = "";
    private static String SIGN_NAME = "";
    private static String SMS_CODE = "";
    private static String MESSAGE_CODE = "";


    private IAcsClient smsInIt() {

        AliSendSMS.ACCESS_KEY_ID = redisUtil.getParam("AliaccessKeyId");
        AliSendSMS.ACCESS_KEY_SECRET = redisUtil.getParam("AliaccessKeySecret");
        AliSendSMS.SIGN_NAME = redisUtil.getParam("AlisignName");
        AliSendSMS.SMS_CODE = redisUtil.getParam("AlismsCode");
        AliSendSMS.MESSAGE_CODE = redisUtil.getParam("AlimessageCode");

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        } catch (ClientException e) {
            e.printStackTrace();
            log.error(ExceptionUntil.getMessage(e));
        }
        return new DefaultAcsClient(profile);
    }


    public static Boolean sendCodeSms(String phone, String code) {
        IAcsClient acsClient = new AliSendSMS().smsInIt();
        SendSmsResponse sendSmsResponse = new SendSmsResponse();
        try {
            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(SIGN_NAME);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(SMS_CODE);
            //可选:模板中的变量替换JSON串,如 您的验证码为${code}"时,此处的值为
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            request.setTemplateParam(JSONObject.fromObject(map).toString());

            //hint 此处可能会抛出异常，注意catch
            sendSmsResponse = acsClient.getAcsResponse(request);
            JSONObject obj = new JSONObject();
            obj.put("Code", sendSmsResponse.getCode());
            obj.put("Message", sendSmsResponse.getMessage());
            obj.put("RequestId", sendSmsResponse.getRequestId());
            obj.put("BizId", sendSmsResponse.getBizId());
            log.info(obj.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            log.error("发送验证短信失败");
            log.error(ExceptionUntil.getMessage(e));
        }
        return sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode());
    }

    public static Boolean sendMessageSms(String phone, String message) {
        IAcsClient acsClient = new AliSendSMS().smsInIt();
        SendSmsResponse sendSmsResponse = new SendSmsResponse();
        try {
            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(SIGN_NAME);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(MESSAGE_CODE);
            //可选:模板中的变量替换JSON串,如 您的验证码为${code}"时,此处的值为
            JSONObject jsonObject =JSONObject.fromObject(message);
            Map<String, String> map = new HashMap<>();
            map.put("device", jsonObject.getString("device"));
            map.put("state", jsonObject.getString("state"));
            request.setTemplateParam(JSONObject.fromObject(map).toString());

            //hint 此处可能会抛出异常，注意catch
            sendSmsResponse = acsClient.getAcsResponse(request);
            JSONObject obj = new JSONObject();
            obj.put("Code", sendSmsResponse.getCode());
            obj.put("Message", sendSmsResponse.getMessage());
            obj.put("RequestId", sendSmsResponse.getRequestId());
            obj.put("BizId", sendSmsResponse.getBizId());
            log.info(obj.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            log.error("发送消息短信失败");
            log.error(ExceptionUntil.getMessage(e));
        }
        return sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode());
    }

}
