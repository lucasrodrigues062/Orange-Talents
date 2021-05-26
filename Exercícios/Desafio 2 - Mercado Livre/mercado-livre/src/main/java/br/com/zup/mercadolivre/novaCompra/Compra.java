package br.com.zup.mercadolivre.novaCompra;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.novaCompra.retornoCompra.Pagamento;
import br.com.zup.mercadolivre.novaCompra.retornoCompra.StatusPagamento;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
@Entity
public class Compra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @NotNull @Valid
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Positive
    private Integer qtd;
    @ManyToOne @Valid
    @JoinColumn(name = "usuario_id")
    private Usuario user;
    @Enumerated(EnumType.STRING) @NotNull
    private Gateway gateway;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    @OneToMany(mappedBy = "compra")
    private List<Pagamento> pagamentos = new ArrayList<Pagamento>();


    public Compra(Produto produto, Integer qtd, Usuario user, Gateway gateway) {
        this.produto = produto;
        this.qtd = qtd;
        this.user = user;
        this.gateway = gateway;
        this.statusCompra = StatusCompra.INICIADA;
    }


    private Compra() {
    }

    public Long getId() {
        return this.id;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Integer getQtd() {
        return this.qtd;
    }

    public Usuario getUser() {
        return this.user;
    }


    @Override
    public String toString() {
        return "Compra [gateway=" + gateway + ", id=" + id + ", produto=" + produto + ", qtd=" + qtd + ", user=" + user
                + "]";
    }



    public Gateway getGateway() {
        return this.gateway;
    }

    public StatusCompra getStatusCompra() {
        return this.statusCompra;
    }



    public List<Pagamento> getPagamentos() {
        return this.pagamentos;
    }

    public boolean atualizaStatus(Pagamento pagamento){
       if(!this.statusCompra.equals(StatusCompra.FINALIZADA)) {
        
           if(pagamento.getStatusPagamento().equals(StatusPagamento.SUCESSO)) {
               this.statusCompra = StatusCompra.FINALIZADA;
               return true;
           } else {
               this.statusCompra = StatusCompra.FALHA;
               return false;
           }

        
        
       }
        return true;
    }
}
