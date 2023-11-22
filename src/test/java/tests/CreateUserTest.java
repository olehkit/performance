package tests;

import com.github.javafaker.Faker;
import configuration.HTTPClientSM;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateUserTest {

    Faker faker = new Faker();

    @Test
    public void test01() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test02() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test03() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test04() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test05() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test06() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test07() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test08() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test09() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

    @Test
    public void test10() {
        HTTPClientSM.createCustomer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@guerrillamail.net", faker.name().firstName(), faker.name().lastName());
    }

}
