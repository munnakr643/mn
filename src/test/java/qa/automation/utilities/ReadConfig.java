package qa.automation.utilities;

import qa.automation.base.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	static Properties pro;
	
	public ReadConfig()
	{
		File src = new File("./src/main/resources/global.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	
	public String getUsername()
	{
	String userId=pro.getProperty("userId");
	return userId;
	}
	
	public String getPassword()
	{
	String password=pro.getProperty("password");
	return password;
	}


	public String getFirefoxPath() {
		String firefoxpath = pro.getProperty("firefoxpath");
		return firefoxpath;
	}

	public static String getMobilePlatformName(){
		return System.getenv("platform");
	}

	public static boolean flag(String flagName){
		String env = System.getenv(flagName);
		boolean flagValue = false;
		if (env != null && env.equalsIgnoreCase("true"))
			flagValue = true;
		return flagValue;
	}

	public String getPlatformName() {
		if (ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android"))
			return "Android";
		else
			return "iOS";
	}

	public static boolean isPlatformNameAndroid(){
		return ReadConfig.getMobilePlatformName().equalsIgnoreCase("Android");
	}





	public String getSafariBundleId() {
		String bundleIdSafari = pro.getProperty("safariBundleId");
		return bundleIdSafari;
	}

	public static String getJiraProjectKey() {
		String projectKey = pro.getProperty("jiraProjectKey");
		return projectKey;
	}
}




