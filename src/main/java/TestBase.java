import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ramatukogu.Book;

public class TestBase {
    protected static final WebDriver driver = new ChromeDriver();
    public Book book;

    @Test(groups="TestBase.beforeSuite")
    @BeforeSuite
    public void beforeSuite() {
        book = new Book(
                "It",
                "King, Stephen",
                "The story follows the experiences of seven children as they are terrorized by an evil entity that exploits the fears of its victims to disguise itself while hunting its prey.",
                "0-670-81302-8",
                "Fiction"
        );
    }

    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }

}
