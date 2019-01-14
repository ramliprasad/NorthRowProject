package com.northrow.freezer.food;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Food Controller</h1>
 * <p>The Food Controller program is the main rest controller. This file creates all the request 
 * mappings with corresponding URI and HTTP headers.</p>
 * @author Prasad Ramalingam
 * @version 1.0
 * @since 13-01-2019
 *
 */
@RestController
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	/**
	 * This method is used to add new "Food" via POST request.
	 * The uri for this request would be "/food"
	 * @param food A food object is passed.
	 * @return String - Returns the primary key field
	 */
	@RequestMapping(method=RequestMethod.POST,value="/food")
	public String addFood(@RequestBody Food food) {
		return foodService.addFood(food);
	}
	
	/**
	 * This method is used to update the existing food record via "PUT" request.
	 * URI: /food/1, /food/2 ...
	 * @param food - A food object is passed with necessary modification
	 * @param id - Primary key field is passed
	 */
	@RequestMapping(method=RequestMethod.PUT,value="/food/{id}")
	public void updateFood(@RequestBody Food food,@PathVariable String id) {
		foodService.updateFood(id, food); 
	}
	
	/**
	 * This method is used to search for food based on name.
	 * URI: /food/search/milk, /food/search/eggs ...
	 * @param name - A name string is passed
	 * @return List<Food> - It returns a list of foods.
	 */
	@RequestMapping("/food/search/{name}")
	public List<Food> getFoodByName(@PathVariable String name){
		return foodService.getFoodByName(name);
	}

	/**
	 * This method is used to search for food based on type
	 * URI: /food/search/type/Diary, /food/search/type/veg ....
	 * @param type - A type string is passed as parameter
	 * @return List<Food> - It returns a list of foods.
	 */
	@RequestMapping("/food/search/type/{type}")
	public List<Food> getFoodByType(@PathVariable String type){
		return foodService.getFoodByType(type);
	}
	
	/**
	 * This method is used to search for food based on type
	 * URI: /food/search/13/01/2019, /food/search/14/01/2019
	 * @param day - an int is passed with day details
	 * @param month - an int is passed with month details
	 * @param year - an int is passed with year details
	 * @return List<Food> - Returns a list of food items
	 * @throws ParseException
	 */
	@RequestMapping("/food/search/{day}/{month}/{year}")
	public List<Food> getFoodByDate(@PathVariable int day,@PathVariable int month, @PathVariable int year) throws ParseException{
		return foodService.getFoodByDate(day,month,year);
	}

	/**
	 * Convenience method for doing bulk posting of data
	 * URI: /food/batch
	 * @param foods - List of foods.
	 */
	@RequestMapping(method=RequestMethod.POST,value="/food/batch")
	public void addFoodByBatch(@RequestBody List<Food> foods) {
		foodService.addFoodByBatch(foods);
	}

}