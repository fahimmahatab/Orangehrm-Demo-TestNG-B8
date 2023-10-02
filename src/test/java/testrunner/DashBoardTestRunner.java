package testrunner;

import config.Setup;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;

public class DashBoardTestRunner extends Setup {
    @BeforeTest
    public void login(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    @Test(priority = 1)
    public void createUser(){
        DashBoardPage dashBoardPage=new DashBoardPage(driver);
        dashBoardPage.createUser("Fahim","Mahatab","fahim123","P@ssword1234");


    }
}
