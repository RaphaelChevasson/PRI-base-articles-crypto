package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the posts_authors database table.
 * 
 */
@Embeddable
public class PostsAuthorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AUTHOR_ID", insertable=false, updatable=false)
	private String authorId;

	@Column(name="POST_ID", insertable=false, updatable=false)
	private String postId;

	public PostsAuthorPK() {
	}
	public String getAuthorId() {
		return this.authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getPostId() {
		return this.postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PostsAuthorPK)) {
			return false;
		}
		PostsAuthorPK castOther = (PostsAuthorPK)other;
		return 
			this.authorId.equals(castOther.authorId)
			&& this.postId.equals(castOther.postId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.authorId.hashCode();
		hash = hash * prime + this.postId.hashCode();
		
		return hash;
	}
}