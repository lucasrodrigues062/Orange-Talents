package br.com.zup.mercadolivre.produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemDto {

	@Size(min = 1) @NotNull
	private List<MultipartFile> imagens = new ArrayList<>();
	

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public ImagemDto(@Size(min = 1) List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "ImagemDto [imagens=" + imagens + "]";
	}
	
	public List<ImagemProduto> toImagemProduto (Produto produto, Set<String> links){
		
		List<ImagemProduto> imagens = new ArrayList<ImagemProduto>();
		links.forEach(link -> {
			ImagemProduto imagem = new ImagemProduto(link, produto);
			imagens.add(imagem);
		});
		
		return imagens;
		
	}
		
	
	
	
	
}
