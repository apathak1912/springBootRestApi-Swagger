package com.restapi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.restapi.dtos.StudentDetailsDTO;
import com.restapi.entity.StudentEntity;
import com.restapi.http.Response;
import com.restapi.reposetry.Studentreposetry;
import com.restapi.service.StudentService;
import com.restapi.util.PaginatedResults;
import com.restapi.util.PaginationMetaData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "certification")
@Validated
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = {"local/studentinfo","system/studentinfo","int/studentinfo","prod/studentinfo"})
public class RestController {
	
	@Autowired
	StudentService studentService;
	
	@ResponseStatus
	@ApiOperation(value = "this is for echo response" , notes = "the end point for echo")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "recived the response"),
			@ApiResponse(code =500, message = "internol server error")})
	@GetMapping(value = "/echo/{studentId}")
	public ResponseEntity<Response> getResponse(
			@PathVariable(name = "studentId", required = true ) String studentId,
			@Size(min = 3,max = 10,message = "first name should be minimum of  three charecter or maximum of 10 charecter")
			@Valid@ApiParam(value = "firstName")
			@RequestParam(name = "firstName",required = true)String firstName,
			@Size(min = 3,max = 10,message = "last name should be minimum of  three charecter or maximum of 10 charecter")
			@Valid@ApiParam(value = "lastName")
			@RequestParam(name = "lastName",required = true)String lastName
			){
		Response response = Response.builder().firstName(firstName)
				.studentId(studentId).LastName(lastName).build();
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
		
	}
	
	@ResponseStatus
	@ApiOperation(value = "This api will save studentInfo " , notes = "the end point for persisting student information")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "student information saved success fully"),
			@ApiResponse(code =500, message = "internol server error", response = String.class)})
	@PostMapping(value = "/studentname/{studentname}/studentclass/{studentclass}/studentadd/{studentadd}/saveStudentInfo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveStudentInfo(
			@PathVariable(name = "studentname", required = true ) String studentname,
			@PathVariable(name = "studentclass", required = true ) String studentclass,
			@PathVariable(name = "studentadd", required = true ) String studentadd,
			@ApiParam(value ="date format will be like 'YYYY-MM-DD'")
			@RequestParam("dob") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
            LocalDate dob){
		    studentname = StringEscapeUtils.escapeHtml3(studentname);//it will senetized the path variabl so no buddy can inject script
		StudentEntity studentEntity = StudentEntity.builder().name(studentname)
				.Address(studentadd)
				.std(studentclass)
				.dob(dob)
				.build();
		boolean isStudentInfoSaved = studentService.saveStudentInfo(studentEntity);
		return isStudentInfoSaved? ResponseEntity.ok("Student Info Persisted SuccessFully"):ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		 
		
	}
	
	@ResponseStatus
	@ApiOperation(value = "This api will show the studentInfo  " , notes = "the end point for representing the student information or the data of Student Class")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Feched student information success fully"),
			@ApiResponse(code =500, message = "internol server error", response = String.class)})
	@GetMapping(value = "/getStudentInfo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaginatedResults<List<StudentDetailsDTO>>> getStudentInfo(
			@RequestParam(value ="Page" ,required = false ,defaultValue = "0") int page,
			@RequestParam(value ="Size" ,required = false ,defaultValue = "8") int size,
			@Valid
			@Size(min =3 ,message = "Student Name should be more then 3 char")@ApiParam(value = "Student Name Filter")
			@RequestParam(value = "studentname", required = false ) String studentname,
			@Valid
			@Size(min =2 ,message = "Student class should be more then 2 char")@ApiParam(value = "Student class Filter")
			@RequestParam(name = "studentclass", required = false ) String studentclass,
			@Valid
			@Size(min = 3 ,message = "Student Address should be more then 3 char")@ApiParam(value = "Student Address Filter")
			@RequestParam(name = "studentadd", required = false ) String studentadd,
			@ApiParam(value ="date format will be like 'YYYY-MM-DD'")
			@RequestParam(name = "dob" , required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
            LocalDate dob){
		    studentname = StringEscapeUtils.escapeHtml3(studentname);//it will senetized the path variabl so no buddy can inject script
		StudentDetailsDTO studentDetailsDto = StudentDetailsDTO.builder().name(studentname)
				.Address(studentadd)
				.std(studentclass)
				.dob(dob)
				.page(page)
				.size(size)
				.build();
		Page<StudentDetailsDTO> pageresult = studentService.getStudentInfo(studentDetailsDto);
		
		if(pageresult.hasContent()) {
			PaginatedResults<List<StudentDetailsDTO>> paginatedresult = PaginatedResults.<List<StudentDetailsDTO>>builder()
					.data(pageresult.getContent())
					.paginationMetaData(
							PaginationMetaData.builder()
							.pageNumber(pageresult.getNumber()+1)
							.pageSize(pageresult.getNumberOfElements())
							.pageRecordSize(pageresult.getSize())
							.totalcount(pageresult.getTotalElements())
							.totalPages(pageresult.getTotalPages())
							.build()
							).build();
					
			return ResponseEntity.status(HttpStatus.OK).body(paginatedresult);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}

}
