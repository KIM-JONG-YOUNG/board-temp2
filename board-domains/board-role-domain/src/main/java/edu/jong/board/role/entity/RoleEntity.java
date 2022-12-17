package edu.jong.board.role.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.jong.board.common.BoardConstants.TableNames;
import edu.jong.board.common.CodeEnum.RoleName;
import edu.jong.board.domain.converter.AbstractAttributeConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = TableNames.TB_ROLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_no",
			unique = true,
			nullable = false)
	private long no;
	
	@Convert(converter = RoleNameAttributeConverter.class)
	@Column(name = "role_name",
			unique = true,
			nullable = false)
	private RoleName name;

	@Converter
	public static class RoleNameAttributeConverter extends AbstractAttributeConverter<RoleName, String> {

		public RoleNameAttributeConverter() {
			super(RoleName.class, false);
		}
	}

	@Builder
	public RoleEntity(RoleName name) {
		super();
		this.name = name;
	}

}
