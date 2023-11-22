package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PerformanceTest extends BaseTest {

    @Test()
    public void test01() {
        fullCheckout();
    }

    @Test()
    public void test02() {
        fullCheckout();
    }

    @Test()
    public void test03() {
        fullCheckout();
    }

    @Test()
    public void test04() {
        fullCheckout();
    }

    @Test()
    public void test05() {
        fullCheckout();
    }

    @Test()
    public void test06() {
        fullCheckout();
    }

    @Test()
    public void test07() {
        fullCheckout();
    }

    @Test()
    public void test08() {
        fullCheckout();
    }

    @Test()
    public void test09() {
        fullCheckout();
    }

    @Test()
    public void test10() {
        fullCheckout();
    }

    @Test()
    public void test11() {
        fullCheckout();
    }

    @Test()
    public void test12() {
        fullCheckout();
    }

    @Test()
    public void test13() {
        fullCheckout();
    }

    @Test()
    public void test14() {
        fullCheckout();
    }

    @Test()
    public void test15() {
        fullCheckout();
    }

    @Test()
    public void test16() {
        fullCheckout();
    }

    @Test()
    public void test17() {
        fullCheckout();
    }

    @Test()
    public void test18() {
        fullCheckout();
    }

    @Test()
    public void test19() {
        fullCheckout();
    }

    @Test()
    public void test20() {
        fullCheckout();
    }

    @Test()
    public void test21() {
        fullCheckout();
    }

    @Test()
    public void test22() {
        fullCheckout();
    }

    @Test()
    public void test23() {
        fullCheckout();
    }

    @Test()
    public void test24() {
        fullCheckout();
    }

    private void fullCheckout() {
        try {
            super.beforeClassMain();
            UI.Objects().selectMenu("E-Gift Card");
            UI.Objects().clickAddToCart("CO2LiftProduct don't remove");
            UI.Objects().clickGoToCheckout();
            UI.Objects().successfulGuestCheckout();
            closeBrowser();
        } catch (Exception e) {
            closeBrowser();
            throw e;
        }
    }

}
