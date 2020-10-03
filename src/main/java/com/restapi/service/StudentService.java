package com.restapi.service;

import org.springframework.data.domain.Page;

import com.restapi.dtos.StudentDetailsDTO;
import com.restapi.entity.StudentEntity;

public interface StudentService {

	public boolean saveStudentInfo(StudentEntity student);

	public Page<StudentDetailsDTO> getStudentInfo(StudentDetailsDTO studentDetailsDto); 
}
