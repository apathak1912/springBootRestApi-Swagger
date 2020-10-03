package com.restapi.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaginatedResults<T> {
	
	public PaginatedResults(T data, PaginationMetaData metadata) {
		
		this.data = data;
		this.paginationMetaData = metadata;
	}

	@JsonProperty("data")
	private T data;
	
	@JsonProperty("metadata")
	private PaginationMetaData paginationMetaData;

}
