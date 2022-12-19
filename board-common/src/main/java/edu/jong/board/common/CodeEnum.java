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

	@Getter
	@RequiredArgsConstructor
	public static enum Gender implements CodeEnum<Character> {

		MAIL('M'), FEMAIL('F');
		
		private final Character code;
	}

	public static enum MemberGroup implements CodeEnum<String> {

		SUPER_ADMIN, ADMIN, USER;

		@Override
		public String getCode() {
			return this.name();
		}
	}

	public static enum Method implements CodeEnum<String> {

		ALL, GET, POST, PUT, DELETE;
		
		@Override
		public String getCode() {
			return this.name();
		}
	}
}
