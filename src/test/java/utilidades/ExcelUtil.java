package utilidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelUtil {
	
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
 
    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            
            int startRow = 1;
            int startCol = 0;
            int ci, cj;
            int totalRows = ExcelWSheet.getLastRowNum();
            int totalCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
            
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <= totalCols - 1; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
 
        } catch (FileNotFoundException e) {
            System.out.println("could not read the Excel sheet");
            e.printStackTrace();
            
        } catch (IOException e) {
            System.out.println("could not read the Excel sheet");
            e.printStackTrace();
            
        }
        return (tabArray);
    }
 
    public static String getCellData(int rowNum, int colNum) throws Exception {
    	
        Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
        String CellData = "";
        
        if (Cell.getCellType() == null) {
            return "";
        } else {
            try {
                CellData = Cell.getStringCellValue();
            } catch (Exception e) {
                CellData = Double.toString(Cell.getNumericCellValue()).split("\\. ")[0];
            }
            return (CellData);
        }
    }
}
