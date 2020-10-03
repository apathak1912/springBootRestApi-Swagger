package com.restapi.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDetailsDTO {
private Integer id;
	
	private String name;
	
	private String std;

	private String Address;

	private LocalDate dob;
	
	private int page;
	
	private int size;

}
