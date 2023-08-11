package web;

import io.qameta.allure.Step;
import model.Vacancy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class UITest {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        loadProperties();
        setupDriver();
    }

    @Step("Загрузить конфигурацилнные файлы")
    private void loadProperties() throws IOException {
        // Читаем конфигурационные файлы в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));

    }

    @Step("Создать экземпляр драйвера")
    private void setupDriver() {
        // Создание экземпляра драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //System.setProperty("webdriver.http.factory", "jdk-http-client");
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        BaseSeleniumPage.setDriver(driver);
    }

    @Test
    public void check() throws InterruptedException {
        //  шаги тест-кейса
        MainPage mainPage = new MainPage();
        //Перейти на страницу https://people.sovcombank.ru/
        driver.get("https://people.sovcombank.ru");
        mainPage.selectButtonVacancies();
        VacanciesPage vacanciesPage = new VacanciesPage();
        vacanciesPage.selectParametrs();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        vacanciesPage.checkCollection(vacanciesPage.getCollection());
    }


    @AfterTest
    public void close() {
        if (driver != null) {
            // Закрываем одно текущее окно браузера
            driver.close();
            // Закрываем все открытые окна браузера, завершаем работу браузера, освобождаем ресурсы
            driver.quit();
        }
    }


}
