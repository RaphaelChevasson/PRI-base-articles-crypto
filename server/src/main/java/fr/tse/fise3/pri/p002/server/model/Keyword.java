package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the keywords database table.
 * 
 */
@Entity
@Table(name="keywords")
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="KEYWORD_ID")
	private BigInteger keywordId;

	@Column(name="KEYWORD_NAME")
	private String keywordName;

	private int reserved;

	private String word;

	//bi-directional many-to-many association to Post
	@ManyToMany(mappedBy="keywords")
	private List<Post> posts;

	//bi-directional many-to-one association to PostsKeyword
	@OneToMany(mappedBy="keyword")
	private List<PostsKeyword> postsKeywords;

	public Keyword() {
	}

	public BigInteger getKeywordId() {
		return this.keywordId;
	}

	public Keyword setKeywordId(BigInteger keywordId) {
		this.keywordId = keywordId;
		return this;
	}

	public String getKeywordName() {
		return this.keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public int getReserved() {
		return this.reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<PostsKeyword> getPostsKeywords() {
		return this.postsKeywords;
	}

	public void setPostsKeywords(List<PostsKeyword> postsKeywords) {
		this.postsKeywords = postsKeywords;
	}

	public PostsKeyword addPostsKeyword(PostsKeyword postsKeyword) {
		getPostsKeywords().add(postsKeyword);
		postsKeyword.setKeyword(this);

		return postsKeyword;
	}

	public PostsKeyword removePostsKeyword(PostsKeyword postsKeyword) {
		getPostsKeywords().remove(postsKeyword);
		postsKeyword.setKeyword(null);

		return postsKeyword;
	}

}