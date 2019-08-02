package com.my.springboot.demo.entity;

public class StudentExtra {
//	  `id` int(12) NOT NULL AUTO_INCREMENT,
//	  `c_student_id` int(12) DEFAULT NULL,
//	  `c_father_name` varchar(12) DEFAULT NULL,
//	  `c_mother_name` varchar(12) DEFAULT NULL,
	
	private Long id;
	private Long studentId;
	private String fatherName;
	private String motherName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
}
