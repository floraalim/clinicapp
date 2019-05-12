package basic;

import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;

public class mycsv{
	


    public String  getccsvfield(String filename, int index) throws IOException {

            CsvReader testdata = new CsvReader(filename);
            
            String csv=testdata.get(index);
            return  csv;
          
        
          }
            		
       public Map<String,String> getalldata(String filename){ 
    	   
    	   Map<String,String> ab=new HashMap();
    	   try {

               CsvReader testcases = new CsvReader("test.csv");

               testcases.readHeaders();
               //get total numbers of header column
              int numberOfHeaders=testcases.getHeaderCount();
               //print header column
               for(int i=0;i<numberOfHeaders;i++)
               {
                   String key=testcases.getHeader(i);
                   String value=testcases.get(i);
                   ab.put(key, value);
               }

               testcases.close();
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
                         }
    	   return ab;

          }
    	   
      }
       
       