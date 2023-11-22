package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Objects extends PageObjectInitializer {

    @FindBy(xpath = "//button[@id='product-addtocart-button']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//button[@title='Go to Checkout' and @data-role='proceed-to-checkout']")
    private WebElement gotToCheckoutBtn;

    @FindBy(xpath = "//div[@class='control _with-tooltip']//input[@id='customer-email']")
    private WebElement customerEmailInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='firstname']")
    private WebElement customerFirstNameInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='lastname']")
    private WebElement customerLastNameInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='street[0]']")
    private WebElement customerFirstStreetAddressInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='city']")
    private WebElement customerCityInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//select[@name='country_id']")
    private WebElement customerCountrySelect;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//select[@name='region_id']")
    private WebElement customerStateSelect;

    @FindBy(xpath = "//select[@name='suorder_comment']/ancestor::div[@data-bind='visible: getStatus()']")
    private WebElement providerDiv;

    @FindBy(xpath = "//select[@name='suorder_comment']")
    private WebElement providerSelect;

    @FindBy(xpath = "//input[@id='cc_split']")
    private WebElement ccSplitRadioButton;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='postcode']")
    private WebElement customerZipCodeInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='telephone']")
    private WebElement customerPhoneInput;

    @FindBy(xpath = "//input[@id='cc_split_cc_number']")
    private WebElement ccSplitCreditCardInput;

    @FindBy(xpath = "//select[@id='cc_split_expiration']")
    private WebElement ccSplitExpirationMonthSelect;

    @FindBy(xpath = "//select[@id='cc_split_expiration_yr']")
    private WebElement ccSplitExpirationYearSelect;

    @FindBy(xpath = "//input[@id='cc_split_cc_cid']")
    private WebElement ccSplitCvvCodeInput;

    @FindBy(xpath = "//div[@class='checkout-agreement required'][1]//input")
    private WebElement agreementCheckbox;

    @FindBy(xpath = "//div[@class='payment-methods']//button[contains(@title,'Place Order')]")
    private WebElement placeOrderButton;

    public void verifySuccessfulCheckout() {
        customWait(90).until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[@class='action primary continue']//span[text()='Continue Shopping']"))));
    }

    public void clickPlaceOrder() {
        click(placeOrderButton);
    }

    public void clickTermsConditionsCheckbox() {
        click(agreementCheckbox);
    }

    public void enterCCSplitCardVerificationNumber(String verificationNumber) {
        sendText(ccSplitCvvCodeInput, verificationNumber);
    }

    public void selectCCSplitExpirationYear(String expirationYear) {
        scrollIntoView(ccSplitExpirationYearSelect);
        Select year = new Select(ccSplitExpirationYearSelect);
        year.selectByVisibleText(expirationYear);
    }

    public void selectCCSplitExpirationMonth(String expirationMonth) {
        scrollIntoView(ccSplitExpirationMonthSelect);
        Select month = new Select(ccSplitExpirationMonthSelect);
        month.selectByVisibleText(expirationMonth);
    }

    public void enterCCSplitCreditCardNumber(String creditCardNumber) {
        sendText(ccSplitCreditCardInput, creditCardNumber);
    }

    public void enterCustomerPhone(String customerPhone) {
        sendText(customerPhoneInput, customerPhone);
    }

    public void enterCustomerZipCode(String customerZipCode) {
        sendText(customerZipCodeInput, customerZipCode);
    }

    public void selectCustomerCountry(String customerCountry) {
        scrollIntoView(customerCountrySelect);
        Select country = new Select(getDriver().findElement(By.xpath("//div[@id='shipping-new-address-form']//select[@name='country_id']")));
        country.selectByVisibleText(customerCountry);
    }

    public void selectCustomerState(String customerState) {
        scrollIntoView(customerStateSelect);
        Select state = new Select(getDriver().findElement(By.xpath("//div[@id='shipping-new-address-form']//select[@name='region_id']")));
        state.selectByVisibleText(customerState);
    }

    public void selectSurveyProviderByIndex() {
        if (providerDiv.getAttribute("style").isEmpty()) {
            scrollIntoView(providerSelect);
            Select surveyProvider = new Select(providerSelect);
            surveyProvider.selectByIndex(1);
        }
    }

    public void clickCCSplitRadioButton() {
        if (ccSplitRadioButton.isDisplayed()) {
            click(ccSplitRadioButton);
        }
    }

    public void clickAddToCart() {
        click(addToCartBtn);
    }

    public void clickGoToCheckout() {
        sleep(1000);
        click(gotToCheckoutBtn);
    }

    public void enterCustomerEmail(String customerEmail) {
        sendText(customerEmailInput, customerEmail);
    }

    public void enterCustomerFirstName(String customerFirstName) {
        sendText(customerFirstNameInput, customerFirstName);
    }

    public void enterCustomerLastName(String customerLastName) {
        sendText(customerLastNameInput, customerLastName);
    }

    public void enterCustomerFirstAddress(String customerFirstAddress) {
        sendText(customerFirstStreetAddressInput, customerFirstAddress);
    }

    public void selectMenu(String item) {
        List<String> menuLevels = Arrays.asList(item.split("/"));
        for (int i = 0; i < menuLevels.size(); i++) {
            Actions action = new Actions(getDriver());
            WebElement menuLevel = null;
            if (i == 0) {
                menuLevel = getDriver().findElement(By.xpath(String.format("//div[@class='inner-container']//nav[@id='mainmenu']//a[contains(@class,'level-top')]//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')]", menuLevels.get(i).toLowerCase().trim())));
            } else {
                menuLevel = getDriver().findElement(By.xpath(String.format("//div[@id='header-container']//*[contains(@style,'display: block;')]//li[contains(@class,'level%s')]//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'%s')]", i, menuLevels.get(i).toLowerCase().trim())));
            }
            wait.until(ExpectedConditions.elementToBeClickable(menuLevel));
            action.moveToElement(menuLevel).perform();
            wait.until(ExpectedConditions.elementToBeClickable(menuLevel));
            if (i == (menuLevels.size() - 1))
                menuLevel.click();
        }
    }

    public void clickAddToCart(String name) {
        WebElement addToCard = getDriver().findElement(By.xpath(String.format("//div[contains(@class,'product-item-info imgdim-x')][.//a[@title=\"%s\"]]//button[@title='Add to Cart']", name)));
        jsClick(addToCard);
    }

    public void enterCustomerCity(String customerCity) {
        sendText(customerCityInput, customerCity);
    }

    public void successfulGuestCheckout() {
        Faker faker = new Faker();
        sleep(5000);
        customWait(60).until(ExpectedConditions.visibilityOf(customerEmailInput));
        enterCustomerEmail(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net");
        enterCustomerFirstName(faker.name().firstName());
        enterCustomerLastName(faker.name().lastName());
        enterCustomerFirstAddress(faker.address().fullAddress());
        enterCustomerCity(faker.address().city());
        selectCustomerCountry("United States");
        selectCustomerState("Alaska");
        sleep(200);
        selectSurveyProviderByIndex();
        clickCCSplitRadioButton();
        enterCustomerZipCode("00001");
        enterCustomerPhone("150" + faker.number().numberBetween(1000000, 9999999));
        enterCCSplitCreditCardNumber("4111111111111111");
        selectCCSplitExpirationMonth("05 - May");
        selectCCSplitExpirationYear("2028");
        enterCCSplitCardVerificationNumber("111");
        clickTermsConditionsCheckbox();
        sleep(200);
        clickPlaceOrder();
        verifySuccessfulCheckout();
    }

}
