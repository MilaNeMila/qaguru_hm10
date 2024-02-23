import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setupBrowserSettings(){
        Configuration.baseUrl = "https://www.wildberries.ru/";
        Configuration.pageLoadStrategy = "eager";
    }
}
