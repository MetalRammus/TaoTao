package com.zhiyou100.sso.redis;

import java.lang.reflect.Method;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	private JedisConnectionFactory jedisConnectionFactory;
	private RedisTemplate<String, String> redisTemplate;
	private RedisCacheManager redisCacheManager;

	public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,
			RedisCacheManager redisCacheManager) {
		super();
		this.jedisConnectionFactory = jedisConnectionFactory;
		this.redisTemplate = redisTemplate;
		this.redisCacheManager = redisCacheManager;
	}

	public RedisCacheConfig() {
		
	}

	public JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public RedisCacheManager getRedisCacheManager() {
		return redisCacheManager;
	}

	@Bean
	//返回一个接口
	public KeyGenerator cusKeyGenerator() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				//获得 StringBuilder
				StringBuilder sb=new StringBuilder();
				//获得传入对象的全限定名 拼接到StringBuilder对象
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for(Object object:params) {
					sb.append(object.toString());
				}
				return null;
			}
		};
		
	}
	
}

