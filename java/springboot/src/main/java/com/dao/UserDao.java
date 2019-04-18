package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.model.User;

@Mapper
public interface UserDao {

	@Select("select * from user ")
	public List<User> getBeanById(Integer id);
}
