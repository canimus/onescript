import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter{
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

    //Iterating over each suite included in the test
    for (ISuite suite : suites) {
      //Following code gets the suite name
      String suiteName = suite.getName();
      //Getting the results for the said suite
      Map<String,ISuiteResult> suiteResults = suite.getResults();
      for (ISuiteResult sr : suiteResults.values()) {
        ITestContext tc = sr.getTestContext();
        System.out.print("OK: ");
        System.out.print(ANSI_GREEN + tc.getPassedTests().getAllResults().size() + ANSI_RESET );

        System.out.print(", KO: ");
        System.out.print(ANSI_RED + tc.getFailedTests().getAllResults().size() + ANSI_RESET );

        System.out.print(", KK: ");
        System.out.println(ANSI_YELLOW + tc.getSkippedTests().getAllResults().size() + ANSI_RESET );
      }
    }
  }
}
