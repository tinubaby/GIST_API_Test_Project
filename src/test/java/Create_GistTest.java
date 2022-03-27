import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.net.http.HttpClient;
import java.util.HashMap;

import org.apache.http.HttpHeaders;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpProtocolParamBean;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Assert.ThrowingRunnable;
import org.testng.annotations.Test;

import files.Files;
import files.Payload;
import files.PayloadFiles;
import files.PojoFname;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
public class Create_GistTest {
 // a) Test that you can create a new gist containing a file with the contents "PojoFname IRP Student QA!"
  @Test
  public void createGistTest() {
//	  
//	  			HashMap<String, Object> map1=new HashMap<String,Object>();
//	  			HashMap<String, Object> map2=new HashMap<String,Object>();
//	  			HashMap<String, Object> map3=new HashMap<String,Object>();
//	  			map3.put("content", "Contents are here");
//	  			map2.put("PojoFname.txt", map3);
//	  			map1.put("files", map2);
	  			
	  			
    
			    //Specify Base URI
			    RestAssured.baseURI = "https://api.github.com";
			    
			    //Request Object
    			RequestSpecification httpRequest=RestAssured.given();
    			
    			//Request headers
    			httpRequest.header("Content-Type","application/json");
    			//***************UPDATE WITH YOUR BEARER TOKEN****************
    			httpRequest.header("Authorization","BjggggjlT3h0XDuDgBp2ezhfhjjcguTeSpOMi1stt13FLrz");
    			
    			PojoFname u= new PojoFname("");
    			u.setContent("PojoFname Student QA!");
    			Files f= new Files(u);
    			PayloadFiles pf=new PayloadFiles(f);
//    			
    			//Request Body
    			//httpRequest.body(Payload.gistContents()).log().all();
    			//httpRequest.body(new File("./Payload.json"));
    			//httpRequest.body(new File("Payload.json"));
    			httpRequest.body(pf);
    			
    			//Response Object		
    			Response response= httpRequest.request(Method.POST,"/gists");
    			System.out.println(response.getHeader("Content-Type"));
    			
    			
    			System.out.println(response.getBody().path("owner.login"));
    			//print response in console window
    			String responseBody=response.getBody().prettyPrint();
    			//System.out.println("The response Body is"+responseBody);
    			
    			//Status Code validation
    			int statuscode=response.getStatusCode();
    			System.out.println("Status Code: "+statuscode);
    			AssertJUnit.assertEquals(statuscode,201);
    			AssertJUnit.assertEquals(response.getBody().path("files.ubc.content"),"PojoFname IRP Student QA!");
    			
    		    
    
  }
  // b) Test that when creating a new gist the file parameter is in fact required
   @Test
   void mandatoryFileParameterTest() {

	    //Specify Base URI
	    RestAssured.baseURI = "https://api.github.com";
	    
	    //Request Object
		RequestSpecification httpRequest=RestAssured.given();		
		//Request headers
		httpRequest.header("Content-Type","application/json");
		//***************UPDATE WITH YOUR BEARER TOKEN****************
		httpRequest.header("Authorization","Bearer BjggggjlT3h0XDuDgBp2ezhfhjjcguTeSpOMi1stt13FLrz");		
		//Request Body
		httpRequest.body(Payload.emptyContents()).log().all();
		
		
		
		//Response Object		
		Response response= httpRequest.request(Method.POST,"/gists");
		
		
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("The response Body is"+responseBody);
		
		//Status Code validation
		int statuscode=response.getStatusCode();
		System.out.println("Status Code: "+statuscode);
		AssertJUnit.assertEquals(statuscode,422);
	  
		String msg = response.jsonPath().getString("message");
		System.out.println(msg);
		AssertJUnit.assertEquals(msg, "Invalid request.\n"
				+ "\n"
				+ "\"files\" wasn't supplied.");
		
		
  }
   
   //c) Test that the "User Agent" header is required when making a request.
   
@Test
   public void mandatoryUserAgentTest() {
//	Response response=given().header("Authorization","Bearer BjggggjlT3h0XDuDgBp2ezhfhjjcguTeSpOMi1stt13FLrz").body(Payload.gistContents())
//	.when()
//	.post("https://api.github.com/gists");
//	response.prettyPrint();
	

	   
	    //Specify Base URI
	    RestAssured.baseURI = "https://api.github.com";
	    
	    //Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
	
	

		//Request headers
		httpRequest.header("Content-Type","application/json");
		//***************UPDATE WITH YOUR BEARER TOKEN****************
		httpRequest.header("Authorization","Bearer BjggggjlT3h0XDuDgBp2ezhfhjjcguTeSpOMi1stt13FLrz");
		
		
	    //Disable user-agent header
		//CloseableHttpClient client= HttpClientBuilder.create().disableDefaultUserAgent().build();
		try {
			httpRequest.header("user-agent",null);
			
		} catch(IllegalArgumentException ex) {
			AssertJUnit.assertEquals("Header value cannot be null", ex.getMessage());
			System.out.println("User-agent cannot be set to null");
			
		}
		

		//Request Body
		httpRequest.body(Payload.gistContents());
		
		
		
		
   }
@Test
 public void listApi() {
	RestAssured.baseURI="https://api.github.com";
	
	RequestSpecification httpRequest= RestAssured.given();
	httpRequest.header("Authorization","Bearer BjggggjlT3h0XDuDfsdsgBp2ezhfhjjcguTrwweeSpOMi1stt13FLrz");
	
	Response response= httpRequest.request(Method.GET,"/gists");
	//response.getBody().prettyPrint();
	
	
	System.out.println(response.getStatusCode());
	
 }


   
}
