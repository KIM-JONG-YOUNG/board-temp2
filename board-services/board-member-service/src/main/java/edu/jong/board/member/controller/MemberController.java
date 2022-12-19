package edu.jong.board.member.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import edu.jong.board.client.exception.DataAlreadyExistsException;
import edu.jong.board.client.exception.DataNotFoundException;
import edu.jong.board.client.exception.DataStateDeactiveException;
import edu.jong.board.client.response.ErrorMessage;
import edu.jong.board.client.response.PagingList;
import edu.jong.board.client.response.ErrorMessage.ErrorMessageBuilder;
import edu.jong.board.common.CodeEnum.State;
import edu.jong.board.member.exception.PasswordNotMatchException;
import edu.jong.board.member.exception.SuperAdminMemberSaveException;
import edu.jong.board.member.operation.MemberOperation;
import edu.jong.board.member.request.MemberAddParam;
import edu.jong.board.member.request.MemberModifyParam;
import edu.jong.board.member.request.MemberPasswordModifyParam;
import edu.jong.board.member.request.MemberSearchCond;
import edu.jong.board.member.response.MemberDTO;
import edu.jong.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberOperation {

	private final MemberService memberService;
	
	@Override
	public ResponseEntity<Void> addMember(MemberAddParam param) {
		String url = "/members/" + memberService.addMember(param);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(HttpHeaders.LOCATION, url).build();
	}

	@Override
	public ResponseEntity<Void> modifyMember(long memberNo, MemberModifyParam param) {
		String url = "/members/" + memberService.modifyMember(memberNo, param);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, url).build();
	}

	@Override
	public ResponseEntity<Void> modifyMemberPassword(long memberNo, MemberPasswordModifyParam param) {
		String url = "/members/" + memberService.modifyMemberPassword(memberNo, param);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, url).build();
	}

	@Override
	public ResponseEntity<Void> modifyMemberState(long memberNo, State state) {
		String url = "/members/" + memberService.modifyMemberState(memberNo, state);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, url).build();
	}

	@Override
	public ResponseEntity<MemberDTO> getMember(long memberNo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(memberService.getMemberByNo(memberNo));
	}

	@Override
	public ResponseEntity<MemberDTO> getMember(String username) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(memberService.getMemberByUsername(username));
	}

	@Override
	public ResponseEntity<PagingList<MemberDTO>> searchMemberList(MemberSearchCond cond) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new PagingList<>(cond,
						memberService.searchMemberList(cond),
						memberService.countMemberList(cond)));
	}

	@ExceptionHandler({
		PasswordNotMatchException.class,
		SuperAdminMemberSaveException.class
	})
	ResponseEntity<ErrorMessage> handleCutomException(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorMessage.builder()
						.errorClass(e.getClass())
						.message(e.getMessage())
						.build());
	}
}
