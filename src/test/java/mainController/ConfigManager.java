package mainController;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class ConfigManager {
	public static final String DEFAULT_BROWSER_CONFIG_PARAM = "Chrome";

	private static final Map<String, String> PROPERTIES = readProperties();

	public static String getConfigProperty(String propertyName) {
		return PROPERTIES.get(propertyName);
	}

	private static Map<String, String> readProperties() {
		Map<String, String> configProperties = new HashMap<String, String>();
		Properties properties = loadConfig(Constants.CONFIG_PROPERTIES);
		configProperties.put("browser", properties.getProperty("browser"));
		configProperties.put("baseurl", properties.getProperty("baseurl"));
		configProperties.put("globalTags", properties.getProperty("globalTags"));
		return configProperties;
	}

	private static Properties loadConfig(String resourceName) {
		Properties configProperties = null;
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
		try {
			configProperties = new Properties();
			configProperties.load(in);
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return configProperties;
	}

	public static List<String> getBrowsersToRun() {
		String browsers = ConfigManager.getConfigProperty(Constants.KEY_BROWSER_CONFIG_PARAM);
		if (!StringUtils.isEmpty(browsers)) {
			return Arrays.asList(browsers.split(","));
		} else {
			return Arrays.asList(DEFAULT_BROWSER_CONFIG_PARAM);
		}
	}
	
	public static String getGlobalTags() {
		String globalTags = ConfigManager.getConfigProperty(Constants.KEY_GLOBAL_TAGS);
		if (!StringUtils.isEmpty(globalTags)) {
			return globalTags;
		}
		return null;
	}

	public static String getBaseUrl() {
		return ConfigManager.getConfigProperty(Constants.KEY_BASE_URL);
	}
}
