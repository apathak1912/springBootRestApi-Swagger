package com.restapi.http;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Validated
@Builder
@AllArgsConstructor
@Data
public class Response {

	@JsonProperty("studentId")
	public String studentId;
	
	@JsonProperty("firstname")
	public String firstName;
	
	@JsonProperty("Lastname")
	public String LastName;
}
