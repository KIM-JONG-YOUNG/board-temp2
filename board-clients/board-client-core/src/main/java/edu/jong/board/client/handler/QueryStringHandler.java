package edu.jong.board.client.handler;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QueryStringHandler {

	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.initDirectFieldAccess();
	}
}
