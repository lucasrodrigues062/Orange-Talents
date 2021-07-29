package br.zup.transacao.transacao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cartao {
    @Id
    private String id;
    private String email;
    @OneToMany(mappedBy = "cartao")
    private List<Transacao> transacaoes;

    @Override
    public String toString() {
        return "Cartao{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", transacaoes=" + transacaoes +
                '}';
    }

    public List<Transacao> getTransacaoes() {
        return transacaoes;
    }

    public Cartao() {
    }

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
