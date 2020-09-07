package com.restapi.reposetry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.entity.StudentEntity;

public interface Studentreposetry extends JpaRepository<StudentEntity, Long>  {
 
}
