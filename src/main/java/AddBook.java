import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddBook extends TestBase {

    @Test(priority = 1, groups="AddBook.testForm")
    public void testForm(ITestContext context) {
        driver.get("https://raamatukogu.herokuapp.com/catalog/book/create");
        setBookTitle(book.getTitle());
        selectAuthorByName(book.getAuthor());
        setBookSummary(book.getSummary());
        setBookISBN(book.getISBN());
        setBookGenre(book.getGenre());

        driver.findElement(By.xpath("//button[contains(text(), 'Submit')]")).click();

        Assert.assertEquals(getTitle(), book.getTitle());
        Assert.assertEquals(getAuthor(), book.getAuthor());
        Assert.assertEquals(getSummary(), book.getSummary());
        Assert.assertEquals(getISBN(), book.getISBN());
        Assert.assertEquals(getGenre(), book.getGenre());

        // Save book Id on success
        String bookId = getBookId();
        context.setAttribute("bookId", bookId);
    }

    private void setBookTitle(String bookTitle) {
        driver.findElement(By.id("title")).sendKeys(bookTitle);
    }

    private void selectAuthorByName(String authorName) {
        Select authorDropdown = new Select(driver.findElement(By.id("author")));
        List<WebElement> options = authorDropdown.getOptions();
        // For multiple options for one author like "Abnett, Dan" take the second one.
        List<WebElement> authorOptions = filterByAuthorName(options, authorName);
        String authorId = (authorOptions.size() == 1)
                ? authorOptions.get(0).getAttribute("value")
                : authorOptions.get(1).getAttribute("value");
        authorDropdown.selectByValue(authorId);
    }

    private List<WebElement> filterByAuthorName(List<WebElement> authorNames, String name) {
        return authorNames
                .stream()
                .filter(authorName -> Objects.equals(authorName.getText(), name))
                .collect(Collectors.toList());
    }

    private void setBookSummary(String bookSummary) {
        driver.findElement(By.id("summary")).sendKeys(bookSummary);
    }

    private void setBookISBN(String bookISBN) {
        driver.findElement(By.id("isbn")).sendKeys(bookISBN);
    }

    private void setBookGenre(String bookGenre) {
        driver.findElement(By.xpath("//label[text()='Genre:']/following::div[1]//label[text()='" + bookGenre +"']"))
                .click();
    }

    private String getBookId() {
        String currentUrl = driver.getCurrentUrl();
        // Book id is the last in path
        int index = currentUrl.lastIndexOf('/');
        return currentUrl.substring(index + 1);
    }

    private String getTitle() {
        WebElement titleElement = driver.findElement(By.xpath("//h1[contains(text(), 'Title:')]"));
        return titleElement.getText().substring(7);
    }

    private String getAuthor() {
        WebElement authorElement = driver.findElement(By.xpath("//*[contains(text(), 'Author:')]/following::a"));
        return authorElement.getText();
    }

    private String getSummary() {
        WebElement summaryElement = driver.findElement(By.xpath("//*[contains(text(), 'Summary:')]/parent::*"));
        return summaryElement.getText().substring(9);
    }

    private String getISBN() {
        WebElement isbnElement = driver.findElement(By.xpath("//*[contains(text(), 'ISBN:')]/parent::*"));
        return isbnElement.getText().substring(6);
    }

    private String getGenre() {
        WebElement genreElement = driver.findElement(By.xpath("//*[contains(text(), 'Genre:')]/following::a"));
        return genreElement.getText();
    }
}