package fr.tse.fise3.pri.p002.server.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;


/**
 * The persistent class for the known_urls database table.
 * 
 */
@Entity
@Table(name="known_urls")
@NamedQuery(name="KnownUrl.findAll", query="SELECT k FROM KnownUrl k")
public class KnownUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="URL_ID")
	private BigInteger urlId;

	@Column(name="URL_NAME")
	@Lob
	private String urlName;

	public KnownUrl() {
	}

	public BigInteger getUrlId() {
		return this.urlId;
	}

	public void setUrlId(BigInteger urlId) {
		this.urlId = urlId;
	}

	public String getUrlName() {
		return this.urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

}