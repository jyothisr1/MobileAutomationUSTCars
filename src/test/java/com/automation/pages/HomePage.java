package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Collections;

public class HomePage extends BasePage {

    @FindBy(id = "com.expedia.bookings:id/uds_button_label")
    WebElement acceptBtn;

    @FindBy(xpath = "//android.widget.TextView[@text='Get started']")
    WebElement getStartedBtn;

    @FindBy(xpath = "//android.widget.Button[@text='Allow']")
    WebElement allowButton;
    @FindBy(xpath = "//android.view.View[@content-desc='Close sheet']/following-sibling::android.widget.Button")
    WebElement closeLocation;

    @FindBy(xpath = "//android.view.View[@content-desc='Close']")
    WebElement closeBtn;

    @FindBy(xpath = "//android.widget.TextView[@text='Let’s go']")
    WebElement letsGoBtn;

    @FindBy(xpath = "//android.widget.TextView[@text='Flights']")
    WebElement flightsTab;

    @FindBy(id = "com.expedia.bookings:id/btn_accept_cookies")
    WebElement acceptCookiesBtn;

    @FindBy(xpath = "//android.view.View/following-sibling::android.widget.Button")
    WebElement closeSignSaving;

    @FindBy(xpath = "//android.view.View[@resource-id='PreSignInDismissIcon']")
    WebElement advCloseBtn;

    @FindBy(xpath = "//android.widget.TextView[@text='Not now']")
    WebElement notNowBtn;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Go beyond your typical stay']")
    WebElement getBeyound;

    @FindBy(xpath = "//android.widget.TextView[@text='Discover more']")
    WebElement swipeElement;

    @FindBy(xpath = "(//android.widget.TextView[@text='Explore stays in popular destinations']/following-sibling::android.view.View//android.widget.TextView[1])[1]")
    WebElement placeName;

    @FindBy(xpath = "//android.widget.TextView[contains(@content-desc,'Explore stays in popular destinations')]")
    WebElement exploreStays;


    public void openApplication() {
//        clickOnElementIfPresent(acceptCookiesBtn);
//        clickOnElementIfPresent(advCloseBtn);
//        clickOnElementIfPresent(notNowBtn);
        clickOnElementIfPresent(closeSignSaving);
        clickOnElementIfPresent(allowButton);
        clickOnElementIfPresent(closeLocation);
//        closeSignSaving.click();
    }


    public boolean isHomeScreenDisplayed() {
        return isPresent(flightsTab);
//        return true;
    }

    public void clickOnFlightTab() {
        flightsTab.click();
    }

    public void performScrollAndSwipe() {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        while (!isPresents(getBeyound)) {
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);
        }
//        if(isPresents(getBeyound)) {
//            while (!isPresents(swipeElement)) {
//                scrollOrSwipe(width / 2, height / 2, 0, height / 2);
//            }
//        }

        while (!(isPresents(exploreStays) && isPresents(placeName))) {
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);
        }

//        Swipe logic

        WebElement currentCard = driver.findElement(By.xpath("//android.widget.TextView[@text='Explore stays in popular destinations']/following-sibling::android.view.View//android.view.View"));
        String currentTitle = placeName.getText();
        String previousTitle = "";

        while (!currentTitle.equals(previousTitle)) {
            int x = currentCard.getLocation().getX();
            int y = currentCard.getLocation().getY();
            int cardWidth = currentCard.getSize().getWidth();
            int cardHeight = currentCard.getSize().getHeight();

            scrollOrSwipe(x + cardWidth, y + cardHeight / 2, 0, y + cardHeight / 2);

             currentCard = driver.findElement(By.xpath("//android.widget.TextView[@text='Explore stays in popular destinations']/following-sibling::android.view.View//android.view.View"));
            previousTitle = currentTitle;
            placeName = driver.findElement(By.xpath("(//android.widget.TextView[@text='Explore stays in popular destinations']/following-sibling::android.view.View//android.widget.TextView[1])"));
            currentTitle = placeName.getText();
            System.out.println("previous title:" + previousTitle);
            System.out.println("current title:" + currentTitle);

        }
    }
}
