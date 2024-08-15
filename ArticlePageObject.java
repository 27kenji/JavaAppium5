package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
    TITLE,
    TITLE_REPLACE,
    FOOTER_ELEMENT,
    SAVE_BUTTON,
    ADD_TO_LIST_BUTTON,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON,
    ADD_TO_SAVED_LIST_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchTitle(String substring) {
        return TITLE_REPLACE.replace("{SUBSTRING}", substring);
    }

    private static String getResultSavedArticleTitle(String name_of_folder) {
        return ADD_TO_SAVED_LIST_BUTTON.replace("{SUBSTRING}", name_of_folder);

    }
    /* TEMPLATES METHODS */

    /*public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE),
                "Cannot find article title on page", 15);

    }*/

    public WebElement waitForTitleElementAndReplace(String substring) {
        String search_result_title = getResultSearchTitle(substring);
        return this.waitForElementPresent(search_result_title, "Cannot find title with substring " + substring, 10);

    }

    /*public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }*/

    public String getArticleTitleAndReplace(String substring) {
        WebElement title_element = waitForTitleElementAndReplace(substring);
        return title_element.getAttribute("text");
    }


    public void swipeToFooter() {
        this.testSwipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 10);
    }

    public void swipeUp() {
        this.testSwipeUpArticle(2000);
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find button to save article", 5);
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON,
                "Cannot find 'Add to list' button", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder,
                "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press OK button", 5);

    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot close article", 5);
    }

    public void secondAddArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find button to save article", 5);
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON,
                "Cannot find 'Add to list' button", 5);
        String element = getResultSavedArticleTitle(name_of_folder);
        this.waitForElementAndClick(element, "Cannot find saved list", 15);

    }


    public void articleTitleIsDisplayed(String title) {
        String element = getResultSearchTitle(title);
        this.assertElementPresent(element);
    }
}
