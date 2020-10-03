package com.restapi.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationMetaData {

	@JsonProperty("totalPages")
	public long totalPages;
	@JsonProperty("totalcount")
	public long totalcount;
	@JsonProperty("pageNumber")
	public int pageNumber;
	@JsonProperty("pageSize")
	public int pageSize;
	@JsonProperty("pageRecordSize")
	public int pageRecordSize;
}
