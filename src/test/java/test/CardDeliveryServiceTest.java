package test;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryServiceTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldOrderDeliveryCard() {


        open("http://localhost:9999");
        $("[class='input__control'][placeholder='Город']").setValue(DataGenerator.generateCity("ru"));
        $("[class='input__control'][placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][placeholder='Дата встречи']").setValue(DataGenerator.generateDate(3));
        $("[class='input__control'][name='name']").setValue(DataGenerator.generateName("ru"));
        $("[class='input__control'][name='phone']").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $(Selectors.byText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
        $("div.notification__content").shouldBe(visible,exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        $("[class='input__control'][placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][placeholder='Дата встречи']").setValue(DataGenerator.generateDate(5));
        $(Selectors.byText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("div.notification__content").shouldBe(visible,exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(5)));


    }
}