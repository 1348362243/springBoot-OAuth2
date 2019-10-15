package com.wx.spring.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis 操作类
 * @Description:
 * @Title: RedisUtils
 * @author wangxin
 * @date 2019年10月14日
 */
@Component
public class RedisUtils {

	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	
	/**
	 * 读取缓存
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @param key
	 * @return
	 */
	public Object getKey(final String key){
		Object result = null;
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	/**
	 * 写入缓存
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/**
	 *  写入缓存(设置过期时间)
	 * @Description:  
	 * @author sunhui
	 * @date 2019年10月14日  
	 * @param 
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
