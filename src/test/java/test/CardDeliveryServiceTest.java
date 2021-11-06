package test;

import com.codeborne.selenide.Selectors;
import data.DataGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryServiceTest {


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
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("div.notification__content").shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        $("[class='input__control'][placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][placeholder='Дата встречи']").setValue(DataGenerator.generateDate(5));
        $(Selectors.byText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("div.notification__content").shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(5)));


    }
}