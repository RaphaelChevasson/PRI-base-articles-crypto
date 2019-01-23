package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posts_keywords database table.
 * 
 */
@Entity
@Table(name="posts_keywords")
@NamedQuery(name="PostsKeyword.findAll", query="SELECT p FROM PostsKeyword p")
public class PostsKeyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PostsKeywordPK id;

	//bi-directional many-to-one association to Keyword
	@ManyToOne
	@JoinColumn(name="KEYWORD_ID", insertable = false, updatable = false)
	private Keyword keyword;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="POST_ID", insertable = false, updatable = false)
	private Post post;

	public PostsKeyword() {
	}

	public PostsKeywordPK getId() {
		return this.id;
	}

	public void setId(PostsKeywordPK id) {
		this.id = id;
	}

	public Keyword getKeyword() {
		return this.keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}