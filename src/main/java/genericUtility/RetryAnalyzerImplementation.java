package genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementation implements IRetryAnalyzer {

	int count = 0, retryCount = 3;

	/**
	 * Retry until retry count is met
	 */
	public boolean retry(ITestResult result) {

		while (count < retryCount) {
			count++;
			return true;
		}
		return false;
	}

}
