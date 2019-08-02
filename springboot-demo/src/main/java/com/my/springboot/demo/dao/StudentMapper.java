package com.my.springboot.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.springboot.demo.entity.Student;
import com.my.springboot.demo.entity.StudentExtra;

@Mapper
public interface StudentMapper {
	List<Student> selectStudentsByClass(@Param("classId") Long classId);
	
	Student selectStudent(@Param("id") Long id);

	StudentExtra selectStudentExtra(@Param("id") Long id);
}
