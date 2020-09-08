
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	public static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		FileInputStream ip = null;
		ip = new FileInputStream("C:\\Documentum\\eclipse-committers-2018-09-win32-x86_64\\eclipse\\WS\\APIMashup\\src\\config.properties");
		//ip = new FileInputStream("\\config.properties");
		prop.load(ip);
		return prop;

	}

}
