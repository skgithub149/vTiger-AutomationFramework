package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {

	public String readDataFromPropertyFile(String key) throws IOException {
		FileInputStream fisp = new FileInputStream(IConstantsUtility.PROPERTY_FILE_PATH);
		Properties pObj = new Properties();
		pObj.load(fisp);
		String value = pObj.getProperty(key);
		return value;
	}
}
