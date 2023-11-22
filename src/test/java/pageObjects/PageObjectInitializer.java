package pageObjects;

import configuration.BaseMethod;
import org.openqa.selenium.support.PageFactory;

public class PageObjectInitializer extends BaseMethod {

    public Objects Objects() {
        return PageFactory.initElements(getDriver(), Objects.class);
    }

}
