package edu.jong.board.role.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.jong.board.common.BoardConstants.TableNames;
import edu.jong.board.domain.entity.BaseEntity;
import edu.jong.board.member.entity.MemberEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@IdClass(RoleMemberEntity.PK.class)
@Table(name = TableNames.TB_ROLE_MEMBER)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleMemberEntity extends BaseEntity {

	@Getter
	@ToString
	@EqualsAndHashCode
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	public static class PK implements Serializable {

		private static final long serialVersionUID = 1L;
		private long role;
		private long member;
	}
	
	@Id @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_no",
				nullable = false)
	private RoleEntity role;

	@Id @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no",
				nullable = false)
	private MemberEntity member;

	@Builder
	public RoleMemberEntity(RoleEntity role, MemberEntity member) {
		super();
		this.role = role;
		this.member = member;
	}

}
