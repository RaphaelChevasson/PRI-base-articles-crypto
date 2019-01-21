package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posts_catergories database table.
 * 
 */
@Entity
@Table(name="posts_catergories")
@NamedQuery(name="PostsCatergory.findAll", query="SELECT p FROM PostsCatergory p")
public class PostsCatergory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PostsCatergoryPK id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID", insertable = false, updatable = false)
	private Category category;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="POST_ID", insertable = false, updatable = false)
	private Post post;

	public PostsCatergory() {
	}

	public PostsCatergoryPK getId() {
		return this.id;
	}

	public void setId(PostsCatergoryPK id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}