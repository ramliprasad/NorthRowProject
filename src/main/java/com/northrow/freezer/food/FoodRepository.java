package com.northrow.freezer.food;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 * This class is used to extend the CrudRepository and add additional findby methods.
 * @author Prasad Ramalingam
 * @version 1.0
 *
 */
public interface FoodRepository extends CrudRepository<Food,String>{

	/**
	 * <h2>findByName</h2>
	 * <p>Method used to find food details based on names</p>
	 * @param name
	 * @return
	 */
	   public List<Food> findByName(String name);
	   
	   /**
	    * <h2>findByType</h2>
	    * <p>Method used to find food details based on Type</p>
	    * @param type
	    * @return
	    */
	   public List<Food> findByType(String type);
	   
	   /**
	    * <h2>findByDate</h2>
	    * <p>Method used to find food details based on date</p>
	    * @param createdDate
	    * @return
	    */
	   public List<Food> findByCreatedDate(Date createdDate);

}
