package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the authors database table.
 * 
 */
@Entity
@Table(name="authors")
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AUTHOR_ID")
	private BigInteger authorId;

	@Column(name="AUTHOR_NAME")
	private String authorName;

	//bi-directional many-to-many association to Post
	@ManyToMany(mappedBy="authors")
	private List<Post> posts;

	//bi-directional many-to-one association to PostsAuthor
	@OneToMany(mappedBy="author")
	private List<PostsAuthor> postsAuthors;

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

	public List<PostsAuthor> getPostsAuthors() {
		return this.postsAuthors;
	}

	public void setPostsAuthors(List<PostsAuthor> postsAuthors) {
		this.postsAuthors = postsAuthors;
	}

	public PostsAuthor addPostsAuthor(PostsAuthor postsAuthor) {
		getPostsAuthors().add(postsAuthor);
		postsAuthor.setAuthor(this);

		return postsAuthor;
	}

	public PostsAuthor removePostsAuthor(PostsAuthor postsAuthor) {
		getPostsAuthors().remove(postsAuthor);
		postsAuthor.setAuthor(null);

		return postsAuthor;
	}

}