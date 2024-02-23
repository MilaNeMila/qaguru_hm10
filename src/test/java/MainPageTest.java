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

@DisplayName("����� �� �������� ��������� �� Wildberries")
public class MainPageTest extends TestBase {
    MainPage mainPage = new MainPage();


    @Tag("SMOKE")
    @CsvSource(value ={
            "����� ����, ����� ���� �����",
            "������ ��� ������������, ����������",
            "������ �������, ������"

    })
    @ParameterizedTest(name = "����� ������ {1} �� ������� {0}")
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
            "����� ����", "����������", "������"
    })
    @ParameterizedTest(name = "����� ������ {0}")
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
                        List.of("/ ��������� ����� ���� ����� � ����������� ��� ����������",
                                "/ ����� ���� ����� � ����������� ��� ����������")
                ),
                Arguments.of(
                        "brands/310474810-yukami",
                        "YUKAMI",
                        List.of("/ ������������ ��������� ����� ���� ����� 78 ���� ����������",
                                "/ ���� ���������� ������� 78 ���� ������� ����",
                                "/ ���� ������ ������� 78 ���� ������ ����",
                                "/ ���� ����������� 78 ���� ������ ����",
                                "/ ���� ������ ������� 78 ���� ������� ����",
                                "/ ���� ���������� ������� 78 ���� ������ ����",
                                "/ ���� ����������� 78 ���� ���������� ����")
                )
        );
    }
    @ParameterizedTest(name = "������� � ������ {1} �� ������ {0} � �������� �������")
    @MethodSource("searchShouldReturnVideoByRequestWithStream")
    public void searchShouldReturnVideoByRequestWithStream(String brandLink, String brandName, List<String> productNameList){
        open(brandLink);
        $(".catalog-title").shouldHave(text(brandName));
        $$(".product-card__name").shouldHave(texts(productNameList));
    }

}
