package edu.jong.board.member.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.jong.board.common.CodeEnum.Gender;
import edu.jong.board.common.CodeEnum.State;
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
public class MemberDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long no;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private Gender gender;
	
	private String email;
	
	private LocalDateTime createdDateTime;

	private LocalDateTime updatedDateTime;
	
	private State state;

}
