package Pages.CalendarPage;

import Interfaces.Block;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;

import static Utils.Log.getLogger;
import static Utils.TestUtils.getYearMonthObjectFromPeriod;

class PeriodSelectorSectionBlock implements Block {
    private SelenideElement mainElement;

    private final static By YEAR_MONTH_TITLE = By.className("date-picker__month-title");
    private final static By MONTH_SWITCH_LEFT_ARROW = By.className("date-picker__prev");
    private final static By MONTH_SWITCH_RIGHT_ARROW = By.className("date-picker__next");

    private final static String DATE_LOCATOR = ".//li[@data-month='%s' and @data-day='%s']";

    PeriodSelectorSectionBlock(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }

    void setDate(LocalDate date) {
        setMonth(YearMonth.of(date.getYear(), date.getMonth()));
        setDate(MonthDay.of(date.getMonth(), date.getDayOfMonth()));
    }

    private void setDate(MonthDay date) {
        getLogger().debug("Settings date:"+date.getDayOfMonth());
        mainElement.find(By.xpath(String.format(DATE_LOCATOR, date.getMonthValue() - 1, date.getDayOfMonth()))).click();
    }

    private void setMonth(YearMonth date) {
        getLogger().debug("Setting year and month: "+date.toString());
        YearMonth currentYearMonth = getYearMonthObjectFromPeriod(mainElement.find(YEAR_MONTH_TITLE).getText(), "LLLL yyyy");
        By arrowToClick = null;
        if (currentYearMonth.compareTo(date) > 0) {
            arrowToClick = MONTH_SWITCH_LEFT_ARROW;
        } else if (currentYearMonth.compareTo(date) < 0) {
            arrowToClick = MONTH_SWITCH_RIGHT_ARROW;
        }

        if (arrowToClick != null) {
            do {
                mainElement.find(arrowToClick).click();
            } while (getYearMonthObjectFromPeriod(mainElement.find(YEAR_MONTH_TITLE).getText(), "LLLL yyyy").compareTo(date) != 0);
        }
    }
}
