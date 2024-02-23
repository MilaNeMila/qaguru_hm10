import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты на проверку продуктов на Wildberries")
public class MainPageTest extends TestBase {
    MainPage mainPage = new MainPage();


    @Tag("SMOKE")
    @CsvSource(value ={
            "Карты Таро, Карты Таро Уэйта",
            "Пленка для фотоаппарата, Фотопленка",
            "Свитер женский, Свитер"

    })
    @ParameterizedTest(name = "Поиск товара {1} по запросу {0}")
    public void searchShouldReturnVideoByRequest(String searchQuery, String productName){
        open("");
        mainPage.searchProduct(searchQuery);
        mainPage.checkProductByRequest(productName);
    }

    @Tags({
            @Tag("SMOKE"),
            @Tag("Web")
    })
    @ValueSource(strings = {
            "Карты Таро", "Фотопленка", "Свитер"
    })
    @ParameterizedTest(name = "Поиск товара {0}")
    public void searchShouldReturnVideoByRequestWithOneArguments(String searchQuery){
        open("");
        mainPage.searchProduct(searchQuery);
        mainPage.checkProductByRequest(searchQuery);
    }

    @Tags({
            @Tag("REGRESSION"),
            @Tag("Web")
    })
    static Stream<Arguments> searchShouldReturnVideoByRequestWithStream() {
        return Stream.of(
                Arguments.of(
                        "brands/pretty-and-funny",
                        "Pretty&Funny",
                        List.of("/ Обучающие Карты Таро Уэйта с инструкцией для начинающих",
                                "/ Карты Таро Уэйта с инструкцией для начинающих")
                ),
                Arguments.of(
                        "brands/310474810-yukami",
                        "YUKAMI",
                        List.of("/ Классические гадальные Карты Таро Уэйта 78 карт инструкция",
                                "/ Таро Славянские Легенды 78 карт золотой срез",
                                "/ Таро Темный Особняк 78 карт черный срез",
                                "/ Таро Элементалей 78 карт черный срез",
                                "/ Таро Темный Особняк 78 карт золотой срез",
                                "/ Таро Славянские Легенды 78 карт черный срез",
                                "/ Таро Элементалей 78 карт серебряный срез")
                )
        );
    }
    @ParameterizedTest(name = "Переход к бренду {1} по ссылке {0} и проверка товаров")
    @MethodSource("searchShouldReturnVideoByRequestWithStream")
    public void searchShouldReturnVideoByRequestWithStream(String brandLink, String brandName, List<String> productNameList){
        open(brandLink);
        $(".catalog-title").shouldHave(text(brandName));
        $$(".product-card__name").shouldHave(texts(productNameList));
    }

}
