package Pages.CalendarPage;

import Interfaces.Component;
import Interfaces.UiNavigationEntity;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static Utils.Log.getLogger;

public class EventsTableComponent implements Component {
    private SelenideElement mainElement;
    private UiNavigationEntity parentPage;

    private final static By TABLE_ROWS = By.className("ec-table__item");

    EventsTableComponent(SelenideElement mainElement, UiNavigationEntity parentPage) {
        this.mainElement = mainElement;
        this.parentPage = parentPage;
    }

    @Override
    public CalendarPage getParent() {
        getLogger().debug("Getting parent page: " + parentPage);
        return (CalendarPage) parentPage;
    }

    @Step("Get events list from events table")
    public List<EventsTableRowComponent> getEvents() {
        getLogger().info("Getting events list from events table");
        List<EventsTableRowComponent> rows = new ArrayList<>();
        mainElement.$$(TABLE_ROWS).forEach(row -> {
            rows.add(new EventsTableRowComponent(row, this));
        });
        return rows;
    }
}
