package com.zhiyou100;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;


public class Redis {


	@Test
	public void testSpringJedisSingle() {
		ApplicationContext app=new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
		RedisTemplate redisTemplate =(RedisTemplate) app.getBean("redisTemplate");
		Long size=redisTemplate.opsForValue().size("getItemBaseInfo");
		System.out.println("==========================="+size);
		
	}

}
