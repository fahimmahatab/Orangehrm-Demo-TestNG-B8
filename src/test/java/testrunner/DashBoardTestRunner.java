package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashBoardTestRunner extends Setup {
    @BeforeTest
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test(priority = 1)
    public void createUser() throws IOException, ParseException, InterruptedException {
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = faker.name().username();
        String password = faker.internet().password();

        EmployeeModel model=new EmployeeModel();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setUserName(userName);
        model.setPassword(password);

        dashBoardPage.createUser(model);
        String textTitleExpected = driver.findElement(By.xpath("//*[contains(text(),\"Personal Details\")]")).getText();
        Thread.sleep(7000);
        if (textTitleExpected.contains("Personal Details")) {
            Utils.saveEmployeeInfo(model);
        }
    }
}
