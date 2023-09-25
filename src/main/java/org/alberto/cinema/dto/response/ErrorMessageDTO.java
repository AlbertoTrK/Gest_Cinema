package org.alberto.cinema.dto.response;

import java.time.LocalDateTime;

public class ErrorMessageDTO {
	
	private String message;
	private LocalDateTime data;

	public ErrorMessageDTO(String message) {
		this.message = message;
		data = LocalDateTime.now();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
	
}
