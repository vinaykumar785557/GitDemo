package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;

	private final int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (count < maxTry) {
			count++;
			return true;
		}

		return false;
	}

}
