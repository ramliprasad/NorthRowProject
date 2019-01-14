package com.northrow.freezer.food;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.northrow.freezer.food.Food;
import com.northrow.freezer.food.FoodRepository;
import com.northrow.freezer.food.FoodService;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceTest {

	@Mock
	FoodRepository foodRepository;

	@InjectMocks
	FoodService foodService;

	@Test
	public void getFoodByName_checkName() {
		when(foodRepository.findByName("Milk")).thenReturn(Arrays.asList(new Food("2","Milk","Diary", 12)));
		List <Food> actualList = foodService.getFoodByName("Milk");
		assertEquals("Milk",actualList.get(0).getName());
	}

	@Test
	public void getFoodByType_checkName() {
		when(foodRepository.findByType("Diary")).thenReturn(Arrays.asList(new Food("2","Milk","Diary", 12)));
		List <Food> actualList = foodService.getFoodByType("Diary");
		assertEquals("Diary",actualList.get(0).getType());
	}

	@Test
	public void getFoodByDate_checkName() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date createdDate = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2019");
		when(foodRepository.findByCreatedDate(createdDate)).thenReturn(Arrays.asList(new Food("2","Milk","Diary", 12)));
		
		List <Food> actualList = foodService.getFoodByDate(13,01,2019);
		assertThat(sdf.format(createdDate),equalTo(sdf.format(actualList.get(0).getCreatedDate())));
		
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void getFoodByDate_exception() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date createdDate = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2019");
		when(foodRepository.findByCreatedDate(createdDate)).thenReturn(Arrays.asList(new Food("2","Milk","Diary", 12)));
		
		List <Food> actualList = foodService.getFoodByDate(13,21,2019);
		assertThat(sdf.format(createdDate),equalTo(sdf.format(actualList.get(0).getCreatedDate())));		
	}
	
}
