package fr.tse.fise3.pri.p002.commons;

public class Post {
    private String author;
    private String title;
    private String date;
    private String topicAbstract;
    private String keywords;

    public Post() {
    }

    public Post(String author, String title, String date, String topicAbstract, String keywords) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.topicAbstract = topicAbstract;
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopicAbstract() {
        return topicAbstract;
    }

    public void setTopicAbstract(String topicAbstract) {
        this.topicAbstract = topicAbstract;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", topicAbstract='" + topicAbstract + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
