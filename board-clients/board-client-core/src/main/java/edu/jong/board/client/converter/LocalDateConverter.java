package edu.jong.board.client.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.jong.board.common.BoardConstants;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter = 
    		DateTimeFormatter.ofPattern(BoardConstants.DATE_FORMAT);

	@Override
	public LocalDate convert(String source) {
		try {
			return (StringUtils.isNotBlank(source)) ? LocalDate.parse(source, formatter) : null;
		} catch (Exception e) {
			throw new RuntimeException("올바르지 않은 날짜 형식입니다.");
		}
	}

}
