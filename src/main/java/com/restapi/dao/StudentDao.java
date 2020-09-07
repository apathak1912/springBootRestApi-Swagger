package com.restapi.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.restapi.entity.StudentEntity;
import com.restapi.reposetry.Studentreposetry;

public class StudentDao {

	@Autowired
	Studentreposetry studentreposetry;
	
	public boolean persistStudent(StudentEntity student) {
		StudentEntity studentEntity = studentreposetry.saveAndFlush(student);
		return (studentEntity!=null)? true: false;
	}
}
