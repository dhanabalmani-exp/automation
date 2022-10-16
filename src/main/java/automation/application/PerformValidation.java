/**
 * 
 */
package automation.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import automation.db.Database;
import automation.file.ExcelReader;
import automation.file.ExcelWriter;
import automation.properties.ApplicationProperties;

public class PerformValidation {

	/**
	 * 
	 */
	public PerformValidation() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//#1. Read Excelsheet data
		final Set<String> benPkgs = ExcelReader
				.readExcel(ApplicationProperties.getInstance().getProperty("source.excel.shared.path"));
		
		//System.out.println(benPkgs);

		//#2. Create object for excel output
		final List<Object[]> excelMemebers = new ArrayList<>();
		excelMemebers.add(new Object[] { "Package Name", "Members" });

		//#3. Retrieve all the package names and find the relevant memeber from database
		for (final String benPkg : benPkgs) {
			
			//#3.1 Retrieve member from database by passing package name
			final Set<String> members = Database.getMember(benPkg);
			
			//#3.2 Load all the member and package for excel output
			for (String member : members) {
				excelMemebers.add(new Object[] { benPkg, member });
			}
		}

		//#4. Write the members list in excel sheet.
		final ExcelWriter writer = new ExcelWriter("Package Members",
				ApplicationProperties.getInstance().getProperty("dest.excel.shared.path"));
		
		writer.writeExcel(excelMemebers);

	}

}
