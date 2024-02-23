package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public SelenideElement mainPageProduct = $(".main-page"),
            searchArea = $(".search-catalog__input"),
            cardProductName = $(".product-card__name"),
            productCard = $(".product-card__link"),
            brandNameInProductCard = $(".product-page__header-brand");


    public MainPage searchProduct(String searchQuery){
        mainPageProduct.shouldBe(visible);
        searchArea.setValue(searchQuery).pressEnter();
        return this;
    }

    public MainPage moveToProduct(){
        productCard.click();
        return this;
    }

    public MainPage moveToBrand(){
        brandNameInProductCard.click();
        return this;
    }


    public MainPage checkProductByRequest(String productName){
        cardProductName.shouldHave(text(productName));
        return this;
    }

    public void checkProductsByRequest(String productName){
        $$(".product-card__name").shouldHave(texts(productName));
    }

}
