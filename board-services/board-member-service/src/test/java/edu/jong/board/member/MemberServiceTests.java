package edu.jong.board.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.jong.board.common.CodeEnum.Gender;
import edu.jong.board.common.CodeEnum.State;
import edu.jong.board.member.request.MemberAddParam;
import edu.jong.board.member.request.MemberModifyParam;
import edu.jong.board.member.request.MemberPasswordModifyParam;
import edu.jong.board.member.response.MemberDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles({"h2", "test"})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberServiceTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordEncoder encoder;

	@Test
	void contextLoads() {
		log.info("Context Loads!");
	}
	
	@Order(1)
	@Test
	void addMemberTest() throws Exception {
		
		// given
		MemberAddParam param = MemberAddParam.builder()
				.username("super_admin")
				.password("1234")
				.name("admin")
				.gender(Gender.MAIL)
				.email("admin@example.com")
				.build();
		
		// when
		mockMvc.perform(post("/members")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(param)))
				.andDo(print())
//				.andExpect(status().isCreated());
				.andExpect(status().isConflict());
		
		// then
//		MvcResult result = mockMvc.perform(get("/members/1"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andReturn();
//	
//		MemberDTO content = objectMapper.readValue(result.getResponse().getContentAsString(), MemberDTO.class);
//
//		assertEquals(param.getUsername(), content.getUsername());
//		assertEquals(param.getName(), content.getName());
//		assertEquals(param.getGender(), content.getGender());
//		assertEquals(param.getEmail(), content.getEmail());

	}
	
	@Order(2)
	@Test
	void modifyMemberTest() throws Exception {
		
		// given
		MemberModifyParam param = MemberModifyParam.builder()
				.name("admin-update")
				.email("admin-update@example.com")
				.build();
		
		// when
		mockMvc.perform(put("/members/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(param)))
				.andDo(print())
				.andExpect(status().isNoContent());
		
		// then
		MvcResult result = mockMvc.perform(get("/members/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	
		MemberDTO content = objectMapper.readValue(result.getResponse().getContentAsString(), MemberDTO.class);

		assertEquals(param.getName(), content.getName());
		assertEquals(param.getEmail(), content.getEmail());
	}
	
	@Order(3)
	@Test
	void modifyMemberPasswordTest() throws Exception {
		
		// given
		MemberPasswordModifyParam param = MemberPasswordModifyParam.builder()
				.curPassword("1234")
				.newPassword("1234-update")
				.build();
		
		// when
		mockMvc.perform(put("/members/1/password")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(param)))
				.andDo(print())
				.andExpect(status().isNoContent());
		
		// then
		MvcResult result = mockMvc.perform(get("/members/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	
		MemberDTO content = objectMapper.readValue(result.getResponse().getContentAsString(), MemberDTO.class);

		assertTrue(encoder.matches(param.getNewPassword(), content.getPassword()));
	}
	
	@Order(5)
	@Test
	void modifyMemberStateTest() throws Exception {
		
		// given
		State state = State.DEACTIVE;
		
		// when
		mockMvc.perform(put("/members/1/state")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(state)))
				.andDo(print())
				.andExpect(status().isNoContent());
		
		// then
		mockMvc.perform(get("/members/1"))
				.andDo(print())
				.andExpect(status().isGone())
				.andReturn();
	
	}
	
	@Order(4)
	@Test
	void getMemberByUsernameTest() throws Exception {
		
		// given
		String username = "super_admin";
		
		// when
		MvcResult result = mockMvc.perform(get("/members/username/" + username))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		MemberDTO content = objectMapper.readValue(result.getResponse().getContentAsString(), MemberDTO.class);

		// then
		assertEquals(username, content.getUsername());
	}
}
