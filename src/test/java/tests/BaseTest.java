package tests;

import configuration.BaseMethod;

import org.testng.annotations.AfterClass;
import pageObjects.PageObjectInitializer;

public class BaseTest extends BaseMethod {

    protected PageObjectInitializer UI;

    protected void beforeClassMain() {
        goToHomepage();
        setDriverAndWait(getDriver());
        UI = new PageObjectInitializer();
    }

//    @AfterClass(alwaysRun = true)
//    public void afterClass() {
//        closeBrowser();
//    }

}
