package basic;

import javax.json.*;
import java.io.*;
public class ReadSimplejson {
    
    public static  JsonValue ReadJson(String filename,String name) throws IOException {

        //define Input Stream for the Json file
        InputStream inputStream=null;
        //define Json Reader
        JsonReader jsonReader=null;
        //define Json Object
        JsonObject jsonObject=null;
        try {
            inputStream = new FileInputStream(filename);
            jsonReader=Json.createReader(inputStream);
            jsonObject = jsonReader.readObject();
          

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    {
            inputStream.close();
            jsonReader.close();
        }
    return jsonObject.get(name);
      }
    
    public static  JsonValue  Readjsonarray(String filename,  String arrayname,Integer  index,String key) throws IOException{
    	
    	//define Input Stream for the Json file
        InputStream inputStream=null;
        //define Json Reader
        JsonReader jsonReader=null;
        //define Json Object
        JsonObject jsonObject=null;
        JsonValue mystep=null;
        try {
            inputStream = new FileInputStream(filename);
            jsonReader=Json.createReader(inputStream);
            jsonObject = jsonReader.readObject();
           ;
         JsonArray testSteps=jsonObject.getJsonArray(arrayname);
           
           mystep=testSteps.getJsonObject(index).get(key);
                
            }

         catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      finally {
            inputStream.close();
            jsonReader.close();
   
    }
    return mystep;
   }
    
    
    
    
    
}
