package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement mainPageProduct = $(".main-page"),
            searchArea = $(".search-catalog__input"),
            cardProductName = $(".product-card__name");


    public MainPage searchProduct(String searchQuery){
        mainPageProduct.shouldBe(visible);
        searchArea.setValue(searchQuery).pressEnter();
        return this;
    }



    public MainPage checkProductByRequest(String productName){
        cardProductName.shouldHave(text(productName));
        return this;
    }


}
