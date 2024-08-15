package tests;

import lib.CoreTestCase2;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase2 {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        String title_before_rotation = ArticlePageObject.getArticleTitleAndReplace("Automation for Apps");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitleAndReplace("Automation for Apps");
        assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitleAndReplace("Automation for Apps");
        assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);

    }

    @Test
    public void testCheckSearchArticleInBackground() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForSearchResult("Automation for Apps");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Automation for Apps");
    }

}
