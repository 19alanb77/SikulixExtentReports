package sikulixConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class that allows to read data from .xlsx file.
 * 
 * @author Alan Buda
 */ 
public class ExcelUtils {

	private static XSSFSheet ExcelWSheet; 
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	/**
	 * Find values in .xlsx workbook.
	 * 
	 * @param FilePath
	 *          path to the .xlsx file 
	 * @param SheetName
	 *          name of the sheet in the workbook 
	 *          
	 * @return array with values from .xlsx file
	 */ 
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception { 	
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);	 
			int startRow = 1;
			int startCol = 1;
			int ci,cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum() - 1;
			tabArray=new String[totalRows][totalCols];
			ci=0;
	 
			for (int i=startRow;i<=totalRows;i++, ci++) {              
				cj=0;
	    			for (int j=startCol;j<=totalCols;j++, cj++){
	    				tabArray[ci][cj]=getCellData(i,j);
	    				System.out.println(tabArray[ci][cj]);   
	    			}
			}
		} catch (FileNotFoundException e){ 
			System.out.println("Could not read the Excel sheet"); 
			e.printStackTrace(); 
		} catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} 
			return(tabArray);	 
	 }
	 
	/**
	 * Find value of the given cell.
	 * 
	 * @param RowNum
	 *          number of the searched row  
	 * @param ColNum
	 *          number of the searched column
	 *          
	 * @return value of the cell
	 */ 
	 public static String getCellData(int RowNum, int ColNum) throws Exception {
		 try{
			 Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);  
			 String CellData = Cell.getStringCellValue();	 
			 return CellData;	 
			 
		 }catch (Exception e){
			 System.out.println(e.getMessage());
			 throw (e);
		 }
	 } 
	 
	 /**
      * Insert next row with specific data for Test Results file.
  	  * 
	  * @param testCaseName
	  *         number of the executed test case
	  * @param status
	  *         status of the execution
	  * @param error
	  *         error if test failed
	  *          
	  */ 
	  public static void insertRowTestResults(String testCaseName, String status, String error) throws Exception {
	 	 String filename = null;
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date date = new Date();
		 String[] values = {testCaseName,dateFormat.format(date),status,error};
       	 filename = ConfigKeys.getConfigKey("results_file");
	     FileInputStream ExcelFile = new FileInputStream(filename);
	     ExcelWBook = new XSSFWorkbook(ExcelFile);
	     ExcelWSheet = ExcelWBook.getSheetAt(0);
	     ExcelWSheet.createRow(ExcelWSheet.getLastRowNum()+1);
	     Row = ExcelWSheet.getRow(ExcelWSheet.getLastRowNum());
	     for(int col=0;col<4;col++) {
	       	 Row.createCell(col, org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
	       	 Row.getCell(col).setCellValue(values[col]);
	     }
	     FileOutputStream out = new FileOutputStream(filename);
	     ExcelWBook.write(out);
	     out.close();
	  } 
}
