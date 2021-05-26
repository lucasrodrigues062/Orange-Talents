package br.com.zup.mercadolivre.detalhe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import br.com.zup.mercadolivre.produto.OpiniaoForm;
import br.com.zup.mercadolivre.produto.Produto;

public class DetalheProdutoForm {

    private BigDecimal preco;
    private String nome;
    private String descricao;
    private Set<DetalheCaracteristicaForm> caracteristicas;
    private List<String> imagensLinks = new ArrayList<String>();
    private List<String> perguntas = new ArrayList<String>();
    private List<OpiniaoForm> opinioes = new ArrayList<>();
    private BigDecimal mediaDeNotas;
    

    public DetalheProdutoForm(Produto produto) {
        
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas().stream().map(c -> new DetalheCaracteristicaForm(c)).collect(Collectors.toSet());
        produto.getImagens().forEach(i -> {
            imagensLinks.add(i.getLink());
        });
        produto.getPerguntas().forEach(p -> {
            perguntas.add(p.getTitulo());
        });
        BigDecimal somaDeNotas = new BigDecimal(0);
        for(int i = 0; i < produto.getOpinioes().size(); i++){
            opinioes.add(new OpiniaoForm(produto.getOpinioes().get(i)));
            BigDecimal nota = new BigDecimal(produto.getOpinioes().get(i).getNota().toString());
            somaDeNotas = somaDeNotas.add(nota);
        }
        if(somaDeNotas.compareTo(new BigDecimal("0")) > 0){
            this.mediaDeNotas = somaDeNotas.divide(new BigDecimal(produto.getOpinioes().size()), 2, RoundingMode.HALF_UP);
        } else {
            this.mediaDeNotas = new BigDecimal(0);
        }
        
        
        
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<DetalheCaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getImagensLinks() {
        return imagensLinks;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public List<OpiniaoForm> getOpinioes() {
        return opinioes;
    }


    public BigDecimal getMediaDeNotas() {
        return this.mediaDeNotas;
    }
  
    
    
}
