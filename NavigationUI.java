package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {

    private static final String
    CLOSE_SEARCH_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    SAVED_ON_MENU = "//android.widget.FrameLayout[@content-desc='Saved']";



    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }


    public void closeSearchResults() {
            this.waitForElementAndClick(CLOSE_SEARCH_BUTTON,
                    "Cannot close search result",5);
}
    public void clickSavedOnMenu() {
        this.waitForElementAndClick(SAVED_ON_MENU,
                "Cannot find navigation button to 'Saved'", 5);
    }
}
