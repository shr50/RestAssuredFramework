package api.endpoints;
//all the endpoints are maintained here
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//Designing endpoints for User module Pet and Store modules are remaining
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	
	//Similarly we can make Strings for Pet and Store modules

}
