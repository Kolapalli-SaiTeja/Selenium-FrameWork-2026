package com.helper;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportPathManager {

	private static final String reportPath;

	static {

		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh_mm_ss_dd_MM_yyyy"));

		reportPath = "Reports/chaintest_" + timeStamp + "/Index.html";

		new File(reportPath).getParentFile().mkdir();

		System.setProperty("chaintest.generator.simple.output-file", reportPath);

	}

	public static String getReportPath() {

		return reportPath;

	}

}
