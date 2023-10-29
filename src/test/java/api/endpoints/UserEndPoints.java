package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Just to perform the CRUD operations for User module
public class UserEndPoints {
	
	public static Response createUser(User payload) {
		Response response= given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
		.when()
		       .post(Routes.post_url);
		
		
		return response;
	}
	
	public static Response readUser(String userName) {
		Response response= given()
				.pathParam("username", userName) // pathParam is already contained in get_url 
				
		.when()
		       .get(Routes.get_url);
		
		
		return response;
	}
	
	
	public static Response updateUser(String userName,User payload) {
		Response response= given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName) // pathParam is already contained in update_url
				.body(payload)
				
		.when()
		       .put(Routes.update_url);
		
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response= given()
				.pathParam("username", userName) // pathParam is already contained in get_url 
				
		.when()
		       .delete(Routes.delete_url);
		
		
		return response;
	}

}
