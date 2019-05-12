package basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility {
    public void writeToFile(String fileFolder,String fileName,String fileExtension, String fileContent)
    {
        //create the folder if it does not exist
        String workingDirectory=System.getProperty("user.dir"); //return current working folder
        String finalDirectory=workingDirectory+File.separator+fileFolder;
        System.out.println("Final folder: "+finalDirectory);
        File f=new File(finalDirectory);

        if(!f.exists())  //if the folder does not exist
        {
            f.mkdir();   //create the folder
            System.out.println("Folder is created");
        }

        String finalFileName=finalDirectory+File.separator+
                fileName+"_" +System.currentTimeMillis()+fileExtension; //add time stamp to the file name

        File file=new File(finalFileName);
        FileWriter fw= null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw=new BufferedWriter(fw); //create a buffer writer instance
        try {
            bw.write(fileContent); //write the content to the file in the buffer
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); //close the file after writing the content
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}