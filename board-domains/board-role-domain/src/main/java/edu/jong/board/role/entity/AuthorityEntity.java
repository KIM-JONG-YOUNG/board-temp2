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
import edu.jong.board.common.CodeEnum.Method;
import edu.jong.board.domain.converter.AbstractAttributeConverter;
import edu.jong.board.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = TableNames.TB_AUTH)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorityEntity extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authority_no",
			unique = true,
			nullable = false)
	private long no;

	@Setter
	@Convert(converter = MethodAttributeConverter.class)
	@Column(name = "authority_access_method",
			length = 3,
			nullable = false)
	private Method accessMethod;

	@Setter
	@Column(name = "authority_access_url_pattern",
			length = 60,
			nullable = false)
	private String accessUrlPattern;

	@Converter
	public static class MethodAttributeConverter extends AbstractAttributeConverter<Method, String> {

		public MethodAttributeConverter() {
			super(Method.class, false);
		}
	}

	@Builder
	public AuthorityEntity(long no, Method accessMethod, String accessUrlPattern) {
		super();
		this.no = no;
		this.accessMethod = accessMethod;
		this.accessUrlPattern = accessUrlPattern;
	}
	
}
