package fr.tse.fise3.pri.p002.server.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the posts_authors database table.
 */
@Entity
@Table(name = "posts_authors")
@NamedQuery(name = "PostsAuthor.findAll", query = "SELECT p FROM PostsAuthor p")
public class PostsAuthor implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PostsAuthorPK id;

    //bi-directional many-to-one association to Author
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", insertable = false, updatable = false)
    private Author author;

    //bi-directional many-to-one association to Post
    @ManyToOne
    @JoinColumn(name = "POST_ID", insertable = false, updatable = false)
    private Post post;

    public PostsAuthor() {
    }

    public PostsAuthorPK getId() {
        return this.id;
    }

    public void setId(PostsAuthorPK id) {
        this.id = id;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}