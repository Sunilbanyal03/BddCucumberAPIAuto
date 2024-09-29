package Resources;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification res;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(res==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		res= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		
		return res;
		}
		
		return res;
	}
	
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fil=new FileInputStream("C:\\Users\\lenovo\\eclipse-workspace\\BddApiFramework\\src\\test\\java\\Resources\\global.properties");
		prop.load(fil);
		return prop.getProperty(key);
	}
	
	
	public String getJsonPath(Response response,String key) {
		
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
		
	}

}
