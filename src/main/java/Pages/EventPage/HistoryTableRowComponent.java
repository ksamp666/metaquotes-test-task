package Pages.EventPage;

import Enums.CurrencyEnum;
import Interfaces.Component;
import Interfaces.UiNavigationEntity;
import Pages.CalendarPage.EventsTableComponent;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static Utils.Log.getLogger;
import static com.codeborne.selenide.Selenide.page;

public class HistoryTableRowComponent implements Component {
    private SelenideElement mainElement;
    private UiNavigationEntity parentPage;

    private final static By DATE = By.className("event-table-history__date");
    private final static By PERIOD = By.className("event-table-history__period");
    private final static By ACTUAL = By.className("event-table-history__actual");
    private final static By FORECAST = By.className("event-table-history__forecast");
    private final static By PREVIOUS = By.className("event-table-history__previous");

    HistoryTableRowComponent(SelenideElement mainElement, UiNavigationEntity parentPage) {
        this.mainElement = mainElement;
        this.parentPage = parentPage;
    }

    @Override
    public EventsTableComponent getParent() {
        getLogger().debug("Getting parent page: " + parentPage);
        return (EventsTableComponent) parentPage;
    }

    public String getDate() {
        String date = mainElement.$(DATE).getText();
        getLogger().debug("Getting history item date: " + date);
        return date;
    }

    public String getPeriod() {
        String period = mainElement.$(PERIOD).getText();
        getLogger().debug("Getting history item date: " + period);
        return period;
    }

    public String getActual() {
        String actual = mainElement.$(ACTUAL).getText();
        getLogger().debug("Getting history item date: " + actual);
        return actual;
    }

    public String getForecast() {
        String forecast = mainElement.$(FORECAST).getText();
        getLogger().debug("Getting history item date: " + forecast);
        return forecast;
    }

    public String getPrevious() {
        String previous = mainElement.$(PREVIOUS).getText();
        getLogger().debug("Getting history item date: " + previous);
        return previous;
    }
}
