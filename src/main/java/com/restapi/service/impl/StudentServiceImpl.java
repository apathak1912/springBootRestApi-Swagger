package com.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.restapi.dao.StudentDao;
import com.restapi.entity.StudentEntity;
import com.restapi.service.StudentService;

public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao studentDao;
	
	@Override
	public boolean saveStudentInfo(StudentEntity student) {
		return studentDao.persistStudent(student);
	}

}
