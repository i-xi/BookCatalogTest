package ramatukogu;

public class Book {
    private String bookTitle;
    private String authorName;
    private String bookSummary;
    private String bookISBN;
    private String bookGenre;

    public Book(String title, String name, String summary, String ISBN, String genre) {
        this.bookTitle = title;
        this.authorName = name;
        this.bookSummary = summary;
        this.bookISBN = ISBN;
        this.bookGenre = genre;
    }

    public String getTitle() {
        return bookTitle;
    }
    public String getAuthor() {
        return authorName;
    }
    public String getSummary() {
        return bookSummary;
    }
    public String getISBN() {
        return bookISBN;
    }
    public String getGenre() {
        return bookGenre;
    }
}
