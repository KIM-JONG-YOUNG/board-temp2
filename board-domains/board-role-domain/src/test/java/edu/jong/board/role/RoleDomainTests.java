package edu.jong.board.role;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import edu.jong.board.common.BoardConstants;
import edu.jong.board.common.CodeEnum.RoleName;
import edu.jong.board.role.entity.RoleEntity;
import edu.jong.board.role.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("h2")
@SpringBootApplication(scanBasePackages = BoardConstants.ROOT_PACKAGE)
public class RoleDomainTests {

	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	void contextLoads() {
		
		log.info("Create Table And Validate Complete!");
		
		EnumSet.allOf(RoleName.class).forEach(x -> {
			log.info("Insert Role => {}", 
					roleRepository.save(RoleEntity.builder().name(x).build()));
		});
	}
	
}
