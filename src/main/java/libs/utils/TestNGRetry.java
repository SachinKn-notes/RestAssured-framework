package libs.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class will be Used for Retrying the failed TestCase Note - In
 * ExtentReport, All the Runs would be printed in same tab(i.e.
 * MethodName-BrowserName-APIName).
 *
 * How To Use -
 *
 * @Test(retryAnalyzer = TestNGRetry.Class)
 */
public class TestNGRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (retryCount < maxRetryCount && result.getThrowable().getClass() != AssertionError.class) {
                retryCount++;
                result.setStatus(ITestResult.FAILURE);
                return true;
            }
            result.setStatus(ITestResult.FAILURE);
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}

