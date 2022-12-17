package edu.jong.board.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import edu.jong.board.common.CodeEnum.State;
import edu.jong.board.domain.converter.AbstractAttributeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	private LocalDateTime createdDateTime;
	
	@LastModifiedDate
	private LocalDateTime updatedDateTime;
	
	@Setter
	@Convert(converter = StateAttrConverter.class)
	private State state = State.ACTIVE;

	@Converter
	public static class StateAttrConverter extends AbstractAttributeConverter<State, Integer> {

		public StateAttrConverter() {
			super(State.class, false);
		}
		
	}
	
}
