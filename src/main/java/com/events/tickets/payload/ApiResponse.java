package com.events.tickets.payload;

 
import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse {
	private boolean success;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private DataResponse data;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	public ApiResponse(boolean success, DataResponse data, String message) {
		super();
		this.success = success;
		this.data = data;
		this.message = message;
	}
	public ApiResponse(String message, DataResponse data) {
		super();
		this.success = true;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(String message) {
		super();
		this.success = true;
		this.message = message;

	}

	public ApiResponse(DataResponse data) {
		super();
		this.success = true;
		this.data = data;

	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public DataResponse getData() {
		return data;
	}
	public void setData(DataResponse data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
