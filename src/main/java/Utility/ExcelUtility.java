package Utility;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelUtility {
	public String readDataFromExcel(String sheet,int row,int cell) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		return wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		}

}
