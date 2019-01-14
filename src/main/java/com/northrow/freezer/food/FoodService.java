package com.northrow.freezer.food;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>Food Service</h1>
 * <p>This program acts a service class by providing methods for easier updates and fetching information.
 * 
 * @author surramli
 *
 */
@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;

	/**
	 * <h2>addFood</h2>
	 * <p>Method used to addFood to the repository</p>
	 * @param food
	 * @return
	 */
	public String addFood(Food food) {
		Date dt = new Date();
		food.setCreatedDate(dt);
		return foodRepository.save(food).getId();
	}

	/**
	 * <h2>updateFood</h2>
	 * <p>Method used to update existing food in the repository </p>
	 * @param id
	 * @param food
	 */
	public void updateFood(String id, Food food) {
		Food dbFood = getFood(id);
		dbFood.setName(food.getName());
		dbFood.setType(food.getType());
		dbFood.setQuantity(food.getQuantity());
		
		Date dt = new Date();
		dbFood.setUpdatedDate(dt);
		
		foodRepository.save(dbFood);
	}

	/**
	 * <h2>findByName<h2>
	 * Method used to search food by name.
	 * @param name
	 * @return
	 */
	public List<Food> getFoodByName(String name) {
		List<Food> foods = new ArrayList<Food>();		
		foodRepository.findByName(name).forEach(foods::add);
		return foods;
	}
	
	/**
	 * <h2>findByType</h2>
	 * Method used to search food by type.
	 * @param type
	 * @return
	 */

	public List<Food> getFoodByType(String type) {
		List<Food> foods = new ArrayList<Food>();		
		foodRepository.findByType(type).forEach(foods::add);
		return foods;
	}
	
	/**
	 * <h2>findByDate</h2>
	 * Method used to search food by date.
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 * @throws ParseException
	 */

	public List<Food> getFoodByDate(int day, int month, int year) throws ParseException {
		List<Food> foods = new ArrayList<Food>();
		Date dt = new SimpleDateFormat("dd-MM-yyyy").parse("" + day + "-" + month + "-" + year);	
		foodRepository.findByCreatedDate(dt).forEach(foods::add);
		return foods;
	}
	
//	public List<Food> getAllFoods() {
//		List<Food> foods = new ArrayList<Food>();
//		foodRepository.findAll().forEach(foods::add);
//		return foods;
//	}

	/**
	 * <h2>getFood</h2>
	 * Method used to get food information by id.
	 * @param id
	 * @return
	 */
	public Food getFood(String id) {
		return foodRepository.findOne(id);
	}

	/**
	 * Method used to add food details in bulk for testing purposes
	 * @param foods
	 */
	public void addFoodByBatch(List<Food> foods) {
		Date dt = new Date();
		
		for(Food food:foods){
			food.setCreatedDate(dt);
			foodRepository.save(food);
		}
	}
	
}