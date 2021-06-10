package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	static ExtentReports reports;

	public static ExtentReports getReports() {

		if (reports == null) {
			reports = new ExtentReports();
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/");
			sparkReporter.config().setReportName("testing  report");
			sparkReporter.config().setDocumentTitle("Automation Report");
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setEncoding("utf-8");
			reports.attachReporter(sparkReporter);
		}
		return reports;
	}

}
