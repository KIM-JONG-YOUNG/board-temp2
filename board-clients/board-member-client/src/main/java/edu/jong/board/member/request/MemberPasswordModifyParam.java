package edu.jong.board.member.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPasswordModifyParam implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String curPassword;

	@NotBlank
	private String newPassword;

}
