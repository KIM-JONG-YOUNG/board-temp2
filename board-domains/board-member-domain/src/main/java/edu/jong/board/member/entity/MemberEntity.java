package edu.jong.board.member.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.jong.board.common.BoardConstants.TableNames;
import edu.jong.board.common.CodeEnum.Gender;
import edu.jong.board.domain.converter.AbstractAttributeConverter;
import edu.jong.board.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = TableNames.TB_MEMBER)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_no",
			unique = true,
			nullable = false)
	private long no;
	
	@Column(name = "member_username",
			length = 30,
			unique = true,
			nullable = false)
	private String username;
	
	@Column(name = "member_password",
			length = 60,
			nullable = false)
	private String password;
	
	@Column(name = "member_name",
			length = 30,
			nullable = false)
	private String name;
	
	@Convert(converter = GenderAttributeConverter.class)
	@Column(name = "member_gender",
			length = 1,
			nullable = false)
	private Gender gender;
	
	@Column(name = "member_email",
			length = 60,
			nullable = false)
	private String email;

	@Converter
	public static class GenderAttributeConverter extends AbstractAttributeConverter<Gender, Character> {

		public GenderAttributeConverter() {
			super(Gender.class, false);
		}
	}
	
}
