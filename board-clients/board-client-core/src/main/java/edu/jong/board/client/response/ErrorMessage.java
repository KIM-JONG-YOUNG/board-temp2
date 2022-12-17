package edu.jong.board.client.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorMessage {

	private Class<? extends Exception> errorClass;

	private String message;

	private LocalDateTime timestamp;

	@Builder
	public ErrorMessage(Class<? extends Exception> errorClass, String message) {
		super();
		this.errorClass = errorClass;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
}
