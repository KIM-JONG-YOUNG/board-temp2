package edu.jong.board.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface CodeEnum<V> {

	V getCode();
	
	@Getter
	@RequiredArgsConstructor
	public static enum State implements CodeEnum<Integer> {

		ACTIVE(1), DEACTIVE(0);

		private final Integer code;
		
	}
	
}
