package com.restapi.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.restapi.http.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "certification")
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = {"local/sayhello","system/sayhello","int/sayhello","prod/sayhello"})
public class RestController {
	@ResponseStatus
	@ApiOperation(value = "this is for echo response" , notes = "the end point for echo")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "recived the response"),
			@ApiResponse(code =500, message = "internol server error")})
	@GetMapping(value = "/echo{studentId}")
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

}
