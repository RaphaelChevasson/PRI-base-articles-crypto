package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Table(name="posts")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="POST_ID")
	private BigInteger postId;

	private String address;

	@Column(name="AUTHOR_ID")
	private BigInteger authorId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String title;

	private String url;

	//bi-directional many-to-many association to Author
	@ManyToMany
	@JoinTable(
		name="posts_authors"
		, joinColumns={
			@JoinColumn(name="POST_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="AUTHOR_ID")
			}
		)
	private List<Author> authors;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="posts_catergories"
		, joinColumns={
			@JoinColumn(name="POST_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="CATEGORY_ID")
			}
		)
	private List<Category> categories;

	//bi-directional many-to-one association to PostsAuthor
	@OneToMany(mappedBy="post")
	private List<PostsAuthor> postsAuthors;

	//bi-directional many-to-one association to PostsCatergory
	@OneToMany(mappedBy="post")
	private List<PostsCatergory> postsCatergories;

	//bi-directional many-to-many association to Keyword
	@ManyToMany
	@JoinTable(
		name="posts_keywords"
		, joinColumns={
			@JoinColumn(name="POST_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="KEYWORD_ID")
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

	public BigInteger getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(BigInteger authorId) {
		this.authorId = authorId;
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

	public List<PostsAuthor> getPostsAuthors() {
		return this.postsAuthors;
	}

	public void setPostsAuthors(List<PostsAuthor> postsAuthors) {
		this.postsAuthors = postsAuthors;
	}

	public PostsAuthor addPostsAuthor(PostsAuthor postsAuthor) {
		getPostsAuthors().add(postsAuthor);
		postsAuthor.setPost(this);

		return postsAuthor;
	}

	public PostsAuthor removePostsAuthor(PostsAuthor postsAuthor) {
		getPostsAuthors().remove(postsAuthor);
		postsAuthor.setPost(null);

		return postsAuthor;
	}

	public List<PostsCatergory> getPostsCatergories() {
		return this.postsCatergories;
	}

	public void setPostsCatergories(List<PostsCatergory> postsCatergories) {
		this.postsCatergories = postsCatergories;
	}

	public PostsCatergory addPostsCatergory(PostsCatergory postsCatergory) {
		getPostsCatergories().add(postsCatergory);
		postsCatergory.setPost(this);

		return postsCatergory;
	}

	public PostsCatergory removePostsCatergory(PostsCatergory postsCatergory) {
		getPostsCatergories().remove(postsCatergory);
		postsCatergory.setPost(null);

		return postsCatergory;
	}

	public List<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

}