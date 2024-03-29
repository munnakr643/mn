package qa.automation.execution;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.List;

/**
 * @deprecated
 */
public class Application {

	private final static Logger logger = LogManager.getLogger(Application.class);

	/**
	 * This will initiate test execution based on properties of device.yml &
	 * Testng.xml
	 * 
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		List<XmlSuite> suites;
		String xmlSuiteFile = null;
		try {
			if (System.getenv("suitename") != null){
				if(System.getenv("suitename").equalsIgnoreCase("regressionSuiteAndroid")){
					xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/regressionSuiteAndroid.xml");
				} else if (System.getenv("suitename").equalsIgnoreCase("regressionSuiteiOS")){
					xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/regressionSuiteiOS.xml");
				} else if (System.getenv("suitename").equalsIgnoreCase("regressioniOS1")) {
					xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/regressioniOS1.xml");
				} else if (System.getenv("suitename").equalsIgnoreCase("regressioniOS2")) {
					xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/regressioniOS2.xml");
				} else if (System.getenv("suitename").equalsIgnoreCase("regressioniOS3")) {
					xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/regressioniOS3.xml");
				} else {
					logger.info("Running default Testng.xml.");
					xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/Testng.xml");
				}
			} else {
				logger.info("suitename not provided. Running default Testng.xml.");
				xmlSuiteFile = System.getProperty("xmlSuiteFile", "test-suites/Testng.xml");
			}

			if (StringUtils.isNotEmpty(xmlSuiteFile)) {
				suites = new Parser(xmlSuiteFile).parseToList();
				TestNG testNGRun = new TestNG();
				testNGRun.setXmlSuites(suites);
				testNGRun.run();
			} else {
				logger.error("Error starting the application. testNG.xml file is not supplied. Stopping the app.");
			}

		} catch (Exception e) {
			logger.error("Error starting the application.please check package/bundleId,udid,device name and appium server,", e);
		}
	}
}
