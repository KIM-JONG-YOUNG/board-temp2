package edu.jong.board.member.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.jong.board.common.BoardConstants;
import edu.jong.board.common.CodeEnum.Gender;
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
	
	@JsonFormat(pattern = BoardConstants.DATE_TIME_FORMAT)
	private LocalDateTime createdDateTime;

	@JsonFormat(pattern = BoardConstants.DATE_TIME_FORMAT)
	private LocalDateTime updatedDateTime;
	
}
