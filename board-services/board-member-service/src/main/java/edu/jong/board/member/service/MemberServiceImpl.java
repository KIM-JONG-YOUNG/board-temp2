package edu.jong.board.member.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQueryFactory;

import edu.jong.board.client.exception.DataAlreadyExistsException;
import edu.jong.board.client.exception.DataNotFoundException;
import edu.jong.board.client.exception.DataStateDeactiveException;
import edu.jong.board.common.CodeEnum.State;
import edu.jong.board.common.SortEnums.OrderBy;
import edu.jong.board.domain.utils.QueryDslUtils;
import edu.jong.board.member.entity.MemberEntity;
import edu.jong.board.member.entity.QMemberEntity;
import edu.jong.board.member.exception.PasswordNotMatchException;
import edu.jong.board.member.mapper.MemberDomainMapper;
import edu.jong.board.member.repository.MemberRepository;
import edu.jong.board.member.request.MemberAddParam;
import edu.jong.board.member.request.MemberModifyParam;
import edu.jong.board.member.request.MemberPasswordModifyParam;
import edu.jong.board.member.request.MemberSearchCond;
import edu.jong.board.member.response.MemberDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final PasswordEncoder encoder;
	private final MemberRepository memberRepository;
	private final MemberDomainMapper memberDomainMapper;
	
	private final JPAQueryFactory jpaQueryFactory;
	private final QMemberEntity TB_MEMBER = QMemberEntity.memberEntity;
	
	private BooleanExpression[] convertBooleanExpression(MemberSearchCond cond) {
		return new BooleanExpression[] {
				QueryDslUtils.containsIfPresent(TB_MEMBER.username, cond.getUsername()),
				QueryDslUtils.containsIfPresent(TB_MEMBER.name, cond.getName()),
				QueryDslUtils.equalsIfPresent(TB_MEMBER.gender, cond.getGender()),
				QueryDslUtils.containsIfPresent(TB_MEMBER.email, cond.getEmail()),
				QueryDslUtils.betweenIfPresent(TB_MEMBER.createdDateTime, cond.getFrom(), cond.getTo())
		};
	}
	
	private OrderSpecifier<?> convertOrderSpecifier(MemberSearchCond cond) {
		
		Order orderBy = (cond.getOrderBy() == OrderBy.ASC) ? Order.ASC : Order.DESC;

		switch (cond.getSortBy()) {
		case NO:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.no);
		case USERNAME:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.username);
		case NAME:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.name);
		case GENDER:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.gender);
		case EMAIL:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.email);
		case CREATE_DATE_TIME:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.createdDateTime);
		case UPDATE_DATE_TIME:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.updatedDateTime);
		default:
			return new OrderSpecifier<>(orderBy, TB_MEMBER.no);
		}
	}
	
	@Transactional
	@Override
	public long addMember(@Valid MemberAddParam param) {
		
		if (memberRepository.existsByUsername(param.getUsername()))
			throw new DataAlreadyExistsException("동일한 계정의 사용자가 존재합니다.");
		
		MemberEntity member = memberRepository.save(memberDomainMapper.toEntity(param, encoder));
		
		return member.getNo();
	}

	@Transactional
	@Override
	public long modifyMember(long memberNo, @Valid MemberModifyParam param) {

		MemberEntity member = memberRepository.findByIdForUpdate(memberNo)
				.orElseThrow(() -> new DataNotFoundException("사용자가 존재하지 않습니다."));
		
		if (member.getState() == State.DEACTIVE)
			throw new DataStateDeactiveException("비활성화된 사용자입니다.");
		
		memberRepository.save(memberDomainMapper.updateEntity(param, member));

		return member.getNo();
	}

	@Transactional
	@Override
	public long modifyMemberPassword(long memberNo, @Valid MemberPasswordModifyParam param) {
		
		MemberEntity member = memberRepository.findByIdForUpdate(memberNo)
				.orElseThrow(() -> new DataNotFoundException("사용자가 존재하지 않습니다."));

		if (member.getState() == State.DEACTIVE)
			throw new DataStateDeactiveException("비활성화된 사용자입니다.");

		if (encoder.matches(param.getCurPassword(), member.getPassword()))
			throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
		
		memberRepository.save(memberDomainMapper.updateEntity(param, encoder, member));

		return member.getNo();
	}

	@Transactional
	@Override
	public long modifyMemberState(long memberNo, @NotNull State state) {

		MemberEntity member = memberRepository.findByIdForUpdate(memberNo)
				.orElseThrow(() -> new DataNotFoundException("사용자가 존재하지 않습니다."));

		member.setState(state);
		
		memberRepository.save(member);
		
		return member.getNo();
	}

	@Transactional(readOnly = true)
	@Override
	public MemberDTO getMemberByNo(long memberNo) {
		
		MemberEntity member = memberRepository.findById(memberNo)
				.orElseThrow(() -> new DataNotFoundException("사용자가 존재하지 않습니다."));

		if (member.getState() == State.DEACTIVE)
			throw new DataStateDeactiveException("비활성화된 사용자입니다.");

		return memberDomainMapper.toDTO(member);
	}

	@Transactional(readOnly = true)
	@Override
	public MemberDTO getMemberByUsername(String username) {

		MemberEntity member = memberRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("사용자가 존재하지 않습니다."));

		if (member.getState() == State.DEACTIVE)
			throw new DataStateDeactiveException("비활성화된 사용자입니다.");

		return memberDomainMapper.toDTO(member);
	}

	@Transactional(readOnly = true)
	@Override
	public long countMemberList(@Valid MemberSearchCond cond) {
		return jpaQueryFactory
				.select(TB_MEMBER.count())
				.from(TB_MEMBER)
				.where(convertBooleanExpression(cond))
				.fetchOne();
	}

	@Transactional(readOnly = true)
	@Override
	public List<MemberDTO> searchMemberList(@Valid MemberSearchCond cond) {
		return jpaQueryFactory
				.select(TB_MEMBER)
				.from(TB_MEMBER)
				.where(convertBooleanExpression(cond))
				.orderBy(convertOrderSpecifier(cond))
				.offset(cond.offset())
				.limit(cond.getPageRows())
				.fetch().stream()
				.map(x -> memberDomainMapper.toDTO(x))
				.collect(Collectors.toList());
	}

}
