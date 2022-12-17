package edu.jong.board.role.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@IdClass(RoleAuthorityEntity.PK.class)
@Table(name = TableNames.TB_ROLE_AUTH)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleAuthorityEntity extends BaseEntity {

	@Getter
	@Builder
	@ToString
	@EqualsAndHashCode
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	public static class PK implements Serializable {

		private static final long serialVersionUID = 1L;
		private long role;
		private long authority;
	}

	@Id @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_no",
				nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RoleEntity role;

	@Id @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authority_no",
				nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AuthorityEntity authority;
	
	@Builder
	public RoleAuthorityEntity(RoleEntity role, AuthorityEntity authority) {
		super();
		this.role = role;
		this.authority = authority;
	}
	
}
