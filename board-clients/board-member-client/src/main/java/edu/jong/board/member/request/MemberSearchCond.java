package edu.jong.board.member.request;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.jong.board.client.request.PagingParam;
import edu.jong.board.common.BoardConstants;
import edu.jong.board.common.CodeEnum.Gender;
import edu.jong.board.common.SortEnums.MemberSortBy;
import edu.jong.board.common.SortEnums.OrderBy;
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
public class MemberSearchCond extends PagingParam {
	
	private static final long serialVersionUID = 1L;

	@Size(max = 30)
	private String username;
	
	@Size(max = 30)
	private String name;
	
	private Gender gender;
	
	@Size(max = 60)
	private String email;
	
	@JsonFormat(pattern = BoardConstants.DATE_FORMAT)
	private LocalDate from;
	
	@JsonFormat(pattern = BoardConstants.DATE_FORMAT)
	private LocalDate to;
	
	private OrderBy orderBy;
	
	private MemberSortBy sortBy;
	
}
