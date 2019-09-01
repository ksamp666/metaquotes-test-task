package Pages.CalendarPage;

import Interfaces.Block;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static Utils.Log.getLogger;
import static com.codeborne.selenide.Selenide.$;

class PeriodSelectorBlock implements Block {
    private SelenideElement mainElement;

    private final static By LEFT_SECTION = By.className("date-picker__part-left");
    private final static By RIGHT_SECTION = By.className("date-picker__part-right");
    private final static By SAVE_BUTTON = By.xpath(".//button[text()='Save']");

    PeriodSelectorBlock(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }

    void setPeriod(LocalDate start, LocalDate end) {
        getLogger().debug("Clicking period input field");
        mainElement.click();
        getLogger().debug("Settings left calendar value: " + start);
        (new PeriodSelectorSectionBlock($(LEFT_SECTION))).setDate(start);
        getLogger().debug("Setting right calendar value " + end);
        (new PeriodSelectorSectionBlock($(RIGHT_SECTION))).setDate(end);
        getLogger().debug("Clicking save button");
        $(SAVE_BUTTON).click();
    }
}
