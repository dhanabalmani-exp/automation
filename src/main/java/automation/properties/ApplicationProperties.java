/**
 * 
 */
package automation.properties;

import java.util.Properties;

public class ApplicationProperties extends Properties {

	private static ApplicationProperties pros;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ApplicationProperties() {
		// TODO Auto-generated constructor stub
	}

	public static ApplicationProperties getInstance() {
		if (pros == null) {
			try {
				pros = new ApplicationProperties();
				pros.load(ApplicationProperties.class.getClassLoader().getResourceAsStream("application.properties"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pros;
	}
}
