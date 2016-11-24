package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUseCase {
	
	public static Properties getProperties(String filePath) throws IOException{
		Properties properties = new Properties();
		
		InputStream in = new FileInputStream(filePath);		
		properties.load(in);		
		in.close();
		
		return properties;
	}

}
