package edu.jong.board.member.operation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.jong.board.common.CodeEnum.State;
import edu.jong.board.member.request.MemberAddParam;
import edu.jong.board.member.request.MemberModifyParam;
import edu.jong.board.member.request.MemberPasswordModifyParam;
import edu.jong.board.member.request.MemberSearchCond;

@FeignClient("member-service")
public interface MemberOperation {

	@PostMapping(value = "/members",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> addMember(
			@RequestBody MemberAddParam param);

	@PutMapping(value = "/members/{memberNo}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyMember(
			@PathVariable long memberNo,
			@RequestBody MemberModifyParam param);

	@PutMapping(value = "/members/{memberNo}/password",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyMemberPassword(
			@PathVariable long memberNo,
			@RequestBody MemberPasswordModifyParam param);

	@PutMapping(value = "/members/{memberNo}/state",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyMemberState(
			@PathVariable long memberNo,
			@RequestBody State state);

	@GetMapping(value = "/members/{memberNo}")
	ResponseEntity<Void> getMember(
			@PathVariable long memberNo);

	@GetMapping(value = "/members/username/{username}")
	ResponseEntity<Void> getMember(
			@PathVariable String username);

	@GetMapping(value = "/members/search")
	ResponseEntity<Void> searchMemberList(
			MemberSearchCond cond);

}
