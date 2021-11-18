package com.signalpath.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class TestNGRunnerCls {

	@CucumberOptions(tags = "", features = "features", glue = "com.signalpath.stepdefs", monochrome = true)

	public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
		

	}

}
