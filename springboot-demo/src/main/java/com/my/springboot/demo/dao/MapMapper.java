package com.my.springboot.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MapMapper {
		void create(@Param("map") Map<String, Object> map);
}
