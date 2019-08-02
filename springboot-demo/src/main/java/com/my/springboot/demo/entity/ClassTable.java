package com.my.springboot.demo.entity;

import java.util.List;

public class ClassTable {
//	  `id` int(12) NOT NULL AUTO_INCREMENT,
//	  `c_class_name` varchar(10) DEFAULT NULL,
	private Long id;
	private String className;
	private List<Student> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
