package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Just to perform the CRUD operations for User module
public class UserEndPointsUsingPropertyFile {
	
	//method created for getting URLs from properties file
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //loads the property file
		return routes;
	}
	
	
	public static Response createUser(User payload) {
		
		String post_url=getURL().getString("post_url");  //code to fetch the URL from property file
		
		Response response= given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
		.when()
		       .post(post_url);
		
		
		return response;
	}
	
	public static Response readUser(String userName) {
		
		String get_url=getURL().getString("get_url");
		
		
		Response response= given()
				.pathParam("username", userName) // pathParam is already contained in get_url 
				
		.when()
		       .get(get_url);
		
		
		return response;
	}
	
	
	public static Response updateUser(String userName,User payload) {
		
		String update_url=getURL().getString("update_url");
		
		
		Response response= given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName) // pathParam is already contained in update_url
				.body(payload)
				
		.when()
		       .put(update_url);
		
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url=getURL().getString("delete_url");
		
		
		Response response= given()
				.pathParam("username", userName) // pathParam is already contained in get_url 
				
		.when()
		       .delete(delete_url);
		
		
		return response;
	}

}
