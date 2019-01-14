package com.northrow.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Value("${security.user.name}")
	private String username;

	@Value("${security.user.password}")
	private String password;

	@Value("${security.user.role}")
	private String role;

	@Test
	public void testSecurity() throws Exception {

		ResponseEntity<String> respEntity = restTemplate.withBasicAuth(username, password)
				.getForEntity("/food/search/milk", String.class);
		assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(respEntity.getBody()).isEqualTo("[]");
	}

}
