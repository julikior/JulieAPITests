package authorization;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Authorization {

    public static String getPropertyByKey(String key) {
        String value = null;
        String propertyPath = Paths.get(System.getProperty("user.dir"),
                "src", "test", "java", "configs.properties").normalize().toString();

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(propertyPath));
            value = properties.getProperty(key);
        }
        catch ( IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public static Object getValueOfJsonByKey(Response response, String keyPath) {
        return new JsonPath(response.asString()).get(keyPath);
    }

}
