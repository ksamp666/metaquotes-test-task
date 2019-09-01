package Utils;

import com.codeborne.selenide.WebDriverRunner;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Supplier;

import static Utils.Log.getLogger;
import static com.codeborne.selenide.Selenide.sleep;

public class TestUtils {
    public static boolean waitUntil(Supplier<Boolean> isTrue, long timeoutInMillis, int sleepInMillis) {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (isTrue.get()) {
                return true;
            }
            if (System.currentTimeMillis() - startTime > timeoutInMillis) {
                return false;
            }
            sleep(sleepInMillis);
        }
    }

    public static YearMonth getYearMonthObjectFromPeriod(String str, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
        return YearMonth.parse(str, formatter);
    }

    public static String getCurrentPageLink() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}
