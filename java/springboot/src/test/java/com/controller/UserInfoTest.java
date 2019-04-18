package com.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dao.UserDao;
import com.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoTest {

	@Autowired
	UserDao userdao ;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "testredis");
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
//		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

    }
}
