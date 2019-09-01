package Pages.CalendarPage;

import Enums.CurrencyEnum;
import Interfaces.Component;
import Interfaces.UiNavigationEntity;
import Pages.EventPage.EventPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static Utils.Log.getLogger;
import static com.codeborne.selenide.Selenide.page;

public class EventsTableRowComponent implements Component {
    private SelenideElement mainElement;
    private UiNavigationEntity parentPage;

    private final static By LINK = By.cssSelector("a");
    private final static By CURRENCY = By.className("ec-table__curency-name");

    EventsTableRowComponent(SelenideElement mainElement, UiNavigationEntity parentPage) {
        this.mainElement = mainElement;
        this.parentPage = parentPage;
    }

    @Override
    public EventsTableComponent getParent() {
        getLogger().debug("Getting parent page: " + parentPage);
        return (EventsTableComponent) parentPage;
    }

    @Step("Open event")
    public EventPage openEvent() {
        getLogger().info("Opening event: " + getEventName());
        mainElement.$(LINK).click();
        return page(EventPage.class);
    }

    public String getEventName() {
        String eventName = mainElement.$(LINK).getText();
        getLogger().debug("Getting event name: " + eventName);
        return eventName;
    }

    public CurrencyEnum getCurrency() {
        CurrencyEnum curency = CurrencyEnum.getCurrencyByCode(mainElement.$(CURRENCY).getText());
        getLogger().debug("Getting event currency: " + curency.getCode());
        return curency;
    }
}
