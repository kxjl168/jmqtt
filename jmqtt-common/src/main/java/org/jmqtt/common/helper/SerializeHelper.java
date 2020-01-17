package org.jmqtt.common.helper;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.jmqtt.common.log.LoggerName;
import org.jmqtt.group.protocol.node.ServerNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class SerializeHelper {

	/*fastjons 解析快，bean 需要有默认无参构造函数！*/
	
    private static final Logger log = LoggerFactory.getLogger(LoggerName.OTHER);

    public static <T> byte[] serialize(T obj){
        if(obj instanceof String){
            return ((String) obj).getBytes();
        }
        
        
        
        return JSONObject.toJSONBytes(obj);
    }

    public static <T> T deserialize(byte[] bytes,Class<T> clazz){
        try{
        	String jsSt=new String(bytes,"utf-8");
            if(clazz == String.class){
                return (T)jsSt;
            }
            Gson gs=new Gson();
            return gs.fromJson(jsSt, clazz);
            //return JSONObject.parseObject(bytes,clazz);
        }catch(Exception ex){
            log.warn("Deserialize failure,cause={}",ex);
        }
        return null;
    }

    public static <T> List<T> deserializeList(byte[] bytes, Class<T> clazz){
        try{
        	//JSONObject.parseArray(new String(bytes),clazz)
        	
        	//zj 
            String json = new String(bytes,"utf-8");
            List<T> result = JSONObject.parseArray(json,clazz);
            return result;
        }catch (Exception ex){
            log.warn("Deserialize failure,cause={}",ex);
        }
        return null;
    }

}
