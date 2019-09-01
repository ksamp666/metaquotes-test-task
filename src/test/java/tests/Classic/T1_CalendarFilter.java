package tests.Classic;

import Base.UiBaseTest;
import Enums.CurrencyEnum;
import Enums.ImportanceEnum;
import Pages.CalendarPage.CalendarPage;
import Pages.EventPage.HistoryTableRowComponent;
import RestServices.LstToApiService;
import Utils.HistoryTablePrinter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static Utils.Log.getLogger;
import static Utils.TestUtils.getCurrentPageLink;

public class T1_CalendarFilter extends UiBaseTest {
    private final static ImportanceEnum IMPORTANCE_TO_FILTER_BY = ImportanceEnum.Medium;
    private final static CurrencyEnum CURRENCY_TO_FILTER_BY = CurrencyEnum.CHF;

    private LstToApiService lstToApiService = new LstToApiService();
    private String eventPageShortenLink;

    @AfterMethod
    public void afterMethod() {
        if(eventPageShortenLink!=null) {
            lstToApiService.deleteLink(eventPageShortenLink);
        }
    }

    @Test(description = "Filter calendar events")
    public void test() {
        List<HistoryTableRowComponent> historyItems = (new CalendarPage())
                .openPage()
                .closeCookiesPanel()
                .setPeriodFilter(getStartDate(), getEndDate())
                .getImportanceCheckboxes()
                .disableAllExcept(IMPORTANCE_TO_FILTER_BY)
                .getParent()
                .getCurrencyCheckboxes()
                .disableAllExcept(CURRENCY_TO_FILTER_BY)
                .getParent()
                .waitForResultsTableToBeUpdated(2000)
                .getEventsTable()
                .getEvents().stream().filter(event -> event.getCurrency() == CURRENCY_TO_FILTER_BY).findFirst().orElse(null)
                .openEvent()
                .checkImportanceValue(IMPORTANCE_TO_FILTER_BY)
                .checkCountryValue(CURRENCY_TO_FILTER_BY.getCountry())
                .getHistoryTable()
                .getHistoryItems();
        HistoryTablePrinter.printTable(historyItems, YearMonth.now().minusMonths(12), YearMonth.now());
        generateAndPrintShortUrl();
    }

    private void generateAndPrintShortUrl() {
        eventPageShortenLink = lstToApiService.getShortenLink(getCurrentPageLink()).getData().getShortUrl();
        getLogger().info("Event page short url: " + eventPageShortenLink);
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
