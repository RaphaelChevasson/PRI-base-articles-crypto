package fr.tse.fise3.pri.p002.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the posts database table.
 */
@Entity
@Table(name = "posts")
@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private BigInteger postId;

    @Lob
    private String address;

    @Column(name = "BOOK_TITLE")
    @Lob
    private String bookTitle;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Lob
    private String title;

    private String url;

    //bi-directional many-to-many association to Author
    @ManyToMany
    @JoinTable(
            name = "posts_authors"
            , joinColumns = {
            @JoinColumn(name = "POST_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "AUTHOR_ID")
    }
    )
    private List<Author> authors;

    //bi-directional many-to-many association to Category
    @ManyToMany
    @JoinTable(
            name = "posts_catergories"
            , joinColumns = {
            @JoinColumn(name = "POST_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "CATEGORY_ID")
    }
    )
    private List<Category> categories;

    //bi-directional many-to-many association to Keyword
    @ManyToMany
    @JoinTable(
            name = "posts_keywords"
            , joinColumns = {
            @JoinColumn(name = "POST_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "KEYWORD_ID")
    }
    )
    private List<Keyword> keywords;

    public Post() {
    }

    public BigInteger getPostId() {
        return this.postId;
    }

    public void setPostId(BigInteger postId) {
        this.postId = postId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Keyword> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

}