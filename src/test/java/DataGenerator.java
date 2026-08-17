import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byText;

public class DataGenerator {
    public static String data(int days, String pattern){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String prefix(SelenideElement input, SelenideElement popup){
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'а'; c <= 'я'; c++) {
            alphabet.add(c);
        }
        alphabet.add('-');
        List<String> validPrefix = new ArrayList<>();
        for (char first : alphabet) {
            for (char second : alphabet) {
                input.setValue("" + first + second);
                if (popup.is(Condition.visible)){
                    validPrefix.add("" + first + second);
                }
                input.press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            }
        }
        Random random = new Random();
        return validPrefix.get(random.nextInt(validPrefix.size()));
    }
    public static void dataCalendar(int days, SelenideElement calendar){
        if (LocalDate.now().getMonthValue() != LocalDate.now().plusDays(days).getMonthValue()){
            calendar.$("[data-step='1']").click();
        }
        calendar.$(byText(String.valueOf(LocalDate.now().plusDays(days).getDayOfMonth()))).click();
    }
}
