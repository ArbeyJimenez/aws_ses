package com.awsses.app;

/**
 * @see https://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services
 */

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.awsses.app.services.AwsSesService;

@WebMvcTest(AwsController.class)
public class AwsController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AwsSesService service;
	
	@Test
	public void pruebaSaludo() throws Exception {
		when(service.saludo()).thenReturn("hola mundo cruel");
		this.mockMvc.perform(get("/url")).andDo(print()).andExpect(status().isOk());
	}

}
