package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "//*[@text='Java (programming language)']";
        TITLE_REPLACE = "//*[contains(@text, '{SUBSTRING}')]";
        FOOTER_ELEMENT = "//*[@text='View article in browser']";
        SAVE_BUTTON = "org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "//android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']";
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "//*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
        ADD_TO_SAVED_LIST_BUTTON = "//*[contains(@text,'{SUBSTRING}')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);

    }
}
