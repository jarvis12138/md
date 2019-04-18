package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.UserDao;
import com.model.User;

@Controller
public class UserInfo {
	private static Logger log = LoggerFactory.getLogger(UserInfo.class); 

	@Autowired
	UserDao userdao ;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping("/home/{id}")
	@ResponseBody
	public Map<String, Object>  getUserInfo( @PathVariable("id") Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
//		User user = userdao.getBeanById(2);
//		System.out.println(user);
		
//		List<User> user = userdao.getBeanById(id);
//		for(User username: user) {
//			System.out.println(username.getUsername());
//		}
//		map.put("code", 0);
//		map.put("data", user);
		
		stringRedisTemplate.opsForValue().set("aaa", "123");
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));


		return map;
	}
}
