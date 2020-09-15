package com.restapi.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name ="Student")
@Data
@Builder
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDENT_ID" ,nullable = false)
	private Integer id;
	
	@Column(name = "STUDENT_NAME")
	private String name;
	
	@Column(name = "STUDENT_CLASS")
	private String std;
	
	@Column(name = "STUDENT_Address")
	private String Address;
	
	@Column(name = "DOB")
	private LocalDate dob;

}
