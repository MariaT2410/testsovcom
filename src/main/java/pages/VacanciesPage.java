package pages;

import io.qameta.allure.Step;
import model.Vacancy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VacanciesPage extends BaseSeleniumPage {


    @FindBy(xpath = "//div[@role='combobox']//input[@type='text' and @placeholder='Все города']")
    private WebElement selectCity;

    @FindBy(xpath = "//div[@role='option']/div/p[normalize-space()=\"Москва\"]")
    private WebElement selectCityInList;

    @FindBy(xpath = "//div[@role='button']//div[@class='v-select__selections']")
    private WebElement selectCompany;

    @FindBy(xpath = "//div[@aria-haspopup='true']//div[text()='Совкомбанк']")
    private WebElement selectCompanyInList;


    private List<WebElement> elementsTitle = driver.findElements(By.xpath("//div[@class='page-vacancies']//div[contains(@class, 'full-width')]/div/a/div//h5"));
    private List<WebElement> elementsTags = driver.findElements(By.xpath("//div[@class='page-vacancies']//div[contains(@class, 'full-width')]/div/a/div//p/span"));


    public VacanciesPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Выбрать фильтр")
    public void selectParametrs() throws InterruptedException {
        selectCity();
        selectCityForList();
        selectCompany();
        selectCompanyForList();
    }

    @Step("Получить коллекцию вакансий")
    public List<Vacancy> getCollection(){
        List<Vacancy> newVacancies = new ArrayList<>();
        for (int i=0; i<=elementsTitle.size()-1; i++) {
            Vacancy vacancy = new Vacancy(elementsTitle.get(i).getText(), elementsTags.get(i).getText());
            newVacancies.add(vacancy);
        }
        return newVacancies;
    }

    @Step("Проверить фильтрацию коллекции")
    public void checkCollection(List<Vacancy> list){
        List<Vacancy> listCheck = new ArrayList<>();
        for(Vacancy vacancy:list) {
            System.out.println(vacancy.getTags());
            if ((vacancy.getTags().contains("Москва")) && ((vacancy.getTags().contains("Совкомбанк")))) {
                listCheck.add(vacancy);
            }
        }
        Assert.assertNotEquals(list,listCheck);
    }

    private void selectCity() {
        selectCity.click();
    }

    public void selectCityForList() {
        selectCityInList.click();
    }

    private void selectCompany() {
        selectCompany.click();
    }
    private void selectCompanyForList() {
        selectCompanyInList.click();
    }
}
