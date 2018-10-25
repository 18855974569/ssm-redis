package com.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 
 * @描述: 静态注入中间类
 * @author wjx
 * @date 2018-10-12
 */
public class RedisCacheTransfer {

	
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}
}