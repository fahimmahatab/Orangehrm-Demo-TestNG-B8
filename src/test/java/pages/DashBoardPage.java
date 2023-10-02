package pages;

import config.EmployeeModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashBoardPage {
    @FindBy(className = "oxd-main-menu-item--name")
    List<WebElement> menuItems;

    @FindBy(className = "oxd-button")
    List<WebElement> buttons;
    @FindBy(className = "oxd-input")
    List<WebElement> formTextFields;
    @FindBy(className = "oxd-switch-input")
    WebElement btnSwitch;

    public DashBoardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void createUser(EmployeeModel model){
        menuItems.get(1).click(); // click PIM
        buttons.get(2).click(); // click add button
        formTextFields.get(1).sendKeys(model.getFirstName());
        formTextFields.get(3).sendKeys(model.getLastName());
        btnSwitch.click();     // toggle Switch
        formTextFields.get(5).sendKeys(model.userName);
        formTextFields.get(6).sendKeys(model.password);
        formTextFields.get(7).sendKeys(model.password); //Confirm Password
        buttons.get(1).click(); // save data
    }

}
