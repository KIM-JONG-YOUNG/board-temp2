package edu.jong.board.client.request;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PagingParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Builder.Default
	private long page = 1;
	
	@Builder.Default
	private long pageRows = 10;
	
	@Builder.Default
	private long pageGroupSize = 10;

}
