package com.my.springboot.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.springboot.demo.entity.ClassTable;

@Mapper
public interface ClassMapper2 {
	ClassTable selectClass(@Param("classId") Long id);
}
