package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/** Главная страница */
public class MainPage extends BaseSeleniumPage {

    @FindBy(xpath = "//div[@id='main-menu']//div/a/button")
    private WebElement buttonVacancies;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Нажать на кнопку Вакансии")
    public MainPage selectButtonVacancies() throws InterruptedException {
       selectButton();
        return this;
    }

    private void selectButton() {
        buttonVacancies.click();
    }
}
