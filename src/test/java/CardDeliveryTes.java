import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTes {
    @Test
    public void shouldHappyPathCardDelivery() {
        open("http://localhost:9999");
        SelenideElement form = $("[method='post']");
        form.$("[data-test-id='city'] .input__control")
                .setValue("Москва");
        form.$("[data-test-id='date'] .input__control")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(DataGenerator.data(3, "dd.MM.yyyy"));
        form.$("[data-test-id='name'] .input__control")
                .setValue("Иван Иванов");
        form.$("[data-test-id='phone'] .input__control")
                .setValue("+79998887766");
        form.$("[data-test-id='agreement']")
                .click();
        form.$(".button__content")
                .click();
        $("[data-test-id='notification']").should(Condition.visible, Duration.ofSeconds(15));
    }
    @Test
    public void shouldHappyPathDynamicElementPopularMenu() {
        open("http://localhost:9999");
        SelenideElement form = $("[method='post']");
        form.$("[data-test-id='city'] .input__control")
                .setValue(DataGenerator.prefix(form.$("[data-test-id='city'] .input__control"), $(".input__popup")));
        $(".input__popup .menu-item").should(Condition.visible).click();
        form.$("[data-test-id='date'] .input__control")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(DataGenerator.data(3, "dd.MM.yyyy"));
        form.$("[data-test-id='name'] .input__control")
                .setValue("Иван Иванов");
        form.$("[data-test-id='phone'] .input__control")
                .setValue("+79998887766");
        form.$("[data-test-id='agreement']")
                .click();
        form.$(".button__content")
                .click();
        $("[data-test-id='notification']").should(Condition.visible, Duration.ofSeconds(15));
    }
    @Test
    public void shouldHappyPathDynamicElementCalendar() {
        open("http://localhost:9999");
        SelenideElement form = $("[method='post']");
        form.$("[data-test-id='city'] .input__control")
                .setValue("Москва");
        form.$("[data-test-id='date'] .input__control")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id='date'] button").click();
        DataGenerator.dataCalendar(7, $(".popup .calendar"));
        form.$("[data-test-id='name'] .input__control")
                .setValue("Иван Иванов");
        form.$("[data-test-id='phone'] .input__control")
                .setValue("+79998887766");
        form.$("[data-test-id='agreement']")
                .click();
        form.$(".button__content")
                .click();
        $("[data-test-id='notification']").should(Condition.visible, Duration.ofSeconds(15));
    }
}
