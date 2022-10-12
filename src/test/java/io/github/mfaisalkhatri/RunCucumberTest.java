package io.github.mfaisalkhatri;

import io.cucumber.junit.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;


@RunWith (Cucumber.class)
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@CucumberOptions(features = { "src/test/resources/features/amazonapp.feature" }, glue = {"io.github.mfaisalkhatri.StepDefinitions"}, tags = "@web")
public class RunCucumberTest {
}
