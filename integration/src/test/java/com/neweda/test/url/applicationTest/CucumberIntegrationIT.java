package com.neweda.test.url.applicationTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * the main class for cucumber where you configure where the features are defined and which formats of reports needed to be generated
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = { "integration/src/test/resources/features/" }, plugin = { "pretty", "html:target/reports/cucumber/html",
                "json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml" })
public class CucumberIntegrationIT {
}
