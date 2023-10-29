package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	
	Faker faker;
	User userPayload;  //defining above method because later it will be passed as parameter
	
	@BeforeClass
	public void setupDate() {
		
		faker= new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		Response response=UserEndPoints.createUser(userPayload); //response import from io.restassured.response.
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		Response response=UserEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateByUsername() {
		
		
		//updating few fields not all for put request
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().body();

        //checking response after update
		Response responseAfterUpdate= UserEndPoints.readUser(userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		
	}

}
