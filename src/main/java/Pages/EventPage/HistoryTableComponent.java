package Pages.EventPage;

import Interfaces.Component;
import Interfaces.UiNavigationEntity;
import Pages.CalendarPage.CalendarPage;
import Pages.CalendarPage.EventsTableRowComponent;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static Utils.Log.getLogger;

public class HistoryTableComponent implements Component {
    private SelenideElement mainElement;
    private UiNavigationEntity parentPage;

    private final static By TABLE_ROWS = By.className("event-table-history__item");

    HistoryTableComponent(SelenideElement mainElement, UiNavigationEntity parentPage) {
        this.mainElement = mainElement;
        this.parentPage = parentPage;
    }

    @Override
    public CalendarPage getParent() {
        getLogger().debug("Getting parent page: " + parentPage);
        return (CalendarPage) parentPage;
    }

    @Step("Get history items")
    public List<HistoryTableRowComponent> getHistoryItems() {
        getLogger().info("Getting history items list");
        List<HistoryTableRowComponent> rows = new ArrayList<>();
        mainElement.$$(TABLE_ROWS).forEach(row -> {
            rows.add(new HistoryTableRowComponent(row, this));
        });
        return rows;
    }
}