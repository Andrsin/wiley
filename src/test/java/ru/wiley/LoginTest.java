package ru.wiley;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Nested;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;
import static junit.framework.TestCase.assertEquals;
import static org.apache.commons.lang3.Validate.matchesPattern;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;


public class LoginTest {

    static String currentURL;

    final static String homePageURL = "https://www.wiley.com/en-us";


    @Test
    public void testOpenSite() {

        //1

        $(".navigation-menu-items").shouldHave
                (text("Who We Serve"),
                        text("Subjects"),
                        text("ABOUT"));
    }
    //2
    //

    @Test
    public void testOpenSite2() {

        $(".navigation-menu-items li").shouldHave(text("Who We Serve")).click();


        $$("#Level1NavNode1 li").shouldHave(size(11));


        $$("#Level1NavNode1 li").shouldHave(texts("Students", "Instructors", "Book Authors", "Professionals", "Researchers", "Institutions", "Librarians", "Corporations", "Societies", "Journal Editors", "Government"));
    }

    //3
    @Test
    public void testOpenSite3() {
        $(".navigation-menu-items li").shouldHave(text("Who We Serve")).click();
        $("#Level1NavNode1 li").shouldHave(text("Students")).click();
        currentURL = url();
        assertEquals("https://www.wiley.com/en-us/students", currentURL);

        //$$(".active").last().shouldHave(text("Students"));
        // $(".navigation-menu-items li").shouldHave(text("Students")).click()


        $(".sg-title-h1").shouldHave(text("Students"));
        //$$(Selectors.ByText("Learn More "))
        // $$("a").filterBy(text("Learn More")).shouldHave(texts(matchText("Learn More""$")));
        //$$("#mytable tbody tr").shouldHave(size(2))


        sleep(2000);
    }

    //5
    @Test
    public void five() {

        $(".js-responsive-image").click();
        currentURL = url();
        assertEquals(currentURL, homePageURL);

    }

    //6
    @Test
    public void six() {

        $(".glyphicon-search").click();
        currentURL = url();
        assertEquals(currentURL, homePageURL);

    }

    //6
    @Test
    public void seven() {

        $("#js-site-search-input").setValue("Java");
        sleep(2000);
        $(".main-navigation-search-autocomplete").shouldBe(visible);
        assertThat($$(".search-list div").size(), is(4));
        ElementsCollection javaElements = $$(".search-list div");
        for (SelenideElement j : javaElements) {

            assertEquals(true, j.getText().matches("^Java.*"));

        }
        System.out.println(javaElements);

        sleep(2000);

        currentURL = url();
        assertEquals(currentURL, homePageURL);

    }


    @Test
    public void find() {
        $("#js-site-search-input").setValue("Java").pressEnter();
        ElementsCollection productHeaders = $$(".product-item  h3 a");
        productHeaders.shouldHaveSize(10);

        for (SelenideElement e : productHeaders) {
            e.getText().contains("Java");
        }
        ElementsCollection productItem = $$("product-item");
        int i= productItem.size();
        for (SelenideElement e:productItem){

           for (e.$(".add-to-cart-button") a: shouldBe(exist);)
            System.out.println(i);
        }

    }


    @Before
    public void openUrl() {
        open("https://www.wiley.com/en-us");
        if ($(byXpath("/html/body/main/div[1]/div/div/form/div[3]/button[2]")).isDisplayed())
            $(byXpath("/html/body/main/div[1]/div/div/form/div[3]/button[2]")).click();

    }


}

