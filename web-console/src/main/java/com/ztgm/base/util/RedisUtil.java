package com.ztgm.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class RedisUtil {


    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void setParam(String key, String value) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setList(String key, List list) {
        try {
            redisTemplate.opsForList().leftPushAll(key, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMap(String key, Map map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getParam(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public List getList(String key) {
        return Collections.singletonList(redisTemplate.opsForList().leftPop(key));
    }


    public Map getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除对应的 value
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

}
