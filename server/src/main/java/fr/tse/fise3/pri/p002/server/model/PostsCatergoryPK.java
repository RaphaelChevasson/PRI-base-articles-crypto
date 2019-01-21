package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the posts_catergories database table.
 * 
 */
@Embeddable
public class PostsCatergoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="POST_ID", insertable=false, updatable=false)
	private String postId;

	@Column(name="CATEGORY_ID", insertable=false, updatable=false)
	private String categoryId;

	public PostsCatergoryPK() {
	}
	public String getPostId() {
		return this.postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PostsCatergoryPK)) {
			return false;
		}
		PostsCatergoryPK castOther = (PostsCatergoryPK)other;
		return 
			this.postId.equals(castOther.postId)
			&& this.categoryId.equals(castOther.categoryId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.postId.hashCode();
		hash = hash * prime + this.categoryId.hashCode();
		
		return hash;
	}
}