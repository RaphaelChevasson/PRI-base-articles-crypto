package fr.tse.fise3.pri.p002.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the data_sources database table.
 */
@Entity
@Table(name = "data_sources")
@NamedQuery(name = "DataSource.findAll", query = "SELECT d FROM DataSource d")
public class DataSource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 45)
    private String name;

    @Column(name = "CURRENT_OFFSET")
    private long currentOffset;

    private long total;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    //bi-directional many-to-one association to Post
    @OneToMany(mappedBy = "dataSource")
    @JsonIgnore
    private List<Post> posts;

    public DataSource() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCurrentOffset() {
        return this.currentOffset;
    }

    public void setCurrentOffset(long currentOffset) {
        this.currentOffset = currentOffset;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Post addPost(Post post) {
        getPosts().add(post);
        post.setDataSource(this);

        return post;
    }

    public Post removePost(Post post) {
        getPosts().remove(post);
        post.setDataSource(null);

        return post;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}