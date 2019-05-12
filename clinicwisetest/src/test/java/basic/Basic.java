package basic;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.io.File;
import cucumber.api.DataTable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.yaml.snakeyaml.Yaml;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.jayway.restassured.builder.RequestSpecBuilder;
//import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Basic {
	WebDriver driver = null;
	String proppath;
	public static Logger logger;
			
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	

	public Basic(WebDriver driver) {
		super(); 
		this.driver = driver;
	}


	public String getProppath() {
		return proppath;
	}
	public void setProppath(String proppath) {
		this.proppath = proppath;
	}


	
	public Properties prop;
	
	
	public void embedScreenshot(Scenario scenario) {
		  try {
		    if (!scenario.isFailed()) {
		      // Take a screenshot only in the failure case
		      return;
		    }

		    String webDriverType = System.getProperty("WebDriverType");
		    if (!webDriverType.equals("HtmlUnit")) {
		      // HtmlUnit does not support screenshots
		      byte[] screenshot = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.BYTES);
		      scenario.embed(screenshot, "image/png");
		    }
		  } catch (WebDriverException somePlatformsDontSupportScreenshots) {
		    scenario.write(somePlatformsDontSupportScreenshots.getMessage());
		  }
		}
	public void init() {
		this.driver=new ChromeDriver();
       this. driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
	    }
	
	  public void  screenshot(String pt) throws IOException {
		
		File src= ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
		
		 // now copy the  screenshot to desired location using copyFile //method
		   FileUtils.copyFile(src, new File(pt));
		   
		 
	
	   }
	  
	  
	  public void run(String cmd) throws IOException {
		  Runtime run = Runtime.getRuntime();
		  Process pr = run.exec(cmd);
		  
	  }
	  
	  public String getprop(String proppath,String name) throws IOException {
		  prop= new Properties();
		  FileInputStream fis=new FileInputStream(proppath);

		  prop.load(fis);
		  String myname=prop.getProperty(name);
		  
		  
		  return myname;
	  }
	  
	  public Logger getlogger(String name) {
		  
	 this.logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		return this.logger;
		 
	  }
	  
	  public String tojson(Object ob) {
		  GsonBuilder builder = new GsonBuilder();
	       Gson gson = builder.create();
	      return  gson.toJson(ob);
		  
	  }
	  
	  public Object toclass(String json,Object ob) {
		  GsonBuilder builder = new GsonBuilder();
	       Gson gson = builder.create();
	      return (Object) gson.fromJson(json, ob.getClass());
	      
	    
	  }
	  public String getcucumberdata(int row, int col) {
		  DataTable table=null;
		  return  table.raw().get(row).get(col);
		 
		  
	  }
	  public void print(String s) {
		  
		  System.out.println(s);
	  }
	  public String getjsondata(URL url, String name) throws JSONException {
		  
		  //API Body
		  String apiBody = "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}";
		  
		  // Building request by using requestSpecBuilder
		  RequestSpecBuilder builder = new    RequestSpecBuilder();
		  
		  //Set API's Body
		  builder.setBody(apiBody);
		  
		  //Setting content type as application/json
		  builder.setContentType("application/json; charset=UTF-8");
		  
		  RequestSpecification requestSpec = builder.build();
		  
		  //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
		  Response response = requestSpec.get(url);
		  
		 
		  JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		  
		  //Get the desired value of a parameter
		  String result = JSONResponseBody.getString(name);
		  
		 return result;
		
	  }
	  public void postdata(String json,URL url) {
  
		  
		  // Building request by using requestSpecBuilder
		  RequestSpecBuilder builder = new    RequestSpecBuilder();
		  
		  //Set API's Body
		  builder.setBody(json);
		  
		  //Setting content type as application/json
		  builder.setContentType("application/json; charset=UTF-8");
		  
		  RequestSpecification requestSpec = builder.build();
		  
		  //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
		  Response response = requestSpec.post(url);
		  
		 
		  
	  }
	  
	  public Map<String, Object> readyamlfile(String yml){
		  
		  Yaml yaml = new Yaml();
		  InputStream inputStream = this.getClass()
		    .getClassLoader()
		    .getResourceAsStream(yml);
		  Map<String, Object> obj = yaml.load(inputStream); 
		  return obj;
		  
	  }
	
	  public String getyml(String file,String key) {
		  Map<String, Object> obj=this.readyamlfile(file);
		  String s=obj.get(key).toString();
		  return s;
	     }
		  public String[] readpdffile(String path) {
			   String lines[]=null;
			  
			  try (PDDocument document = PDDocument.load(new File(path))) {

		            document.getClass();

		            if (!document.isEncrypted()) {
					
		                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		                stripper.setSortByPosition(true);

		                PDFTextStripper tStripper = new PDFTextStripper();

		                String pdfFileInText = tStripper.getText(document);
		                
		                lines= pdfFileInText.split("\\r?\\n");
		                

		            }

		        } catch(Exception e) {
			  
			     e.printStackTrace();
		     }
		     return lines;
          }
		  
 public String readhtml(String file,String tag) {

	 Document htmlFile = null;
	 
	 try { htmlFile = Jsoup.parse(new File(file), "ISO-8859-1"); } 
	    catch (IOException e) 
	   { 
		 e.printStackTrace(); } 
	 
	     

     return  htmlFile.body().getElementsByTag(tag).text();
	 
      }

 public String readurltagvalue(String url,String tag) {

	 Document htmlFile = null;
	 
	 try { htmlFile = Jsoup.connect(url).get(); } 
	    catch (IOException e) 
	   { 
		 e.printStackTrace(); } 
	 
	     

     return  htmlFile.body().getElementsByTag(tag).text();
	 
      }
 
    public WebElement getelement(String type,String value) {
    	   WebElement  el=null;
 
            switch (type) {
            	
            case "name":
            el=this.driver.findElement(By.name(value))	;
               break;
            case "id":
               el=this.driver.findElement(By.id(value))	;
                break;
            case "xpath":
                el=this.driver.findElement(By.xpath(value))	;
                 break;
            case "css":
                el=this.driver.findElement(By.cssSelector(value))	;
                 break;
            case "linktext":
                el=this.driver.findElement(By.linkText(value))	;
                 break;
            case "tagname":
                el=this.driver.findElement(By.tagName(value))	;
                 break;
            default:
            	
            el=this.driver.findElement(By.id(value))	;
             break;    
          	
          		
               }
            return el;
          }
    
    public WebElement getelementbyid(String value) {
    	
    	return  this.driver.findElement(By.id(value));
    	
    }
    
