package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashBoardTestRunner extends Setup {
    DashBoardPage dashboardPage;
    @BeforeTest(groups = "smoke")
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        dashboardPage = new DashBoardPage(driver);
        dashboardPage.menuItems.get(1).click(); // click PIM
    }

    @Test(priority = 1,groups = "smoke", description = "Check if Search button is working")
    public void clickonSearchButton(){

        driver.findElement(By.cssSelector("[type='submit']")).click();
    }
    @Test(priority = 2,groups = "smoke", description = "Check if Reset button is working")
    public void clickonResetButton (){
        driver.findElement(By.cssSelector("[type=reset]")).click();
    }

    @Test(priority = 3, description = "Check if New User is created successfully")
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
        Allure.description("User created successfully");
    }
}
