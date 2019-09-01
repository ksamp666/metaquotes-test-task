package Pages.CalendarPage;

import Interfaces.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static Utils.Log.getLogger;
import static Utils.TestUtils.waitUntil;
import static com.codeborne.selenide.Selenide.*;

public class CalendarPage implements Page {
    private final static String PAGE_URL = "https://www.mql5.com/en/economic-calendar";

    private final static By VERTICAL_COOKIES_PANEL_CROSS = By.className("float-vertical-panel__cross");
    private final static By PERIOD_SELECTOR = By.className("economic-calendar__filter-date-input");
    private final static By IMPORTANCE_SELECTOR = By.className("economic-calendar__filter-importance");
    private final static By CURRENCY_SELECTOR = By.className("economic-calendar__filter-currency");
    private final static By RESULTS_TABLE = By.className("ec-table");

    @Step("Open calendar page")
    public CalendarPage openPage() {
        open(PAGE_URL);
        getLogger().info("Opening calendar page: " + PAGE_URL);
        return this;
    }

    @Step("Close cookies panel")
    public CalendarPage closeCookiesPanel() {
        getLogger().info("Closing cookies panel.");
        if ($(VERTICAL_COOKIES_PANEL_CROSS).isDisplayed()) {
            $(VERTICAL_COOKIES_PANEL_CROSS).click();
        }
        return this;
    }

    @Step("Set period filter")
    public CalendarPage setPeriodFilter(LocalDate start, LocalDate end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.ENGLISH);
        getLogger().info("Setting time period filter to: " + formatter.format(start) + " - " + formatter.format(end));
        (new PeriodSelectorBlock($(PERIOD_SELECTOR))).setPeriod(start, end);
        return this;
    }

    public CheckboxComponent getImportanceCheckboxes() {
        getLogger().debug("Getting importance checkboxes component");
        return new CheckboxComponent($(IMPORTANCE_SELECTOR), this);
    }

    public CheckboxComponent getCurrencyCheckboxes() {
        getLogger().debug("Getting currency checkboxes component");
        return new CheckboxComponent($(CURRENCY_SELECTOR), this);
    }

    public EventsTableComponent getEventsTable() {
        getLogger().debug("Getting events table");
        return new EventsTableComponent($(RESULTS_TABLE), this);
    }

    @Step("Wait for results table to be updated")
    public CalendarPage waitForResultsTableToBeUpdated(int timeout) {
        getLogger().info("Waiting for table to be updated");
        int tableSize = getEventsTable().getEvents().size();
        waitUntil(() -> getEventsTable().getEvents().size() != tableSize, timeout, 500);
        return this;
    }
}