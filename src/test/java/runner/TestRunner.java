package runner;

import org.junit.platform.suite.api.*;

/**
 * TestRunner — Entry point eksekusi Cucumber.
 *
 * Cara jalankan:
 *   mvn test                    → semua scenario
 *   mvn test -Dheadless=true    → tanpa browser window (CI/CD)
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
    key   = "cucumber.glue",
    value = "stepdefs,hooks,reporting"
)
@ConfigurationParameter(
    key   = "cucumber.plugin",
    value = "pretty," +
            "html:target/cucumber-reports/cucumber.html," +
            "json:target/cucumber-reports/cucumber.json," +
            "junit:target/cucumber-reports/cucumber.xml"
)
@ConfigurationParameter(key = "cucumber.publish.quiet", value = "true")
public class TestRunner { }
