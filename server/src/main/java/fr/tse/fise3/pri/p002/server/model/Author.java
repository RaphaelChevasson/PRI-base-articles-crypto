package fr.tse.fise3.pri.p002.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the authors database table.
 */
@Entity
@Table(name = "authors")
@NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID")
    private BigInteger authorId;

    @Column(name = "AUTHOR_NAME", columnDefinition="LONGTEXT")
    private String authorName;

    //bi-directional many-to-many association to Post
    @ManyToMany(mappedBy = "authors")
    private List<Post> posts;

    public Author() {
    }

    public BigInteger getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(BigInteger authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}