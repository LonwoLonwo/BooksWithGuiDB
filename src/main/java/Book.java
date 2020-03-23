public class Book
{
    private int id;
    private String name;
    private String author;
    private int publishingYear;

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    int getPublishingYear() {
        return publishingYear;
    }

    void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }
}
