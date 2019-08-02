package com.my.springboot.demo.entity;

public class Student {
//	  `id` int(12) NOT NULL AUTO_INCREMENT,
//	  `c_name` varchar(12) DEFAULT NULL,
//	  `c_class_id` int(12) DEFAULT NULL,

	private Long id;
	private Long classId;
	private String name;
	private StudentExtra studentExtra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentExtra getStudentExtra() {
		return studentExtra;
	}

	public void setStudentExtra(StudentExtra studentExtra) {
		this.studentExtra = studentExtra;
	}

}
