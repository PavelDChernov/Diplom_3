package service.abstractions;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import service.driver.WebDriverCreator;
import service.json.User;
import service.utilities.TestUtilities;

public class AbstractLoginTest {
    protected WebDriver driver;
    protected User user;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        user = TestUtilities.getNewRegisteredUser();
    }

    @After
    public void tearDown() {
        TestUtilities.deleteUser(user);
        user = null;
        driver.quit();
    }
}
