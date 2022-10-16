package automation.file;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	private String sheetName;
	private String filePath;

	public ExcelWriter(final String sheetName, final String filePath) {
		this.sheetName = sheetName;
		this.filePath = filePath;
	}

	@SuppressWarnings("resource")
	public void writeExcel(List<Object[]> list) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(this.sheetName);

		int rownum = 0;
		for (Object[] objArr : list) {
			
			final Row row = sheet.createRow(rownum++);
			int cellnum = 0;
			
			for (Object obj : objArr) {

				final Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		
		FileOutputStream out = null;
		try
        {
            //Write the workbook in file system
            out = new FileOutputStream(new File(filePath));
            workbook.write(out);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		finally {
			if(out!=null) { try { out.close(); } catch(Exception e) {} }
		}
	}

}
