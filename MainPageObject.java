package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement skipOnboarding(String locator, String error_message, long timeoutInSeconds) {
        WebElement elementSkip = waitForElementPresent(locator, error_message, timeoutInSeconds);
        elementSkip.click();
        return elementSkip;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    ;


    public void testSwipeUpArticle(long timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = (int) (size.width * 0.5);
            int start_y = (int) (size.height * 0.6);
            int end_y = (int) (size.height * 0.2);
            action.press(PointOption.point(x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).release().perform();

        } else {
            System.out.println("Method testSwipeUpArticle does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public void swipeUpQuick() {
        if (driver instanceof AppiumDriver) {
            testSwipeUpArticle(200);
        } else {
            System.out.println("Method swipeUpQuick does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void testSwipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        if (driver instanceof AppiumDriver) {
            int alreadySwiped = 0;
            while (driver.findElements(by).isEmpty()) {
                if (alreadySwiped > maxSwipes) {
                    waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                    return;
                }
                swipeUpQuick();
                ++alreadySwiped;
            }
        } else {
            System.out.println("Method testSwipeUpToFindElement does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeElementToLeft(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(PointOption.point(left_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .moveTo(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeOnboarding(long timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AndroidTouchAction action = new AndroidTouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int y = (int) (size.height * 0.5);
            int start_x = (int) (size.width * 0.2);
            int end_x = (int) (size.width * 0.6);
            action.press(PointOption.point(start_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(end_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).release().perform();
        } else {
            System.out.println("Method swipeOnboarding does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();

    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeout) {
        WebElement element = waitForElementPresent(locator, error_message, timeout);
        return element.getAttribute(attribute);
    }

    public int countOfArticles(String locator, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        int elements = driver.findElements(by).size();
        return elements;
    }

    public void assertElementPresent(String locator) {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        Boolean title = element.isDisplayed();
        if (title == false) {
            String default_message = "A title is not present";
            throw new AssertionError(default_message);
        }
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        }

        else if(by_type.equals("id")) {
            return By.id(locator);
        }

        else if(by_type.equals("css")) {
            return By.cssSelector(locator);
        }

        else {
            throw new IllegalArgumentException("Cannot get type of locator");
        }

    }
}
