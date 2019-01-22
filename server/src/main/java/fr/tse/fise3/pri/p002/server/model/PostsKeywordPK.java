package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the posts_keywords database table.
 * 
 */
@Embeddable
public class PostsKeywordPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="POST_ID", insertable=false, updatable=false)
	private String postId;

	@Column(name="KEYWORD_ID", insertable=false, updatable=false)
	private String keywordId;

	public PostsKeywordPK() {
	}
	public String getPostId() {
		return this.postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getKeywordId() {
		return this.keywordId;
	}
	public void setKeywordId(String keywordId) {
		this.keywordId = keywordId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PostsKeywordPK)) {
			return false;
		}
		PostsKeywordPK castOther = (PostsKeywordPK)other;
		return 
			this.postId.equals(castOther.postId)
			&& this.keywordId.equals(castOther.keywordId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.postId.hashCode();
		hash = hash * prime + this.keywordId.hashCode();
		
		return hash;
	}
}