package fr.tse.fise3.pri.p002.server.dto;


import java.util.Date;
import java.util.List;

public class PostDTO {

    private String address;

    private Date date;

    private String title;

    private String url;

    private List<String> authors;
    private List<String> keywords;

    public PostDTO() {
    }

    public PostDTO(String address, Date date, String title, String url, List<String> author, List<String> keywords) {
        super();
        this.address = address;
        this.date = date;
        this.title = title;
        this.url = url;
        this.authors = author;
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "address='" + address + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", authors=" + authors +
                ", keywords=" + keywords +
                '}';
    }
}
