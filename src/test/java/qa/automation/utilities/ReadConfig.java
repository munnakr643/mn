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

	public static String getPackageName() {
		String packageName ;
		if (BaseTest.appName.equalsIgnoreCase("neovo")){
			 packageName = pro.getProperty("neovoPackageName");
		}else {
			 packageName = pro.getProperty("sandboxPackageName");
		}
		return packageName;
	}

	public static String getBundleId() {
		String bundleId ;
		if (BaseTest.appName.equalsIgnoreCase("neovo")){
			bundleId = pro.getProperty("neovoBundleId");
		}else {
			bundleId = pro.getProperty("sandboxBundleId");
		}
		return bundleId;
	}

	public String getSafariBundleId() {
		String bundleIdSafari = pro.getProperty("safariBundleId");
		return bundleIdSafari;
	}

	public static String getJiraProjectKey() {
		String projectKey = pro.getProperty("jiraProjectKey");
		return projectKey;
	}

	public String getBaseUrlForAndroid() {
		String baseUrl ;
		if (BaseTest.appName.equalsIgnoreCase("neovo")){
			baseUrl = pro.getProperty("androidNeovoBaseUrl");
		}else {
			baseUrl = pro.getProperty("androidSandboxBaseUrl");
		}
		return baseUrl;
	}

	public String getBaseUrlForIos() {
		String iosBaseUrl ;
		if (BaseTest.appName.equalsIgnoreCase("neovo")){
			iosBaseUrl = pro.getProperty("iosNeovoBaseUrl");
		}else {
			iosBaseUrl = pro.getProperty("iosSandboxBaseUrl");
		}
		return iosBaseUrl;
	}

	public static String getActvityName() {
		String activityName ;
		if (BaseTest.appName.equalsIgnoreCase("neovo")){
			activityName = pro.getProperty("neovoAppActivityName");
		}else {
			activityName = pro.getProperty("sandboxAppActivityName");
		}
		return activityName;
	}
}




