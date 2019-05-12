package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.apache.http.util.EntityUtils;


public class restapi {
	 final String USER_AGENT="Mozilla/5.0";
	 int code=0;
	
	
 public Boolean isgoodstatus(String url) throws ClientProtocolException, IOException {
	

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
	 
	   return response.getStatusLine().getStatusCode()==200?true:false;
	    
 }
 public String getbody(String url) throws ClientProtocolException, IOException {
	 
	 HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		
           return result.toString();
          }
 
public JSONObject getbodyasjson(String url) throws ClientProtocolException, IOException, ParseException {
	  JSONObject jso=null;
	 String s=this.getbody(url);
	 JSONObject jso1=new JSONObject(s);
	 
	  return   jso1;
	  
	      
          } 
  public String getdatavalue(String url, String key ) throws JSONException, ClientProtocolException, IOException, ParseException {

         return  this.getbodyasjson(url).getString(key);
      

        }
  
       public  boolean iscontainthisstring(String url, String actual) throws  Exception {
    	       String s=this.getbody(url);
    	   
    	        boolean bl=s.contains(actual);
			
    	        return bl;
   	   
                   }
       //post json data to url and get response content
       public String postdata(String url,String json) throws ClientProtocolException, IOException {
    	   HttpClient client = HttpClientBuilder.create().build();
    	   HttpPost httpPost = new HttpPost(url);
    	   HttpEntity stringEntity = new StringEntity(json,ContentType.APPLICATION_JSON);
    	    httpPost.setEntity(stringEntity);
    	   HttpResponse response = client.execute(httpPost);
    	    
    	   BufferedReader rd = new BufferedReader(
    		        new InputStreamReader(response.getEntity().getContent()));

    		StringBuffer result = new StringBuffer();
    		String line = "";
    		while ((line = rd.readLine()) != null) {
    			result.append(line);
    		}
    		System.out.println(response.getStatusLine().getStatusCode());
    		
      	  return result.toString();
      	 
    	    
       }
       
  public String  putdata(String url,String putdata) throws IOException{  
	  String responseBody =null;
	  
	  try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
          HttpPut httpPut = new HttpPut(url);
          httpPut.setEntity(new StringEntity(putdata));

         

          // Create a custom response handler
          ResponseHandler<String> responseHandler = response -> {
              int status = response.getStatusLine().getStatusCode();
               this.code=status;
              if (status >= 200 && status < 300) {
                  HttpEntity entity = response.getEntity();
                  
                  return entity != null ? EntityUtils.toString(entity) : null;
              } else {
                  throw new ClientProtocolException("Unexpected response status: " + status);
              }
          };
          responseBody = httpclient.execute(httpPut, responseHandler);
          System.out.println("----------------------------------------");
          System.out.println(responseBody);
      }
	  return  responseBody;
      }
  
  public void print(String i) {
	  
	  System.out.println(i);
  }
  public  int  deletedata(String url) throws IOException {
	   
      try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
          HttpDelete httpDelete = new HttpDelete(url);

          System.out.println("Executing request " + httpDelete.getRequestLine());

          // Create a custom response handler
          ResponseHandler<String> responseHandler = response -> {
              int status = response.getStatusLine().getStatusCode();
              this.code=status;
              System.out.println(status);
              if (status >= 200 && status < 300) {
                  HttpEntity entity = response.getEntity();
                  return entity != null ? EntityUtils.toString(entity) : null;
              } else {
                  throw new ClientProtocolException("Unexpected response status: " + status);
              }
          };
          
          String responseBody = httpclient.execute(httpDelete, responseHandler);
          System.out.println("--------------test begin--------------------------");
          System.out.println(responseBody);
          
      }
      return this.code;
    }
  
  
 public void gettest( ) throws Throwable {
	   
	
	  String s=this.getbody("https://reqres.in/api/users/2").trim();
	    
	  this.print(s);
	 String title =this.getdatavalue("https://jsonplaceholder.typicode.com/todos/1", "title");
	 this.print(title);
	boolean s1=  this.iscontainthisstring("https://jsonplaceholder.typicode.com/todos/1", "delectus aut autem");
	
	this.print(Boolean.toString(s1));
	
	
	
   }
 @Test 
 public void mytest() throws Throwable  {
	  String s3="{\"name\":\"morpheus\",\"job\":\"leader\"}";  
	  String putdata="{\"name\":\"morpheus\",\"job\":\"zion resident\"}"; 
	 String s= this.postdata("https://reqres.in/api/users", s3);
      this.print(s);
    int t=  this.deletedata("https://reqres.in/api/users/2");
      this.putdata("https://reqres.in/api/users/2",putdata );
 }
       
}