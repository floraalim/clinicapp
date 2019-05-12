package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	
	String [][] getData(String xlpath,String sheet) throws IOException
	{
		String path=xlpath;
		
		int rownum=this.getRowCount(path, sheet);
		int colcount=this.getCellCount(path,sheet,1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=this.getCellData(path,sheet, i,j);//1 0
			}
				
		}
	 return logindata;
	}
	public static void writeContent(String fileName, int sheetName, int rowNumber, int cellNumber, String cellValue) throws IOException {
        String projectPath=System.getProperty("user.dir");
        FileInputStream input= new FileInputStream(new File(projectPath+File.separator+fileName)); //Read the spreadsheet that needs to be updated
        HSSFWorkbook wb = new HSSFWorkbook(input); //Access the workbook
        HSSFSheet worksheet = wb.getSheetAt(sheetName); //Access the worksheet, so that we can update / modify it.
        Row row=worksheet.getRow(rowNumber);//Access row
        if (row==null) {
            row = worksheet.createRow(rowNumber);//if row does not exists, create one
        }
        Cell cell = row.getCell(cellNumber);   // Access a cell of a row to update/wriite the value
        if (cell==null) {
            cell = row.createCell(cellNumber);//create a cell if it does not exists
            cell.setCellValue(cellValue);  // Get current cell value value and overwrite the value
        }
        System.out.println(rowNumber + " " + cellNumber + " " + cellValue);
        input.close(); //Close the InputStream
        FileOutputStream output_file =new FileOutputStream(new File(projectPath+ File.separator+fileName));  //Open FileOutputStream to write updates
        wb.write(output_file); //write changes
        output_file.close();  //close the stream
    }	
	
	
	
	
	
}
