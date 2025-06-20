package com.browserStack.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        features = {"src/test/resources/features"},
        glue = {"com.browserStack.stepDefinitions"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
