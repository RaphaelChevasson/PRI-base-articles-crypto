package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CATEGORY_ID")
	private BigInteger categoryId;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="SUBCATEGORY_ID")
	private Category category;

	//bi-directional many-to-one association to Category
	@OneToMany(mappedBy="category")
	private List<Category> categories;

	//bi-directional many-to-many association to Post
	@ManyToMany(mappedBy="categories")
	private List<Post> posts;

	//bi-directional many-to-one association to PostsCatergory
	@OneToMany(mappedBy="category")
	private List<PostsCatergory> postsCatergories;

	public Category() {
	}

	public BigInteger getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(BigInteger categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setCategory(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setCategory(null);

		return category;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<PostsCatergory> getPostsCatergories() {
		return this.postsCatergories;
	}

	public void setPostsCatergories(List<PostsCatergory> postsCatergories) {
		this.postsCatergories = postsCatergories;
	}

	public PostsCatergory addPostsCatergory(PostsCatergory postsCatergory) {
		getPostsCatergories().add(postsCatergory);
		postsCatergory.setCategory(this);

		return postsCatergory;
	}

	public PostsCatergory removePostsCatergory(PostsCatergory postsCatergory) {
		getPostsCatergories().remove(postsCatergory);
		postsCatergory.setCategory(null);

		return postsCatergory;
	}

}