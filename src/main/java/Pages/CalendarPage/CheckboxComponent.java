package Pages.CalendarPage;

import Interfaces.CheckboxValues;
import Interfaces.Component;
import Interfaces.UiNavigationEntity;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static Utils.Log.getLogger;

public class CheckboxComponent implements Component {
    private SelenideElement mainElement;
    private UiNavigationEntity parentPage;

    private final static By CHECKBOXES_LIST = By.cssSelector("li");
    private final static By CHECKBOXES_LIST_LABEL = By.cssSelector("label");
    private final static By CHECKBOXES_LIST_CHECKBOX = By.cssSelector("input");

    private final static String EXACT_LABEL_LOCATOR = ".//label[text()='%s']";
    private final static String EXACT_CHECKBOX_LOCATOR = EXACT_LABEL_LOCATOR + "/ancestor::li/input";

    CheckboxComponent(SelenideElement mainElement, UiNavigationEntity parentPage) {
        this.mainElement = mainElement;
        this.parentPage = parentPage;
    }

    @Override
    public CalendarPage getParent() {
        getLogger().debug("Getting parent page: " + parentPage);
        return (CalendarPage) parentPage;
    }

    @Step("Disable all chekboxes except one")
    public CheckboxComponent disableAllExcept(CheckboxValues exception) {
        getLogger().info("Disabling all importance checkboxes except: " + exception.getLabel());
        mainElement.$$(CHECKBOXES_LIST).forEach(element -> {
            if(element.find(CHECKBOXES_LIST_LABEL).getText().equals(exception.getLabel())) {
                if(!element.find(CHECKBOXES_LIST_CHECKBOX).isSelected()) {
                    element.find(CHECKBOXES_LIST_LABEL).click();
                }
            } else {
                if(element.find(CHECKBOXES_LIST_CHECKBOX).isSelected()) {
                    element.find(CHECKBOXES_LIST_LABEL).click();
                }
            }
        });
        return this;
    }

    @Step("Set checkbox value")
    public CheckboxComponent setCheckbox(CheckboxValues label, boolean value) {
        getLogger().info("Setting importance checkbox "+label.getLabel()+" value to: " + value);
        if (mainElement.find(By.xpath(String.format(EXACT_CHECKBOX_LOCATOR, label.getLabel()))).isSelected() != value) {
            mainElement.find(By.xpath(String.format(EXACT_LABEL_LOCATOR, label.getLabel()))).click();
        }
        return this;
    }
}
