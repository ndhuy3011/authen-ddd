package com.ndhuy.authen;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ndhuy.authen.users.infrastructure.event.UserCommunicateGrpc;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class AuthenApplicationTests {

	@Resource UserCommunicateGrpc userGrpc;

	@Test
	void contextLoads() {
	var reponse=  userGrpc.authenticate("caubediza", "123456");
	log.info(reponse.getUuid());
	Assert.assertEquals(reponse.getUuid(), "ac7555bd-9944-490f-88ba-ff21050522ba");
	}

}
