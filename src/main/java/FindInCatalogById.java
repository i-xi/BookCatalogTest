import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class FindInCatalogById extends TestBase {

    @Test(priority = 2, dependsOnGroups={"TestBase.beforeSuite", "AddBook.testForm"})
    public void testCatalog(ITestContext context) {
        String bookId = context.getAttribute("bookId").toString();
        driver.get("https://raamatukogu.herokuapp.com/catalog/books");
        WebElement bookLink = driver
                .findElement(By.xpath("//a[contains(@href, '/catalog/book/" + bookId + "')]"));
        String bookTitle = bookLink.getText();
        String bookEntry = driver
                .findElement(By.xpath("//a[contains(@href, '/catalog/book/" + bookId + "')]/parent::*"))
                .getText();
        String authorName = bookEntry.substring(bookTitle.length() + 2, bookEntry.length() - 1);

        Assert.assertEquals(bookTitle, book.getTitle());
        Assert.assertEquals(authorName, book.getAuthor());
    }

}
