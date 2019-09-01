package tests.BDD.stepdefs;

import Configuration.ConfigurationProperties;
import Enums.CurrencyEnum;
import Enums.ImportanceEnum;
import Pages.CalendarPage.CalendarPage;
import Pages.EventPage.EventPage;
import Pages.EventPage.HistoryTableRowComponent;
import RestServices.LstToApiService;
import Utils.HistoryTablePrinter;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java8.En;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static Utils.Log.getLogger;
import static Utils.TestUtils.getCurrentPageLink;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class StepDefinitions implements En {
    private final static String USER_AGENT = ConfigurationProperties.getProperties().getUserAgent();

    private CalendarPage calendarPage = new CalendarPage();
    private EventPage eventPage = new EventPage();
    private LstToApiService lstToApiService = new LstToApiService();
    private String eventPageShortenLink;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + USER_AGENT);
        WebDriver webDriver = new ChromeDriver(options);
        setWebDriver(webDriver);
        getLogger().info("Chrome driver successfully initialized.");
    }

    @After
    public void tearDown() {
        if(eventPageShortenLink!=null) {
            lstToApiService.deleteLink(eventPageShortenLink);
        }
        WebDriverRunner.getAndCheckWebDriver().close();
    }

    public StepDefinitions() {
        Given("^Calendar page is opened$", () -> {
            calendarPage.openPage();
        });
        When("^User closes cookies notification panel$", () -> {
            calendarPage.closeCookiesPanel();
        });
        And("^User sets period filter to current month$", () -> {
            calendarPage.setPeriodFilter(getStartDate(), getEndDate());
        });
        And("^User sets all importance checkboxes to false except \"([^\"]*)\"$", (String importanceLabel) -> {
            calendarPage.getImportanceCheckboxes()
                    .disableAllExcept(ImportanceEnum.getImportanceByTitle(importanceLabel));
        });
        And("^User sets all currency checkboxes to false except \"([^\"]*)\"$", (String currencyCode) -> {
            calendarPage.getCurrencyCheckboxes()
                    .disableAllExcept(CurrencyEnum.getCurrencyByCode(currencyCode));
        });
        And("^User waits for events table to be updated$", () -> {
            calendarPage.waitForResultsTableToBeUpdated(2000);
        });
        And("^User clicks the first item in table with currency \"([^\"]*)\"$", (String currencyCode) -> {
            calendarPage.getEventsTable()
                    .getEvents().stream().filter(event -> event.getCurrency() == CurrencyEnum.getCurrencyByCode(currencyCode)).findFirst().orElse(null)
                    .openEvent();
        });
        Then("^Event importance should be set to \"([^\"]*)\"$", (String importanceLabel) -> {
            eventPage.checkImportanceValue(ImportanceEnum.getImportanceByTitle(importanceLabel));
        });
        And("^Event country should be set to \"([^\"]*)\"$", (String countryName) -> {
            eventPage.checkCountryValue(countryName);
        });
        And("^Event history for last year should be printed to log$", () -> {
            List<HistoryTableRowComponent> historyItems = eventPage
                    .getHistoryTable()
                    .getHistoryItems();
            HistoryTablePrinter.printTable(historyItems, YearMonth.now().minusMonths(12), YearMonth.now());
        });
        And("^Short url for event page should be generated and printed to log$", () -> {
            eventPageShortenLink = lstToApiService.getShortenLink(getCurrentPageLink()).getData().getShortUrl();
            getLogger().info("Event page short url: " + eventPageShortenLink);
        });

    }

    private LocalDate getStartDate() {
        return LocalDate.now().withDayOfMonth(1);
    }

    private LocalDate getEndDate() {
        LocalDate endDate = LocalDate.now();
        endDate = endDate.withDayOfMonth(endDate.lengthOfMonth());
        return endDate;
    }
}
