package Pages.EventPage;

import Enums.ImportanceEnum;
import Interfaces.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static Utils.Log.getLogger;
import static com.codeborne.selenide.Selenide.$;

public class EventPage implements Page {
    private final static By IMPORTANCE = By.className("event-table__importance");
    private final static By COUNTRY = By.xpath("//span[contains(text(),'Country')]/following-sibling::div/a");
    private final static By HISTORY_TAB_BUTTON = By.xpath("//li[@data-content='history']");
    private final static By HISTORY_TAB_CONTENT = By.id("tab_content_history");

    @Step("Check importance value for event")
    public EventPage checkImportanceValue(ImportanceEnum expectedValue) {
        getLogger().info("Checking importance value to be: "+expectedValue.getLabel());
        Assert.assertEquals(ImportanceEnum.getImportanceByTitle($(IMPORTANCE).getText()), expectedValue, "Importance value is incorrect!");
        return this;
    }

    @Step("Check country value for event")
    public EventPage checkCountryValue(String expectedValue) {
        getLogger().info("Checking country value to be: "+expectedValue);
        Assert.assertEquals($(COUNTRY).getText(), expectedValue, "Country value is incorrect!");
        return this;
    }

    @Step("Click history tab")
    public HistoryTableComponent getHistoryTable() {
        getLogger().info("Clicking history tab");
        $(HISTORY_TAB_BUTTON).click();
        return new HistoryTableComponent($(HISTORY_TAB_CONTENT), this);
    }
}
