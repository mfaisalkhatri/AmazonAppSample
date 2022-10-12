package io.github.mfaisalkhatri;

import io.cucumber.junit.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.runner.RunWith;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;


@IncludeEngines ("cucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@CucumberOptions(features = { "src/test/resources/features" }, glue = {"StepDefinitions"}, plugin = {"pretty"})
@RunWith (Cucumber.class)
public class RunCucumberTest {
}
