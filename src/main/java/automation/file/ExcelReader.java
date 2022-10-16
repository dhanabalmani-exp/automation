/**
 * 
 */
package automation.file;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	@SuppressWarnings("resource")
	public static Set<String> readExcel(final String path) {

		final Set<String> set = new HashSet<>();
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(path);
			final XSSFWorkbook wb = new XSSFWorkbook(file);
			final XSSFSheet sh = wb.getSheetAt(0);

			for (int r = 0; r <= sh.getLastRowNum(); r++) {
				set.add(sh.getRow(r).getCell(1).getStringCellValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(file!=null) { try { file.close(); } catch(Exception e) {} }
		}

		return set;
	}

}
