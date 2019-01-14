The NorthRow Food Freezer Project
----------------------------------

Implementation decisions: 
-------------------------
The project is implemented in Spring Boot. Since this project is a mixture of REST API's, Basic Authentication implementation Spring Boot became an ideal choice. It has its own Rest Controller which manages the request mappings and the service layer takes care of talking to the repository and doing the necessary CRUD operations. 

Testing is being done by JUnit , Mockito.

Future Enhancements
--------------------
Delete - A delete option can be provided in order to delete a food item or multiple food item
SearchBetweenDates - A functionality to search for food items between two created dates can be added.

Authentication - At the moment it has basic authentication. It can be enhanced to have OAUTH based authentication.

Challenges - 
------------
As the data increases we need to look at design and see how best we could access the data. 
Some testing challenges in terms of both unit testing and integration testing 

Convenience Method
-------------------

http://localhost:8080/food/batch METHOD=POST is provided in case you want to add the data onto DB. The json file(data.json) has been placed inside resources directory.

URLs for postman
-----------------
add a Food Item = http://localhost:8080/food  METHOD=POST
update a Food Item = http://localhost:8080/food/{id}  METHOD=PUT
Search By name = http://localhost:8080/food/search/{name} METHOD=GET
Search By type = http://localhost:8080/food/search/type/{type}  METHOD=GET
Search By date = http://localhost:8080/food/search/13/01/2019 METHOD=GET

Awaiting positive response

!Happy Testing!

