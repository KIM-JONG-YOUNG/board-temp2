package edu.jong.board.member_role.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import edu.jong.board.common.BoardConstants.TableNames;
import edu.jong.board.domain.entity.BaseEntity;
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
@IdClass(MemberRoleEntity.PK.class)
@Table(name = TableNames.TB_MEMBER_ROLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoleEntity extends BaseEntity {

	@Getter
	@Builder
	@ToString
	@EqualsAndHashCode
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	public static class PK implements Serializable {

		private static final long serialVersionUID = 1L;
		private long memberNo;
		private long roleNo;
	}

	@Id
	@Column(name = "member_no",
			unique = true,
			nullable = false)
	private long memberNo;

	@Id
	@Column(name = "role_no",
			unique = true,
			nullable = false)
	private long roleNo;

	@Builder
	public MemberRoleEntity(long memberNo, long roleNo) {
		super();
		this.memberNo = memberNo;
		this.roleNo = roleNo;
	}

}
