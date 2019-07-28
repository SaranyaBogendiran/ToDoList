package com.saranya.microservice.todolist.Exception;

import java.util.Date;

public class ErrorResponse {
    private Date timeStamp;
    private String message;
    private String Description;
	public ErrorResponse(Date timeStamp, String message, String description) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		Description = description;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String toString() {
		return "ErrorResponse [timeStamp=" + timeStamp + ", message=" + message + ", Description=" + Description + "]";
	}
    
    
    
}
