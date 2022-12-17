package edu.jong.board.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import edu.jong.board.common.BoardConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("h2")
@SpringBootApplication(scanBasePackages = BoardConstants.ROOT_PACKAGE)
public class MemberDomainTests {

	@Test
	void contextLoads() {
		log.info("Create Table And Validate Complete!");
	}
	
}
