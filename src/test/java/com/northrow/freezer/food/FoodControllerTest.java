package com.northrow.freezer.food;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northrow.freezer.food.Food;
import com.northrow.freezer.food.FoodController;
import com.northrow.freezer.food.FoodService;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodController.class)																																																																																																																																				
public class FoodControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FoodService foodService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${security.user.name}")
	private String username;
	
	@Value("${security.user.password}")
	private String password;
	
	@Value("${security.user.role}")
	private String role;
	
	@Test
	public void testAddFood() throws Exception {
		Food food = new Food("Eggs", "Non Veg", 10);
		String content = objectMapper.writeValueAsString(food);
		
		this.mvc.perform(post("/food")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(content)
	            .with(csrf())
				.with(user(username).password(password).roles(role))
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		;
		
		verify(foodService,times(1)).addFood(any(Food.class));
	}
	
	@Test
	public void testUpdateFood() throws Exception {
		Food food = new Food("1","Eggs", "Non Veg", 10);
		String content = objectMapper.writeValueAsString(food);
		
		this.mvc.perform(put("/food/1")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(content)
	            .with(csrf())
				.with(user(username).password(password).roles(role))
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		;
		
		verify(foodService,times(1)).updateFood(anyString(),any(Food.class));
		
	}

	
	@Test
	public void testSearchByName() throws Exception {
		String name="Milk";

		this.mvc.perform(get("/food/search/" + name)
				.contentType(MediaType.APPLICATION_JSON)
	            .with(csrf())
				.with(user(username).password(password).roles(role))
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		;
		
		verify(foodService,times(1)).getFoodByName(anyString());
	}

	@Test
	public void testSearchByType() throws Exception {
		
		String type = "Diary";

		this.mvc.perform(put("/food/search/type/" + type)
				.contentType(MediaType.APPLICATION_JSON)
	            .with(csrf())
				.with(user(username).password(password).roles(role))
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		;
		
		verify(foodService,times(1)).getFoodByType(anyString());
	}

	@Test
	public void testSearchByDate() throws Exception {

		this.mvc.perform(put("/food/search/13/01/2019")
				.contentType(MediaType.APPLICATION_JSON)
	            .with(csrf())
				.with(user(username).password(password).roles(role))
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		;
		
		verify(foodService,times(1)).getFoodByDate(anyInt(), anyInt(), anyInt());
	}
	
}
