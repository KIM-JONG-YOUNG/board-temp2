package edu.jong.board.member.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.jong.board.common.CodeEnum.Gender;
import edu.jong.board.common.CodeEnum.MemberGroup;
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
public class MemberAddParam implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	@Size(max = 30)
	private String name;
	
	@NotNull
	private Gender gender;
	
	@NotBlank
	@Email
	@Size(max = 60)
	private String email;
	
	@NotNull
	private MemberGroup group;

}
