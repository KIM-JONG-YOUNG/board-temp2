package edu.jong.board.member.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import edu.jong.board.common.CodeEnum.State;
import edu.jong.board.member.request.MemberAddParam;
import edu.jong.board.member.request.MemberModifyParam;
import edu.jong.board.member.request.MemberPasswordModifyParam;
import edu.jong.board.member.request.MemberSearchCond;
import edu.jong.board.member.response.MemberDTO;

@Validated
public interface MemberService {

	long addMember(@Valid MemberAddParam param);

	long modifyMember(long memberNo, @Valid MemberModifyParam param);

	long modifyMemberPassword(long memberNo, @Valid MemberPasswordModifyParam param);

	long modifyMemberState(long memberNo, @NotNull State state);

	MemberDTO getMemberByNo(long memberNo);

	MemberDTO getMemberByUsername(String username);
	
	long countMemberList(@Valid MemberSearchCond cond);

	List<MemberDTO> searchMemberList(@Valid MemberSearchCond cond);

}
