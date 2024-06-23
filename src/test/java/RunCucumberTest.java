import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("de.haw_hamburg")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "de.haw_hamburg")
public class RunCucumberTest {
}