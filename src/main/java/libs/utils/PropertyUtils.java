package libs.utils;

import java.io.FileReader;
import java.util.Properties;

public class PropertyUtils {

    public static String getProperty(String fileName, String propName) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader("./src/test/resources/" + fileName + ".properties"));
        return prop.getProperty(propName);
    }

}
