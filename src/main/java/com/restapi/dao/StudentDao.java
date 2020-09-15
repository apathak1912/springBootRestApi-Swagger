package com.restapi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restapi.entity.StudentEntity;
import com.restapi.reposetry.Studentreposetry;

@Component
public class StudentDao {

	@Autowired
	Studentreposetry studentreposetry;
	
	public boolean persistStudent(StudentEntity student) {
		StudentEntity studentEntity = studentreposetry.saveAndFlush(student);
		return (studentEntity!=null)? true: false;
	}
}
