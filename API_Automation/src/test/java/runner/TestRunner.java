package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

    features = "src/test/resources/Features",
    glue = "stepDefination",
    //plugin = "html:target/reports.html",
    plugin = {"pretty", "html:target/reports.html"},
    monochrome = true
    
		)
public class TestRunner {
}

