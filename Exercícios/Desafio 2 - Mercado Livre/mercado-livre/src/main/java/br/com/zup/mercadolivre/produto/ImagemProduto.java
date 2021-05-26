package br.com.zup.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@URL
	private String link;
	@ManyToOne
	private Produto produto;
	
	
	public ImagemProduto(String link, Produto produto) {
		super();
		this.link = link;
		this.produto = produto;
	}


	@Override
	public String toString() {
		return "ImagemProduto [id=" + id + ", link=" + link + ", produto=" + produto + "]";
	}
	
	private ImagemProduto() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagemProduto other = (ImagemProduto) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}


	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}
	public Produto getProduto() {
		return produto;
	}
	
	
	
}
