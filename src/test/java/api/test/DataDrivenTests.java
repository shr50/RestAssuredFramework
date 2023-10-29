package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	
	//if data provider is from same class then we dont need to provide the 
	//location of where data provider class is other we have to provide
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String userID,String username,String fname,String lname,String email,String pwd,String ph) {
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		
		Response response=UserEndPoints.createUser(userPayload); //response import from io.restassured.response.
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUsersByName(String userName) {
		
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