public WebElement getelementbyname(String value) {
    	
    	return  this.driver.findElement(By.name(value))	;
    	
    }
    
public WebElement getelementbytagname(String value) {
	
	return  this.driver.findElement(By.tagName(value))	;
	
}

public WebElement getelementbycss(String value) {
	
	return  this.driver.findElement(By.cssSelector(value));
	
}
public WebElement getelementbylinktext(String value) {
	
	return  this.driver.findElement(By.linkText(value));
	
}
 public WebElement getlementbyxpath(String value) {
	 
	return this.driver.findElement(By.xpath(value))	;
 }
    

    
    
    public Select getdropdown(String type,String value) {
       Select se=new Select(this.getelement(type, value));
         
       return se;
        }
    
     public void selecttextbox(String type,String value,String sel ) {
    	  this.getdropdown(type, value).selectByVisibleText(sel);
     }
      public String readjsonstring(String json,String key) throws JSONException {

    	   String s=null;
    	   JSONObject obj = new JSONObject(json);
    	   s = obj.getString(key);
    	  
    	 
             return s;
          }
      
      
      public String readjsonstring(String json,String parent,String child) throws JSONException {

   	   String s=null;
   	   JSONObject obj = new JSONObject(json);
   	    s = obj.getJSONObject(parent).getString(child);
   	  
   	 
            return s;
         }

      public String readjsonsarray(String json,String arrayname,int index,String name) throws JSONException {

      	   String s=null;
      	   JSONObject obj = new JSONObject(json);
      	    s = obj.getJSONArray(arrayname).getJSONObject(index).getString(name);
      	  
      	 
               return s;
            }
      
      
     public void Wait(By by, long timeout)
      {
          WebDriverWait wait = new WebDriverWait(this.driver, timeout); 
          wait.until((ExpectedConditions.visibilityOfElementLocated(by)));
      }  
      
  public void moveToFrame(){
          WebElement iframeElement=driver.findElement(By.tagName("iframe")); 
          driver.switchTo().frame(iframeElement); 
      }  
  
      public Actions getaction() {
    	  
    	  Actions actions1=new Actions(this.driver);
    	  return  actions1;
      }

   public void draganddrop(WebElement source,WebElement target) {
	   
	   Actions actions=new Actions(this.driver);
	   actions.dragAndDrop(source,target); 
         Action action=actions.build(); 
         action.perform(); 
	   
   }
public void dragtopoint(WebElement source,Point p) {
	   
	   Actions actions=new Actions(this.driver);
	   actions.dragAndDropBy(source,p.x,p.y); 
         Action action=actions.build(); 
         action.perform(); 
	   
   }
   
public void contentclick() {
	  
	   Actions actions=new Actions(this.driver);
	   actions.contextClick().build().perform();
     
  }
public void dblclick(WebElement we) {
	  
	   Actions actions=new Actions(this.driver);
	   actions.doubleClick(we).build().perform();
  
     }

public void dblclick() {
	  
	   Actions actions=new Actions(this.driver);
	   actions.doubleClick( ).build().perform();

  }
       public  Point getlocation(WebElement el) {
    	   return el.getLocation();
       }
       
     public  boolean comparePos(Point p1, Point p2){
           boolean res=false;
           if(p1.x != p2.x || p1.y != p2.y)
                  res=true;
           return res;
       }

       public void navigatetosite(String url){
          this.driver.get(url);
       }   
       
       public void clickandhold(WebElement el) {
    	   Actions actions=new Actions(driver); 
           actions.clickAndHold(el).release().build().perform();
       }
       
       public void waitForElementVisible(WebElement webElement, int timeOutSeconds){
           WebDriverWait wait=new WebDriverWait(driver,timeOutSeconds,100);
           wait.until(ExpectedConditions.visibilityOf(webElement));
       }   
       public void  waitForAjaxIsCompleted() {
           StopWatch stopWatch = new StopWatch();
           stopWatch.start();
           while (stopWatch.getTime() / 1000 < 60) {
               final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
               boolean isAjaxActive = (Boolean) javascriptExecutor.executeScript("return window.jQuery!=underfined && jQuery.active==0");
               if (isAjaxActive)
                   break;
               else {
                   try {
                       Thread.sleep(2000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }     
       
    
      }

       public void waittime(int time) {
    	   
           this.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);   
            	   
               }
               
         public Alert alert() {
            	   
            	  return  this.driver.switchTo().alert();
           }

   public void execscript(String script) {
	   final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        javascriptExecutor.executeScript(script);
   
   }
   
   

}
 

	  



	  
	  

