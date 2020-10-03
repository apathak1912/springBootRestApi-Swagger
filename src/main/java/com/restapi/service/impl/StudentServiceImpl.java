package com.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restapi.dao.StudentDao;
import com.restapi.dtos.StudentDetailsDTO;
import com.restapi.entity.StudentEntity;
import com.restapi.entity.StudentInfoSpecification;
import com.restapi.reposetry.Studentreposetry;
import com.restapi.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	Studentreposetry studentreposetry;
	
	@Override
	public boolean saveStudentInfo(StudentEntity student) {
		//studentreposetry.existsByNameAnd
		return studentDao.persistStudent(student);
	}

	@Override
	public Page<StudentDetailsDTO> getStudentInfo(StudentDetailsDTO studentDetailsDto) {
	    StudentInfoSpecification spec = StudentInfoSpecification.builder()
	    		.studentDetailsDTO(studentDetailsDto)
	    		.build();
	   org.springframework.data.domain.Pageable pageable = PageRequest.of(studentDetailsDto.getPage(), studentDetailsDto.getSize());
	   Page<StudentEntity> student = studentreposetry.findAll(spec,pageable);
		return student.map(this :: convertToStudentDetailsDTO);
	}
	
	public StudentDetailsDTO convertToStudentDetailsDTO(StudentEntity studentEntity) {
		return StudentDetailsDTO.builder()
				.id(studentEntity.getId())
				.name(studentEntity.getName())
				.std(studentEntity.getStd())
				.Address(studentEntity.getAddress())
				.dob(studentEntity.getDob())
				.build();
	}
	

}
